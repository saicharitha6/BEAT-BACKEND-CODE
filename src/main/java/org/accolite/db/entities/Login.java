package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="LOGIN")
public class Login {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long empId;

    private String username;

    private String email;

    private String password;

    private long accessId;
}