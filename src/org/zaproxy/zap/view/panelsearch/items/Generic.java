package org.zaproxy.zap.view.panelsearch.items;

public final class Generic {

    public static <T> T cast(Object component){
        T casted;
        try{
            casted  = (T)component;
        }catch (ClassCastException ex){
            return null;
        }
        return casted;
    }
}
