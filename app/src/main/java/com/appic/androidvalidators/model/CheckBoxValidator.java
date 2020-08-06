package com.appic.androidvalidators.model;

import android.util.Log;
import android.widget.CheckBox;

public class CheckBoxValidator {
    public static String TAG = CheckBoxValidator.class.getSimpleName();

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


        public CheckBoxValidator build() {
            CheckBoxValidator checkBoxValidator = new CheckBoxValidator();
            if (this.isRequired) {
                if (mCheckBox != null && !mCheckBox.isChecked()) {
                    return checkBoxValidator;
                } else {
                    Log.e(TAG, "Not Selected or Null");
                }
            }
            return null;
        }

    }

    private CheckBoxValidator() {
    }
}
