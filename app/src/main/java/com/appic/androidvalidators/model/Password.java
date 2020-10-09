package com.appic.androidvalidators.model;

import com.appic.androidvalidators.interfaces.ErrorCallBack;

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
    public static String MAX_RANGE = "Max_Range";
    public static String MIN_RANGE = "Min_Range";
    //public static String SPECIAL_CHARACTER = "SpecialCharacter";
    //public static String SPECIAL_CHARACTER_REQUIRED = "SPECIAL_CHARACTER_REQUIRED";
    public static String NUMBER = "Number";
    public static String NUMBER_REQUIRED = "NUMBER_REQUIRED";
    //public static String UPPER_CASE = "UpperCase";
    //public static String UPPER_CASE_REQUIRED = "UPPER_CASE_REQUIRED";
    //public static String LOWER_CASE = "LowerCase";
    //public static String LOWER_CASE_REQUIRED = "LOWER_CASE_REQUIRED";
    public static String CONFIRM_PASSWORD = "ConfirmPassword";
    public static String MATCH_PASSWORD = "MatchPassword";
    public static String USE_PREDEFINED_PATTERN = "USE_PREDEFINED_PATTERN";
    public static String PREDEFINED_PATTERN = "PREDEFINED_PATTERN";
    private ErrorCallBack errorCallBack;

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
        private String confirmPassword = "";
        public int minValue = 0;
        private int maxValue = 0;
        //private boolean isSpecialCharacterRequired = false;
        private boolean isNumberRequired = false;
        //private boolean isUppercaseRequired = false;
        //private boolean isLowerCaseRequired = false;
        private boolean isRequired = true;
        private boolean usePreDefinedPattern = true;
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

        public int getMaxValue() {

            return this.maxValue;
        }

        public int getMinValue() {

            return this.minValue;
        }

        /*public Password.PasswordBuilder usePreDefinePattern(boolean usePreDefinedPattern) {
            this.usePreDefinedPattern = usePreDefinedPattern;
            return this;
        }*/

        /*public Password.PasswordBuilder setSpecialCharacterRequired(boolean isSpecialCharacterRequired) {
            this.isSpecialCharacterRequired = isSpecialCharacterRequired;
            return this;
        }*/

        public Password.PasswordBuilder setNumbersOnly(boolean isNumberRequired) {
            this.isNumberRequired = isNumberRequired;
            return this;
        }

        /*public Password.PasswordBuilder setUpperCase(boolean isUppercaseRequired) {
            this.isUppercaseRequired = isUppercaseRequired;
            return this;
        }

        public Password.PasswordBuilder setLowerCase(boolean isLowerCaseRequired) {
            this.isLowerCaseRequired = isLowerCaseRequired;
            return this;
        }*/

        /*public Password.PasswordBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }*/

        public HashMap<String, Object> build() {
            HashMap<String, Object> passwordValidationResponse = new HashMap<>();

            if (minValue == 0) {
                minValue = 6;
            }
            if (maxValue == 0) {
                maxValue = 8;
            }

            Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
            Pattern lowerCasePatten = Pattern.compile("[a-z ]");
            Pattern digitCasePatten = Pattern.compile("[0-9]+" + "(?=\\\\S+$)");
            // Regex to check valid password.
            String regexComb = "^(?=.*[0-9])"
                    + "(?=.*[a-z])(?=.*[A-Z])"
                    + "(?=.*[@#$%^&+=])"
                    + "(?=\\S+$).{" + minValue + "," + maxValue + "}$";
            Pattern combination = Pattern.compile(regexComb);


            boolean isSuccess = true;

            if (this.isRequired) {
                passwordValidationResponse.put(IS_REQUIRED, true);
            }/* else {
                passwordValidationResponse.put(IS_REQUIRED, false);
            }*/

            if (this.userPassword != null && !this.userPassword.isEmpty()) {
                passwordValidationResponse.put(EMPTY, false);
                if (maxValue == 0 && minValue == 0) {
                    passwordValidationResponse.put(RANGE, true);
                    /*if (this.isSpecialCharacterRequired) {
                        passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                        if (specailCharPatten.matcher(this.userPassword).find()) {
                            passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                        } else {
                            passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                            isSuccess = false;
                        }
                    } else {
                        passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                        passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                    }*/
                    if (!this.isNumberRequired) {
                        if (this.usePreDefinedPattern) {
                            passwordValidationResponse.put(USE_PREDEFINED_PATTERN, true);
                            if (combination.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(PREDEFINED_PATTERN, true);
                            } else {
                                passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                            passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                        }
                    } else {
                        passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                        passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                    }

                    if (this.isNumberRequired) {
                        passwordValidationResponse.put(NUMBER_REQUIRED, true);
                        if (digitCasePatten.matcher(this.userPassword).find()) {
                            passwordValidationResponse.put(NUMBER, true);
                            passwordValidationResponse.put(MAX_RANGE, maxValue);
                            passwordValidationResponse.put(MIN_RANGE, minValue);
                        } else {
                            passwordValidationResponse.put(NUMBER, false);
                            isSuccess = false;
                        }
                    } else {
                        passwordValidationResponse.put(NUMBER, false);
                        passwordValidationResponse.put(NUMBER_REQUIRED, false);
                    }


                    /*if (this.isUppercaseRequired) {
                        passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                        if (UpperCasePatten.matcher(this.userPassword).find()) {
                            passwordValidationResponse.put(UPPER_CASE, true);
                        } else {
                            passwordValidationResponse.put(UPPER_CASE, false);
                            isSuccess = false;
                        }
                    } else {
                        passwordValidationResponse.put(UPPER_CASE, false);
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
                        passwordValidationResponse.put(LOWER_CASE, false);
                        passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                    }*/

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
                        passwordValidationResponse.put(MATCH_PASSWORD, false);
                        passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                    }
                } else {
                    if (this.userPassword.length() >= minValue && this.userPassword.length() <= maxValue) {
                        passwordValidationResponse.put(RANGE, true);
                        passwordValidationResponse.put(MAX_RANGE, maxValue);
                        passwordValidationResponse.put(MIN_RANGE, minValue);
                        /*if (this.isSpecialCharacterRequired) {
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, true);
                            if (specailCharPatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, true);
                            } else {
                                passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                            passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                        }*/
                        if (!this.isNumberRequired) {
                            if (this.usePreDefinedPattern) {
                                passwordValidationResponse.put(USE_PREDEFINED_PATTERN, true);
                                if (combination.matcher(this.userPassword).find()) {
                                    passwordValidationResponse.put(PREDEFINED_PATTERN, true);
                                } else {
                                    passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                                    isSuccess = false;
                                }
                            } else {
                                passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                                passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                            }
                        } else {
                            passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                            passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                            passwordValidationResponse.put(MAX_RANGE, maxValue);
                            passwordValidationResponse.put(MIN_RANGE, minValue);
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
                            passwordValidationResponse.put(NUMBER, false);
                            passwordValidationResponse.put(NUMBER_REQUIRED, false);
                        }


                        /*if (this.isUppercaseRequired) {
                            passwordValidationResponse.put(UPPER_CASE_REQUIRED, true);
                            if (UpperCasePatten.matcher(this.userPassword).find()) {
                                passwordValidationResponse.put(UPPER_CASE, true);
                            } else {
                                passwordValidationResponse.put(UPPER_CASE, false);
                                isSuccess = false;
                            }
                        } else {
                            passwordValidationResponse.put(UPPER_CASE, false);
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
                            passwordValidationResponse.put(LOWER_CASE, false);
                            passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
                        }*/

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
                            passwordValidationResponse.put(MATCH_PASSWORD, false);
                            passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                        }

                    } else {
                        isSuccess = false;
                        passwordValidationResponse.put(RANGE, false);
                        passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                        passwordValidationResponse.put(MATCH_PASSWORD, false);
                        passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                        passwordValidationResponse.put(PREDEFINED_PATTERN, false);
                        passwordValidationResponse.put(NUMBER_REQUIRED, false);
                        passwordValidationResponse.put(NUMBER, false);
                        passwordValidationResponse.put(EMPTY, false);
                        passwordValidationResponse.put(MAX_RANGE, maxValue);
                        passwordValidationResponse.put(MIN_RANGE, minValue);
                    }
                }
            } else {
                isSuccess = false;
                passwordValidationResponse.put(EMPTY, true);
                passwordValidationResponse.put(RANGE, false);
                //passwordValidationResponse.put(SPECIAL_CHARACTER_REQUIRED, false);
                //passwordValidationResponse.put(SPECIAL_CHARACTER, false);
                passwordValidationResponse.put(NUMBER_REQUIRED, false);
                passwordValidationResponse.put(NUMBER, false);
                //passwordValidationResponse.put(UPPER_CASE_REQUIRED, false);
//                passwordValidationResponse.put(UPPER_CASE, false);
//                passwordValidationResponse.put(LOWER_CASE_REQUIRED, false);
//                passwordValidationResponse.put(LOWER_CASE, false);
                passwordValidationResponse.put(CONFIRM_PASSWORD, false);
                passwordValidationResponse.put(MATCH_PASSWORD, false);
                passwordValidationResponse.put(USE_PREDEFINED_PATTERN, false);
                passwordValidationResponse.put(PREDEFINED_PATTERN, false);

            }
            passwordValidationResponse.put(SUCCESS, isSuccess);
            return passwordValidationResponse;
        }

    }

    public Password(ErrorCallBack errorCallBack) {
        this.errorCallBack = errorCallBack;
    }

    public boolean isValid(HashMap<String, Object> hashMap) {

        if ((boolean) hashMap.get(Password.SUCCESS)) {
            return true;
        } else {
            if ((boolean) hashMap.get(Password.IS_REQUIRED)) {
                if (!(boolean) hashMap.get(Password.EMPTY)) {
                    if ((boolean) hashMap.get(Password.USE_PREDEFINED_PATTERN)) {
                        if (!(boolean) hashMap.get(Password.PREDEFINED_PATTERN)) {

                            errorCallBack.onValidationError("Password should contains at least one digits, one upper case alphabet, one lower case alphabet, one special characters, and length " + hashMap.get(Password.MIN_RANGE) + " to " + hashMap.get(Password.MAX_RANGE) + " characters and no whitespace are allowed.");
                            return false;
                        }
                        if ((boolean) hashMap.get(Password.CONFIRM_PASSWORD)) {
                            if (!(boolean) hashMap.get(Password.MATCH_PASSWORD)) {
                                errorCallBack.onValidationError("Password and Confirm Password doesn't match.");
                                return false;
                            }
                        }
                    } else {
                        if ((boolean) hashMap.get(Password.RANGE)) {
                            if ((boolean) hashMap.get(Password.NUMBER_REQUIRED)) {//true or false
                                if (!(boolean) hashMap.get(Password.NUMBER)) {
                                    errorCallBack.onValidationError("Password should contain number only and no whitespace are allowed.");
                                    return false;
                                }
                            }
                            if ((boolean) hashMap.get(Password.CONFIRM_PASSWORD)) {
                                if (!(boolean) hashMap.get(Password.MATCH_PASSWORD)) {
                                    errorCallBack.onValidationError("Password and Confirm Password doesn't match.");
                                    return false;
                                }
                            }
                        } else {
                            errorCallBack.onValidationError("Password length should be "  + hashMap.get(Password.MIN_RANGE) + " to " + hashMap.get(Password.MAX_RANGE) +  " characters");
                            return false;
                        }
                    }
                } else {
                    errorCallBack.onValidationError("Password should not be empty.");
                    return false;
                }
            } else {
                errorCallBack.onValidationError("Password is required.");
                return false;
            }
        }
        return true;
    }

    private Password(Password.PasswordBuilder passwordBuilder) {
        this.compare_password = passwordBuilder.compare_password;
    }
}
