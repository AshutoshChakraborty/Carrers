package com.project.supportClasses;

import android.content.Context;

import com.project.integratedservices.R;

import cn.refactor.lib.colordialog.ColorDialog;


public class MyColorDialog
{
  static ColorDialog colorDialog = null;

    //private constructor.
    private MyColorDialog(){}

    public static ColorDialog getInstance(Context context) {
//        if(colorDialog==null){
            colorDialog = new ColorDialog(context);
            colorDialog.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
            colorDialog.setTitle(R.string.app_name);
//        }
        return colorDialog;
    }
}
