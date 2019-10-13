package com.pl.football.backend.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Embeddable
public class Statistic implements Serializable {


    @Pattern(regexp = "^[0-9]*$")
    private Integer redCards;
    @Pattern(regexp = "^[0-9]*$")
    private Integer yellowCards;
    @Pattern(regexp = "^[0-9]*$")
    private Integer goals;
    @Pattern(regexp = "^[0-9]*$")
    private Integer assist;


}
