package org.accolite.buisnesslogic;

public class Access{
    private static Access accessInstance = null;
    public String access;
    private Access() {access=null;}
    public static Access getInstance()
    {
        if (accessInstance == null)
            accessInstance = new Access();
        return accessInstance;
    }
}

