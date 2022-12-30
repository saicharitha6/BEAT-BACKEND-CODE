package org.accolite.controllers;

public final class PathConstants  {

    // Suppress default constructor for noninstantiability
    private PathConstants() {
        throw new AssertionError("No instances for you!");
    }

    //login paths
    public static final String ApiPath = "/api/v1";
    public static final String loginPath = "/login/user";
    public static final String accessPath = "/logged/access";

}