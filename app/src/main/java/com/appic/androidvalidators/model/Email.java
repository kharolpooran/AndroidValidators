package com.appic.androidvalidators.model;

import android.util.Log;

import java.util.HashMap;

public class Email {

    public static String TAG = Email.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String IS_EMAIL = "IsEmail";
    public static String IS_REQUIRED = "IsRequired";
    public static String EMPTY = "Empty";

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public static class EmailBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;

        public EmailBuilder(String value) {
            this.value = value;
        }

        public EmailBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public HashMap<String, Boolean> build() {
            boolean success;
            HashMap<String, Boolean> emailValidationResponse = new HashMap<>();
            if (this.isRequired) {
                if (this.value != null && !this.value.isEmpty()) {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (this.value.matches(emailPattern)) {
                        success = true;
                        emailValidationResponse.put(IS_EMAIL, true);
                    } else {
                        success = false;
                        emailValidationResponse.put(IS_EMAIL, false);
                    }
                } else {
                    success = false;
                    emailValidationResponse.put(IS_REQUIRED, false);
                }

            } else {
                if (this.value != null && !this.value.isEmpty()) {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (this.value.matches(emailPattern)) {
                        success = true;
                        emailValidationResponse.put(IS_EMAIL, true);
                    } else {
                        success = false;
                        emailValidationResponse.put(IS_EMAIL, false);
                    }
                } else {
                    success = true;
                    emailValidationResponse.put(IS_REQUIRED, false);
                }
            }
            emailValidationResponse.put(SUCCESS, success);
            return emailValidationResponse;
        }

    }

    private Email(EmailBuilder emailBuilder) {
        this.value = emailBuilder.value;
        this.isRequired = emailBuilder.isRequired;
    }
}
