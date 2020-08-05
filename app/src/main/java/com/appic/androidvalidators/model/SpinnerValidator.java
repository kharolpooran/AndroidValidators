package com.appic.androidvalidators.model;

import android.util.Log;
import android.widget.Spinner;

public class SpinnerValidator {
    public static String TAG = SpinnerValidator.class.getSimpleName();

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


        public SpinnerValidator build() {
            SpinnerValidator spinnerValidator = new SpinnerValidator();
            if (this.isRequired) {
                if (mSpinner != null && mSpinner.getSelectedItem() != null) {
                    return spinnerValidator;
                } else {
                    Log.e(TAG, "Not Selected or Null");
                }
            }
            return null;
        }

    }

    private SpinnerValidator() {
    }
}
