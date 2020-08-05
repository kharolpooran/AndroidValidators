package com.appic.androidvalidators.model;

import android.util.Log;

public class PhoneNumber {
    public static String TAG = PhoneNumber.class.getSimpleName();
    private String value; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public static class PhoneNumberBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;

        public PhoneNumberBuilder(String value) {
            this.value = value;
        }

        public PhoneNumberBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public PhoneNumber build() {
            PhoneNumber email = new PhoneNumber(this);
            if (this.isRequired) {
                if (this.value.length() == 10) {
                    return email;
                } else {
                    Log.e(TAG, "Invalid Number");
                }
            }
            return email;
        }

    }

    private PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        this.value = phoneNumberBuilder.value;
        this.isRequired = phoneNumberBuilder.isRequired;
    }
}
