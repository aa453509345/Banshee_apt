package com.example.model;

import com.example.BansheeView;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by Carry on 16/8/9.
 */
public class BansheeViewModel {
  private VariableElement mFiledElement;
  private int resId;
    public BansheeViewModel(Element element){
        if(element.getKind()!= ElementKind.FIELD){
            throw new IllegalArgumentException("banshee only annnotated Filed");
        }

        mFiledElement=(VariableElement)element;
        BansheeView view=mFiledElement.getAnnotation(BansheeView.class);
        //得到注解的id
        int id=view.value();
        if(id<0){
            throw new IllegalArgumentException("resid is invaild");
        }
        resId=id;

    }


    public int getResId(){
        return resId;
    }

    public TypeMirror getFiledType(){
        return mFiledElement.asType();
    }


    public String getFiledName(){
        return mFiledElement.getSimpleName().toString();
    }
}
