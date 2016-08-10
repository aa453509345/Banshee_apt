package com.example.model;

import com.example.BansheeView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Carry on 16/8/9.
 */
public class ClassModel {
    private List<BansheeViewModel> fileds;
    private TypeElement mTypeElement;
    private Elements mElementsUtils;
    public ClassModel(TypeElement element, Elements elementsUtils){
      this.fileds=new ArrayList<>();
      this.mElementsUtils=elementsUtils;
      this.mTypeElement=element;
    }


    public void addToFileds(BansheeViewModel bansheeViewModel){
        fileds.add(bansheeViewModel);
    }

    public JavaFile generateBansheeView(){
        MethodSpec.Builder methodBuilder=MethodSpec.methodBuilder("inject")
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(Override.class)
        .addParameter(TypeName.get(mTypeElement.asType()),"host",Modifier.FINAL)
        .addParameter(TypeName.OBJECT,"source")
        .addParameter(ClassName.get("benmu.com.bansheeview.provide","Provide"),"provide");
        //构造出inject方法

        for(BansheeViewModel bansheeView:fileds){
            //实例化控件
            methodBuilder.addStatement("host.$N = ($T)(provide.findView(source,$L))",bansheeView.getFiledName(),ClassName.get(bansheeView.getFiledType()),bansheeView.getResId());

        }

        //生成内部类
        TypeSpec finderClass=TypeSpec.classBuilder(mTypeElement.getSimpleName()+"$$Banshee")
        .addModifiers(Modifier.PUBLIC)
        .addSuperinterface(ParameterizedTypeName.get(ClassName.get("benmu.com.bansheeview","Banshee"),TypeName.get(mTypeElement.asType())))
        .addMethod(methodBuilder.build()).build();

        String packageName=mElementsUtils.getPackageOf(mTypeElement).getQualifiedName().toString();
        return JavaFile.builder(packageName,finderClass).build();
    }
}
