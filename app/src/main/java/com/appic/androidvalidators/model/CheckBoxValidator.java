package com.appic.androidvalidators.model;

import android.widget.CheckBox;
import com.appic.androidvalidators.interfaces.ErrorCallBack;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class CheckBoxValidator {
    public static String TAG = CheckBoxValidator.class.getSimpleName();

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String IS_CHECKED = "IS_CHECKED";
    public static String IS_REQUIRED = "IsRequired";
    private ErrorCallBack errorCallBack;

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
            if (mCheckBox != null && mCheckBox.isChecked()) {
                hashMap.put(IS_CHECKED, true);
                hashMap.put(SUCCESS, true);
            } else {
                hashMap.put(IS_CHECKED, false);
                hashMap.put(SUCCESS, false);
            }
            return hashMap;
        }

    }

    public boolean isValid(@NotNull HashMap<String, Boolean> hashMap) {

        if (hashMap.get(CheckBoxValidator.SUCCESS)) {
            return true;
        } else {
            if (hashMap.get(CheckBoxValidator.IS_REQUIRED)) {
                if (!hashMap.get(CheckBoxValidator.IS_CHECKED)) {
                    errorCallBack.onValidationError("Please check checkbox.");
                    return false;
                }
            }
        }
        return true;
    }

    public CheckBoxValidator(ErrorCallBack s) {
        this.errorCallBack = s;
    }
}
