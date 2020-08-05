package com.appic.androidvalidators.model;

import android.util.Log;

public class Email {

    public static String TAG=Email.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;

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

        public Email build() {
            Email email = new Email(this);
            if (this.isRequired) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (this.value.matches(emailPattern)) {
                    return email;
                } else {
                    Log.e(TAG,"Invalid Email.");
                }
            }
            return null;
        }

    }

    private Email(EmailBuilder emailBuilder) {
        this.value = emailBuilder.value;
        this.isRequired = emailBuilder.isRequired;
    }
}
