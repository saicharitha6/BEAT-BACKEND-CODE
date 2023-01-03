package org.accolite.pojo;

import lombok.Data;

@Data
public class LoginResponse {
    private  long empId;
    private  String jwt;
    private  String accessId;

}
