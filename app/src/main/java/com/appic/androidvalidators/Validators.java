package com.appic.androidvalidators;

import java.util.HashMap;
import java.util.Map;

public class Validators {


    Map<Class<?>, Object> values = new HashMap<>();

    public <T> void put(Class<T> key, HashMap<String, Boolean> value ) {
        values.put( key, value );
    }

    public <T> T get( Class<T> key ) {
        return key.cast( values.get( key ) );
    }




    private boolean isValid() {



        return true;
    }

    /*public class Key<T> {

        final String identifier;
        final Class<T> type;

        public Key( String identifier, Class<T> type ) {
            this.identifier = identifier;
            this.type = type;
        }
    }*/

}
