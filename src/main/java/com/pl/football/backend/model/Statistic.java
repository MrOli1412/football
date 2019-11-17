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


    private Integer redCards;
    private Integer yellowCards;
    private Integer goals;
    private Integer assist;


}
