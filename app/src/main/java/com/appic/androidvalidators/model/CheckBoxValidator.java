package com.appic.androidvalidators.model;

import android.widget.CheckBox;

import java.util.HashMap;


public class CheckBoxValidator {
    public static String TAG = CheckBoxValidator.class.getSimpleName();

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String NULL = "NULL";
    public static String IS_REQUIRED = "IsRequired";

    public static class CheckBoxBuilder {
        private CheckBox mCheckBox; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;

        public CheckBoxBuilder(CheckBox value) {
            this.mCheckBox = value;
        }

        public CheckBoxValidator.CheckBoxBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }


        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> hashMap = new HashMap<>();
            if (this.isRequired) {
                hashMap.put(IS_REQUIRED, true);
            } else {
                hashMap.put(IS_REQUIRED, false);
            }
            if (mCheckBox != null && !mCheckBox.isChecked()) {
                hashMap.put(NULL, false);
                hashMap.put(SUCCESS, true);
            } else {
                hashMap.put(NULL, true);
                hashMap.put(SUCCESS, false);
            }
            return hashMap;
        }

    }

    private CheckBoxValidator() {
    }
}
