package com.xiaoice.example.aptcompile;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.xiaoice.example.aptannotation.BindView;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;
    private Messager mMessage;
    //save entities that create .java
    private Map<String, CreateClassEntity> mEntities = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        mMessage = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        info("-->call process!");
        mEntities.clear();
        //1：init entity
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //must variableElement
        for (Element element : elementsAnnotatedWith) {
            if (element instanceof VariableElement) {
                VariableElement variableElement = (VariableElement) element;
                TypeElement classElement = (TypeElement) variableElement.getEnclosingElement();
                String fullClassName = classElement.getQualifiedName().toString();
                CreateClassEntity entity = mEntities.get(fullClassName);
                if (entity == null) {
                    entity = new CreateClassEntity(mElementUtils, classElement);
                    mEntities.put(fullClassName, entity);
                }

                entity.putVariableElement(variableElement.getAnnotation(BindView.class).value(), variableElement);
            }
        }

        //2:use javapoet build .java
        for (String key : mEntities.keySet()) {
            CreateClassEntity createClassEntity = mEntities.get(key);
            if (createClassEntity != null) {
                TypeSpec typeSpec = new CreateClassModel(createClassEntity).generateJavaCode();
                JavaFile javaFile = JavaFile.builder(createClassEntity.getmPackageName(), typeSpec).build();
                try {
                    javaFile.writeTo(mFiler);
                    info("-->create java success!!");
                } catch (IOException e) {
                    info("-->create java failed!!");
                    e.printStackTrace();
                }
            }

        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public void info(String info) {
        if (mMessage != null) {
            mMessage.printMessage(Diagnostic.Kind.NOTE, info);
        }
    }
}
