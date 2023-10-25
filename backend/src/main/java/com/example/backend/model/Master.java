package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE masters SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "masters")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "cover_image", nullable = false)
    private String coverImage;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "phone_number")
    private String phoneNumber;
}
