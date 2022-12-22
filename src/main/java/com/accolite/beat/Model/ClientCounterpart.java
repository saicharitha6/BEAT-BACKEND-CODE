package com.accolite.beat.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="CLIENT_COUNTERPART")
public class ClientCounterpart {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    private String name;

    private long clientId;

    private String orgId;
}

