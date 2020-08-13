package com.appic.androidvalidators.model;

import com.appic.androidvalidators.interfaces.ErrorCallBack;

import java.util.HashMap;

public class Username {
    public static String TAG = Username.class.getSimpleName();
    private String value = ""; //This is important, so we'll pass it to the constructor.
    private boolean isRequired = false;
    private boolean isCaseSensitive = false;

    public String getValue() {
        return value;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    //Static Response Code.
    public static String SUCCESS = "Success";
    public static String IS_CASE_SENSITIVE = "CaseSensitive";
    public static String IS_REQUIRED = "IsRequired";
    public static String IS_ALL_LOWER_CASE = "IsAllLowerCase";
    public static String EMPTY = "Empty";
    private ErrorCallBack errorCallBack;

    public static class UsernameBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        private boolean isCaseSensitive = false;

        public UsernameBuilder(String value) {
            this.value = value.trim();
        }

        public Username.UsernameBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public Username.UsernameBuilder setCaseSensitive(boolean isCaseSensitive) {
            this.isCaseSensitive = isCaseSensitive;
            return this;
        }

        public HashMap<String, Boolean> build() {
            HashMap<String, Boolean> username = new HashMap<>();

            if (this.isRequired) {
                username.put(IS_REQUIRED, true);
            } else {
                username.put(IS_REQUIRED, false);
            }
            if (this.value != null && !this.value.isEmpty()) {
                username.put(EMPTY, false);
                if (this.isCaseSensitive) {
                    username.put(IS_CASE_SENSITIVE, true);
                    if (isStringLowerCase(this.value)) {
                        username.put(SUCCESS, true);
                        username.put(IS_ALL_LOWER_CASE, true);
                    } else {
                        username.put(SUCCESS, false);
                        username.put(IS_ALL_LOWER_CASE, false);
                    }
                } else {
                    username.put(IS_CASE_SENSITIVE, false);
                    username.put(IS_ALL_LOWER_CASE, false);
                    username.put(SUCCESS, false);
                }
            } else {
                username.put(EMPTY, true);
                username.put(SUCCESS, false);
                username.put(IS_CASE_SENSITIVE, false);
                username.put(IS_ALL_LOWER_CASE, false);
            }

            return username;
        }

    }

    private static boolean isStringLowerCase(String str) {

        //convert String to char array
        char[] charArray = str.toCharArray();

        for (char c : charArray) {
            //if any character is not in lower case, return false
            if (!Character.isLowerCase(c))
                return false;
        }

        return true;

    }

    public Username(ErrorCallBack errorCallBack) {
        this.errorCallBack = errorCallBack;
    }

    public boolean isValid(HashMap<String, Boolean> hashMap) {

        if (hashMap.get(Username.SUCCESS)) {
            return true;
        } else {
            if (hashMap.get(Username.IS_REQUIRED)) {
                if (!hashMap.get(Username.EMPTY)) {
                    if (hashMap.get(Username.IS_CASE_SENSITIVE)) {
                        if (!hashMap.get(Username.IS_ALL_LOWER_CASE)) {
                            errorCallBack.onError("username should be in lower case");
                            return false;
                        }
                    }
                } else {
                    errorCallBack.onError("username is should not be empty");
                    return false;
                }
            } else {
                if (!hashMap.get(Username.EMPTY)) {
                    if (hashMap.get(Username.IS_CASE_SENSITIVE)) {
                        if (!hashMap.get(Username.IS_ALL_LOWER_CASE)) {
                            errorCallBack.onError("Not Valid username");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private Username(Username.UsernameBuilder usernameBuilder) {
        this.value = usernameBuilder.value;
        this.isRequired = usernameBuilder.isRequired;
        this.isCaseSensitive = usernameBuilder.isCaseSensitive;
    }
}
