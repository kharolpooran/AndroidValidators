package com.appic.androidvalidators.model;

import android.util.Log;

public class Username {
    public static String TAG=Username.class.getSimpleName();
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

    public static class UsernameBuilder {
        private String value; //This is important, so we'll pass it to the constructor.
        private boolean isRequired = false;
        private boolean isCaseSensitive = false;

        public UsernameBuilder(String value) {
            this.value = value;
        }

        public Username.UsernameBuilder setRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        public Username.UsernameBuilder setCaseSensitive(boolean isCaseSensitive) {
            this.isCaseSensitive = isCaseSensitive;
            return this;
        }

        public Username build() {
            Username username = new Username(this);
            if (this.isRequired) {
                if (this.isCaseSensitive) {
                    if (isStringLowerCase(this.value)) {
                        return username;
                    } else {
                        Log.e(TAG,"Not CaseSensitive.");
                    }
                } else {
                    Log.e(TAG,"Not CaseSensitive.");
                }
            } else {
                return username;
            }
            return null;
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

    private Username(Username.UsernameBuilder usernameBuilder) {
        this.value = usernameBuilder.value;
        this.isRequired = usernameBuilder.isRequired;
        this.isCaseSensitive = usernameBuilder.isCaseSensitive;
    }
}
