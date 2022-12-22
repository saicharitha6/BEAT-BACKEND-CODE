package com.accolite.beat.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class SlabCharges {
    @Id @NonNull @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NonNull
    private long slabId;

    @NonNull
    private long orgId;

    @NonNull
    private long cost;

    @NonNull
    private boolean status;

    @NonNull
    private long editorId;
}
