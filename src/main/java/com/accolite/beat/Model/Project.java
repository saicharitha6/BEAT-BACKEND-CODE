package com.accolite.beat.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.w3c.dom.Text;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class Project {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String organization;
    @NonNull
    private String project_manager;
    @NonNull
    private Date start_date;
    @NonNull
    private String location;
    @Nullable
    private Date end_date;
    @Nullable
    private String description;
    @NonNull
    private String project_type;
    @NonNull
    private long editor_id;
}
