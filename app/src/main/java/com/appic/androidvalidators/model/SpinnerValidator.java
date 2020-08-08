package com.appic.androidvalidators.model;

import android.widget.Spinner;

import java.util.HashMap;


public class SpinnerValidator {
    public static String TAG = SpinnerValidator.class.getSimpleName();

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String NULL = "NULL";
    public static String IS_REQUIRED = "IsRequired";

    public static class SpinnerBuilder {
        private Spinner mSpinner; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        private String value = "";

        public SpinnerBuilder(Spinner value) {
            this.mSpinner = value;
        }

        public SpinnerValidator.SpinnerBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }


        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> spinnerBooleanHashMap = new HashMap<String, Boolean>();
            boolean success = true;
            if (this.isRequired) {
                spinnerBooleanHashMap.put(IS_REQUIRED, true);
            } else {
                spinnerBooleanHashMap.put(IS_REQUIRED, false);
            }
            if (mSpinner != null && mSpinner.getSelectedItem() != null) {
                spinnerBooleanHashMap.put(NULL, false);
                success = true;
            } else {
                success = false;
                spinnerBooleanHashMap.put(NULL, true);
            }
            spinnerBooleanHashMap.put(SUCCESS, success);
            return spinnerBooleanHashMap;
        }

    }

    private SpinnerValidator() {
    }
}
