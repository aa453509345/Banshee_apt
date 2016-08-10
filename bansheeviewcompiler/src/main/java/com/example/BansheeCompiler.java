package com.example;

import com.example.model.BansheeViewModel;
import com.example.model.ClassModel;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Created by Carry on 16/8/9.
 */
//autoService自动生成meta－inf
@AutoService(Processor.class)
public class BansheeCompiler extends AbstractProcessor {
    private Filer mFiler;
    private Elements mElement;
    private Messager messager;
    private static Map<String,ClassModel> map=new HashMap<>();
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        map.clear();
         processBansheeView(roundEnv);
        for(ClassModel model:map.values()){
            try {
                model.generateBansheeView().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
        return true;
    }

    private void processBansheeView(RoundEnvironment roundEnv) {
       for(Element e:roundEnv.getElementsAnnotatedWith(BansheeView.class)){
             ClassModel model=getClassModel(e);
             BansheeViewModel viewmodel=new BansheeViewModel(e);
             model.addToFileds(viewmodel);
       }


    }

    private ClassModel getClassModel(Element e) {
        //得到注解所在类的名字
        TypeElement element= (TypeElement) e.getEnclosingElement();
        String fullName=element.getQualifiedName().toString();
       //messager.printMessage(Diagnostic.Kind.NOTE,String.format("fullname>>>>%s",fullName));
        ClassModel model=map.get(fullName);
        if(model==null){
            model=new ClassModel(element,mElement);
            map.put(fullName,model);
        }

        return model;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set=new HashSet<>();
        set.add(BansheeView.class.getCanonicalName());
        return set;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        mFiler= processingEnv.getFiler();
        mElement=processingEnv.getElementUtils();
        messager=processingEnv.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
