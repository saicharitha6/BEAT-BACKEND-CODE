package org.accolite.controllers;

public final class PathConstants  {

    // Suppress default constructor for noninstantiability
    private PathConstants() {
        throw new AssertionError("No instances for you!");
    }

    //login paths
    public static final String ApiPath = "/beat/api/v1";
    public static final String loginPath = "/login/user";
    public static final String accessPath = "/logged/access";

    public static final String employeePath = "/beat/api/v1/employee";
    public static final String organizationPath = "/beat/api/v1/organization";
    public static final String projectPath = "/beat/api/v1/project";

    public static final String createPath = "/create";
    public static final String updatePath = "/update";
    public static final String getPath = "/get/{id}";
    public static final String disablePath = "/disable";
    public static final String getById = "/get/{id}";
    public static final String getHierarchy = "/getHierarchy/{id}";
    public static final String search = "/search/{name}";
}