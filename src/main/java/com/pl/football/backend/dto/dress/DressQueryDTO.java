package com.pl.football.backend.dto.dress;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class DressQueryDTO {
    private UUID id;
    @NotNull
    private String color;
    private String allowedNumbers;
}
