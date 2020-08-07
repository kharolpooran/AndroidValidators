package com.appic.androidvalidators.model;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Password {
    public static String TAG = Password.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public static class PasswordBuilder {
        private String userPassword; //This is important, so we'll pass it to the constructor.
        private String confirmPassword;
        private int minValue;
        private int maxValue;
        private boolean isSpecialCharacterRequired = false;
        private boolean isNumberRequired = false;
        private boolean isRequired = false;

        public PasswordBuilder(String value) {
            this.userPassword = value;
        }

        public Password.PasswordBuilder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Password.PasswordBuilder setMinValue(int minValue) {
            this.minValue = minValue;
            return this;
        }

        public Password.PasswordBuilder setMaxValue(int maxValue) {
            this.maxValue = maxValue;
            return this;
        }

        public Password.PasswordBuilder setSpecialCharacterRequired(boolean isSpecialCharacterRequired) {
            this.isSpecialCharacterRequired = isSpecialCharacterRequired;
            return this;
        }

        public Password.PasswordBuilder setNumbersOnly(boolean isNumberRequired) {
            this.isNumberRequired = isNumberRequired;
            return this;
        }

        public Password.PasswordBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> passwordValidationResponse = new HashMap<>();

            Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
            Pattern lowerCasePatten = Pattern.compile("[a-z ]");
            Pattern digitCasePatten = Pattern.compile("[0-9 ]");
            boolean isSuccess = true;
            if (this.isRequired) {
                if (this.userPassword.length() != 0) {
                    if (this.minValue != 0 && this.maxValue != 0) {
                        if (this.userPassword.length() > this.minValue && this.userPassword.length() < this.maxValue) {

                            if (this.isSpecialCharacterRequired) {
                                if (specailCharPatten.matcher(this.userPassword).find()) {
                                    Log.e(TAG, "Not Special Char");
                                } else {
                                    isSuccess = false;
                                }
                            }

                            if (this.isNumberRequired) {
                                if (!digitCasePatten.matcher(this.userPassword).find()) {
                                    //false
                                    Log.e(TAG, "Not Digit");
                                } else {
                                    isSuccess = false;
                                }
                            }

                            if (this.isNumberRequired) {
                                if (!digitCasePatten.matcher(this.userPassword).find()) {
                                    //false
                                    Log.e(TAG, "Not Digit");
                                } else {
                                    isSuccess = false;
                                }
                            }

                            if (!this.userPassword.equals(this.confirmPassword)) {
                                Log.e(TAG, "Not Same Password");
                            } else {
                                isSuccess = false;
                            }

                        } else {
                            isSuccess = false;
                        }
                    } else {
                        if (this.isSpecialCharacterRequired) {
                            if (!specailCharPatten.matcher(this.userPassword).find()) {
                                //false
                                Log.e(TAG, "Not Special Char");
                            }
                        } else if (this.isNumberRequired) {
                            if (!digitCasePatten.matcher(this.userPassword).find()) {
                                //false
                                Log.e(TAG, "Not Digit");
                            }
                        } else {
                            if (!this.userPassword.equals(this.confirmPassword)) {
                                //false
                                Log.e(TAG, "Not Same Password");
                            }
                        }
                    }
                } else {
                    //Empty String
                    Log.e(TAG, "Empty String");
                }
            }
            return passwordValidationResponse;
        }

    }

    private Password() {
    }
}
