package com.amsidh.mvc.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Service1 {
    @GeneratedValue
    @Id
    private Integer service1Id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String service1Message;
}
