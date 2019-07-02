package com.xiaoice.example.aptcompile;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

/**
 * 创建.java文件
 */
public class CreateClassModel {

    private CreateClassEntity mEntity;

    public CreateClassModel(CreateClassEntity entity) {
        this.mEntity = entity;
    }

    public TypeSpec generateJavaCode() {
        return TypeSpec.classBuilder(mEntity.getmBindingClassName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateJavaMethod())
                .build();
    }

    private MethodSpec generateJavaMethod() {
        ClassName host = ClassName.bestGuess(mEntity.getmTypeElement().getQualifiedName().toString());
        MethodSpec.Builder builder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(host, "host");

        for (int id : mEntity.getmVariableElementMap().keySet()) {
            VariableElement variableElement = mEntity.getmVariableElementMap().get(id);
            // host.mTv=(TextView)host.findViewById(id);
            String name = variableElement.getSimpleName().toString();
            String type = variableElement.asType().toString();
            builder.addCode("host." + name + " = (" + type + ")host.findViewById(" + id + ");");
        }
        return builder.build();
    }
}
