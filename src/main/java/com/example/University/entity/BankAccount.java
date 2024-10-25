package com.example.University.entity;

import com.example.University.BaseEntityRequirement.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
public class BankAccount extends AbstractBaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String phoneNumber;

  private LocalDate dateOfBirth;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(columnDefinition = "jsonb")
  private Map<String, Object> address;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
