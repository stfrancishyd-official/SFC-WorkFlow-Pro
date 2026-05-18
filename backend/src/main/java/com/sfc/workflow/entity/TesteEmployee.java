package com.sfc.workflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesteEmployee {


    @Id
    private Long id;

    private String name;
}
