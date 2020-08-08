package com.appic.androidvalidators.model;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Password {
    public static String TAG = Password.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    private boolean compare_password = false;
    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String IS_EMAIL = "IsEmail";
    public static String IS_REQUIRED = "IsRequired";
    public static String EMPTY = "Empty";
    public static String RANGE = "Range";
    public static String SPECIAL_CHARACTER = "SpecialCharacter";
    public static String SPECIAL_CHARACTER_REQUIRED = "SPECIAL_CHARACTER_REQUIRED";
    public static String NUMBER = "Number";
    public static String NUMBER_REQUIRED = "NUMBER_REQUIRED";
    public static String UPPER_CASE = "UpperCase";
    public static String UPPER_CASE_REQUIRED = "UPPER_CASE_REQUIRED";
    public static String LOWER_CASE = "LowerCase";
    public static String LOWER_CASE_REQUIRED = "LOWER_CASE_REQUIRED";
    public static String CONFIRM_PASSWORD = "ConfirmPassword";
    public static String MATCH_PASSWORD = "MatchPassword";

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean getComparePassword() {
        return compare_password;
    }

    public static class PasswordBuilder {
        private String userPassword; //This is important, so we'll pass it to the constructor.
        private String confirmPassword;
        private int minValue = 0;
        private int maxValue = 0;
        private boolean isSpecialCharacterRequired = false;
        private boolean isNumberRequired = false;
        private boolean isUppercaseRequired = false;
        private boolean isLowerCaseRequired = false;
        private boolean isRequired = false;
        private boolean compare_password = false;

        public PasswordBuilder(String value) {
            this.userPassword = value;
        }

        public Password.PasswordBuilder setConfirmPassword(String confirmPassword) {
            this.confirmPassword = confirmPassword;
            return this;
        }

        public Password.PasswordBuilder comparePassword(boolean compare_password) {
            this.compare_password = compare_password;
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

        public Password.PasswordBuilder setUpperCase(boolean isUppercaseRequired) {
            this.isUppercaseRequired = isUppercaseRequired;
            return this;
        }

        public Password.PasswordBuilder setLowerCase(boolean isLowerCaseRequired) {
            this.isLowerCaseRequired = isLowerCaseRequired;
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
            Pattern digitCasePatten = Pattern.compile("[0-9]+");

            boolean isSuccess = true;

            if (this.isRequired) {
                passwordValidationResponse.put(IS_REQUIRED, true);
                if (this.userPassword != null && !this.userPassword.isEmpty()) {
                    passwordValidationResponse.put(EMPTY, false);
                    if (maxValue == 0 && minValue == 0) {
                        passwordValidationResponse.put(RANGE, true);
                        if (this.isSpecialCharacterRequired) {
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                            if (specailCharPatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                            } else {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                        }

                        if (this.isNumberRequired) {
                            passwordValidationResponse.put(NUMBER_REQUIRED, true);
                            if (digitCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(NUMBER, true);
                            } else {
                                passwordValidationResponse.put(NUMBER, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(NUMBER_REQUIRED, false);
                        }


                        if (this.isUppercaseRequired) {
                            passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                            if (UpperCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(UPPER_CASE, true);
                            } else {
                                passwordValidationResponse.put(UPPER_CASE, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(UPPER_CASE_REQUIRED, false);
                        }

                        if (this.isLowerCaseRequired) {
                            passwordValidationResponse.put(LOWER_CASE_REQUIRED, true);
                            if (lowerCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(LOWER_CASE, true);
                            } else {
                                passwordValidationResponse.put(LOWER_CASE, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                        }

                        if (this.compare_password) {
                            passwordValidationResponse.put(CONFIRM_PASSWORD, true);
                            if (this.confirmPassword != null && !this.confirmPassword.isEmpty()) {
                                if (confirmPassword.equals(userPassword)) {
                                    passwordValidationResponse.put(MATCH_PASSWORD, true);
                                } else {
                                    passwordValidationResponse.put(MATCH_PASSWORD, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(MATCH_PASSWORD, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                        }
                    } else {
                        if (this.userPassword.length() >= minValue && this.userPassword.length() <= maxValue) {
                            passwordValidationResponse.put(RANGE, true);
                            if (this.isSpecialCharacterRequired) {
                                passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                                if (specailCharPatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                                } else {
                                    passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                            }

                            if (this.isNumberRequired) {
                                String regex = "\\d+";
                                passwordValidationResponse.put(NUMBER_REQUIRED, true);
                                if (this.userPassword.matches(regex)) {
                                    passwordValidationResponse.put(NUMBER, true);
                                } else {
                                    passwordValidationResponse.put(NUMBER, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(NUMBER_REQUIRED, false);
                            }


                            if (this.isUppercaseRequired) {
                                passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                                if (UpperCasePatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(UPPER_CASE, true);
                                } else {
                                    passwordValidationResponse.put(UPPER_CASE, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(UPPER_CASE_REQUIRED, false);
                            }

                            if (this.isLowerCaseRequired) {
                                passwordValidationResponse.put(LOWER_CASE_REQUIRED, true);
                                if (lowerCasePatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(LOWER_CASE, true);
                                } else {
                                    passwordValidationResponse.put(LOWER_CASE, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                            }

                            if (this.compare_password) {
                                passwordValidationResponse.put(CONFIRM_PASSWORD, true);
                                if (this.confirmPassword != null && !this.confirmPassword.isEmpty()) {
                                    if (confirmPassword.equals(userPassword)) {
                                        passwordValidationResponse.put(MATCH_PASSWORD, true);
                                    } else {
                                        passwordValidationResponse.put(MATCH_PASSWORD, false);
                                        isSuccess = false;
                                    }
                                } else {
                                    passwordValidationResponse.put(MATCH_PASSWORD, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                            }

                        } else {
                            isSuccess = false;
                            passwordValidationResponse.put(RANGE, false);
                        }
                    }
                } else {
                    isSuccess = false;
                    passwordValidationResponse.put(EMPTY, true);
                }
            } else {
                passwordValidationResponse.put(IS_REQUIRED, false);
                if (this.userPassword != null && !this.userPassword.isEmpty()) {
                    passwordValidationResponse.put(EMPTY, false);
                    if (maxValue == 0 && minValue == 0) {
                        passwordValidationResponse.put(RANGE, true);
                        if (this.isSpecialCharacterRequired) {
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                            if (specailCharPatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                            } else {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                        }

                        if (this.isNumberRequired) {
                            passwordValidationResponse.put(NUMBER_REQUIRED, true);
                            if (digitCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(NUMBER, true);
                            } else {
                                passwordValidationResponse.put(NUMBER, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(NUMBER_REQUIRED, false);
                        }

                        if (this.isUppercaseRequired) {
                            passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                            if (UpperCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(UPPER_CASE, true);
                            } else {
                                passwordValidationResponse.put(UPPER_CASE, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(UPPER_CASE_REQUIRED, false);
                        }

                        if (this.isLowerCaseRequired) {
                            passwordValidationResponse.put(LOWER_CASE_REQUIRED, true);
                            if (lowerCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(LOWER_CASE, true);
                            } else {
                                passwordValidationResponse.put(LOWER_CASE, false);
                                isSuccess = false;
                            }
                        }
                        else {
                            passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                        }

                        if (this.compare_password) {
                            passwordValidationResponse.put(CONFIRM_PASSWORD, true);
                            if (this.confirmPassword != null && !this.confirmPassword.isEmpty()) {
                                if (confirmPassword.equals(userPassword)) {
                                    passwordValidationResponse.put(MATCH_PASSWORD, true);
                                } else {
                                    passwordValidationResponse.put(MATCH_PASSWORD, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(MATCH_PASSWORD, false);
                                isSuccess = false;
                            }
                        }
                        else {
                            passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                        }
                    } else {
                        if (this.userPassword.length() >= minValue && this.userPassword.length() <= maxValue) {
                            passwordValidationResponse.put(RANGE, true);
                            if (this.isSpecialCharacterRequired) {
                                passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                                if (specailCharPatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                                } else {
                                    passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                                    isSuccess = false;
                                }
                            }
                            else {
                                passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                            }

                            if (this.isNumberRequired) {
                                passwordValidationResponse.put(NUMBER_REQUIRED, true);
                                if (digitCasePatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(NUMBER, true);
                                } else {
                                    passwordValidationResponse.put(NUMBER, false);
                                    isSuccess = false;
                                }
                            }
                            else {
                                passwordValidationResponse.put(NUMBER_REQUIRED, false);
                            }

                            if (this.isUppercaseRequired) {
                                passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                                if (UpperCasePatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(UPPER_CASE, true);
                                } else {
                                    passwordValidationResponse.put(UPPER_CASE, false);
                                    isSuccess = false;
                                }
                            }
                            else {
                                passwordValidationResponse.put(UPPER_CASE_REQUIRED, false);
                            }

                            if (this.isLowerCaseRequired) {
                                passwordValidationResponse.put(LOWER_CASE_REQUIRED, true);
                                if (lowerCasePatten.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(LOWER_CASE, true);
                                } else {
                                    passwordValidationResponse.put(LOWER_CASE, false);
                                    isSuccess = false;
                                }
                            }
                            else {
                                passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                            }

                            if (this.compare_password) {
                                passwordValidationResponse.put(CONFIRM_PASSWORD, true);
                                if (this.confirmPassword != null && !this.confirmPassword.isEmpty()) {
                                    if (confirmPassword.equals(userPassword)) {
                                        passwordValidationResponse.put(MATCH_PASSWORD, true);
                                    } else {
                                        passwordValidationResponse.put(MATCH_PASSWORD, false);
                                        isSuccess = false;
                                    }
                                } else {
                                    passwordValidationResponse.put(MATCH_PASSWORD, false);
                                    isSuccess = false;
                                }
                            }
                            else {
                                passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                            }

                        } else {
                            isSuccess = false;
                            passwordValidationResponse.put(RANGE, false);
                        }
                    }
                } else {
                    passwordValidationResponse.put(EMPTY, false);
                }
            }
            passwordValidationResponse.put(SUCCESS, isSuccess);
            return passwordValidationResponse;
        }

    }

    private Password(Password.PasswordBuilder passwordBuilder) {
        this.compare_password = passwordBuilder.compare_password;
    }
}
