package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="LOGIN")
public class Login {

    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    private long empId;

    private String name;

    private String email;

    private String password;

    private long accessId;


}


