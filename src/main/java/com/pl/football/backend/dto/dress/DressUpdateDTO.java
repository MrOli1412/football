package com.pl.football.backend.dto.dress;

import lombok.Data;

@Data
public class DressUpdateDTO {

    private String color;
    Integer[] allowedNumbers;
}
