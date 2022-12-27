package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="SLAB_CHARGES")
public class SlabCharges {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long slabId;

    private long orgId;

    private long cost;

    private boolean status;

    private long editorId;
}
