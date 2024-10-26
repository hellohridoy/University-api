package com.example.University.entity.joining;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

}
