package com.appic.androidvalidators.model;

import android.util.Log;
import android.widget.EditText;

import java.util.HashMap;

public class PhoneNumber {
    public static String TAG = PhoneNumber.class.getSimpleName();
    private String value; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    private int maxLength = 10;
    private EditText mEditText;
    private boolean checkInputType = false;

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String MAX_LENGTH = "MaxLength";
    public static String IS_REQUIRED = "IsRequired";
    public static String EMPTY = "Empty";
    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public Boolean checkInputType() {
        return checkInputType;
    }

    public static class PhoneNumberBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        private int maxLength = 10;
        private EditText mEditText;
        private boolean checkInputType = false;

        public PhoneNumberBuilder(String value) {
            this.value = value;
        }

        public PhoneNumberBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public PhoneNumberBuilder setMaxLenght(int maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public PhoneNumberBuilder setEditText(EditText mEditText){
            this.mEditText = mEditText;
            return this;
        }

        public PhoneNumberBuilder checkInputType(boolean checkInputType){
            this.checkInputType = checkInputType;
            return this;
        }

        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> phoneNumberValidatorResp = new HashMap<String, Boolean>();

            if (this.isRequired) {
                if (this.value != null && !this.value.isEmpty() && this.value.length() > 0) {
                    if (this.value.length() == maxLength) {
                        phoneNumberValidatorResp.put(SUCCESS, true);
                        phoneNumberValidatorResp.put(MAX_LENGTH, true);
                    } else {
                        phoneNumberValidatorResp.put(SUCCESS, false);
                        phoneNumberValidatorResp.put(MAX_LENGTH, false);
                    }
                } else {
                    phoneNumberValidatorResp.put(SUCCESS, false);
                    phoneNumberValidatorResp.put(EMPTY, true);
                }
            } else {
                if (this.value != null && !this.value.isEmpty()) {

                } else {
                    phoneNumberValidatorResp.put(SUCCESS, false);
                    phoneNumberValidatorResp.put(EMPTY, true);
                }
            }
            return phoneNumberValidatorResp;
        }

    }

    private PhoneNumber(PhoneNumberBuilder phoneNumberBuilder) {
        this.value = phoneNumberBuilder.value;
        this.isRequired = phoneNumberBuilder.isRequired;
        this.maxLength = phoneNumberBuilder.maxLength;
        this.mEditText = phoneNumberBuilder.mEditText;
        this.checkInputType = phoneNumberBuilder.checkInputType;
    }
}
