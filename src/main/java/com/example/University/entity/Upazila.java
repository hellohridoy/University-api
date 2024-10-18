package com.example.University.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Upazila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String upazilaName;

    private String upazilaNumber;

    private String upazilaCode;

    private Integer sangsadAsonNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zila_id")
    @JsonBackReference
    private Zila zila;

}
