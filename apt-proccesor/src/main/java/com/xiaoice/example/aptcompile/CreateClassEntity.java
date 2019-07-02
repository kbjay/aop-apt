package com.xiaoice.example.aptcompile;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * 创建.java文件实体类
 *
 * @author v-zewan
 * @time 2019/7/2 11:57
 **/
public class CreateClassEntity {
    /**
     * class_name
     */
    private String mBindingClassName;
    /**
     * package
     */
    private String mPackageName;
    /**
     * class
     */
    private TypeElement mTypeElement;
    /**
     * Variable
     */
    private Map<Integer, VariableElement> mVariableElementMap = new HashMap<>();

    /**
     * init
     *
     * @param elementUtils
     * @param typeElement
     */
    public CreateClassEntity(Elements elementUtils, TypeElement typeElement) {
        this.mTypeElement = typeElement;
        PackageElement packageElement = elementUtils.getPackageOf(typeElement);
        mPackageName = packageElement.getQualifiedName().toString();
        mBindingClassName = typeElement.getSimpleName().toString() + "_BindView";
    }

    public void putVariableElement(int id, VariableElement element) {
        mVariableElementMap.put(id, element);
    }

    public String getmBindingClassName() {
        return mBindingClassName;
    }

    public String getmPackageName() {
        return mPackageName;
    }

    public TypeElement getmTypeElement() {
        return mTypeElement;
    }

    public Map<Integer, VariableElement> getmVariableElementMap() {
        return mVariableElementMap;
    }
}
