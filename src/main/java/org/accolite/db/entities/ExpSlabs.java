package org.accolite.db.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="EXP_SLABS")
public class ExpSlabs {
    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    private String slab;
}
