package com.appic.androidvalidators.model;

import android.util.Patterns;


import com.appic.androidvalidators.interfaces.ErrorCallBack;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Email {

    public static String TAG = Email.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    //public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String IS_EMAIL = "IsEmail";
    public static String IS_REQUIRED = "IsRequired";
    public static String EMPTY = "Empty";
    public HashMap<String, Boolean> emailValidationResponse;
    private ErrorCallBack errorCallBack;

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }


    public static class EmailBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        public HashMap<String, Boolean> emailValidationResponse;

        public EmailBuilder(String value) {
            this.value = value;
        }

        public EmailBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public HashMap<String, Boolean> build() {
            boolean success;
            emailValidationResponse = new HashMap<>();
            if (this.isRequired) {
                emailValidationResponse.put(IS_REQUIRED, true);
            } else {
                emailValidationResponse.put(IS_REQUIRED, false);
            }
            if (this.value != null && !this.value.isEmpty()) {
                emailValidationResponse.put(EMPTY, false);
                //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                Pattern pattern = Patterns.EMAIL_ADDRESS;
                if (pattern.matcher(this.value).matches()) {
                    success = true;
                    emailValidationResponse.put(IS_EMAIL, true);
                } else {
                    success = false;
                    emailValidationResponse.put(IS_EMAIL, false);
                }
            } else {
                success = false;
                emailValidationResponse.put(EMPTY, true);
                emailValidationResponse.put(IS_EMAIL, false);
            }
            emailValidationResponse.put(SUCCESS, success);
            return emailValidationResponse;
        }

    }

    public boolean isValid(HashMap<String, Boolean> emailValidation) {

        if (emailValidation.get(Email.SUCCESS)) {
            return true;
        } else {
            if (emailValidation.get(Email.IS_REQUIRED)) {
                if (!emailValidation.get(Email.EMPTY)) {
                    if (!emailValidation.get(Email.IS_EMAIL)) {
                        errorCallBack.onValidationError("Not Valid Email");
                        return false;
                    }
                } else {
                    errorCallBack.onValidationError("email is should not be empty");
                    return false;
                }
            } else {
                if (!emailValidation.get(Email.EMPTY)) {
                    if (!emailValidation.get(Email.IS_EMAIL)) {
                        errorCallBack.onValidationError("Not Valid Email");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Email(ErrorCallBack c) {
        this.errorCallBack = c;
    }

    private Email(EmailBuilder emailBuilder) {
        this.value = emailBuilder.value;
        this.isRequired = emailBuilder.isRequired;
        this.emailValidationResponse = emailBuilder.emailValidationResponse;

    }
}
