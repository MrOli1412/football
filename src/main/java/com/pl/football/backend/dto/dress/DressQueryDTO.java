package com.pl.football.backend.dto.dress;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DressQueryDTO {
    @NotNull
    private String color;
    String allowedNumbers;
}
