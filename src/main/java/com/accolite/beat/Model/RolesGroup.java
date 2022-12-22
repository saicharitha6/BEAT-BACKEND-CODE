package com.accolite.beat.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class RolesGroup {
    @Id @NonNull @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;

    @Nullable
    private String access;
}
