package com.pl.football.backend.dto.player;

import com.pl.football.backend.model.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlayerFullDataDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String evidentialNumber;

    @NotNull
    private LocalDate birthDay;

    @NotNull
    private LocalDate contractDate;

    private String amateur;

    private String lastClub;

    private TransferType transferType;

    private Integer dressNumber;

    public void setTransferType(String value) {
        this.transferType = TransferType.valueOf(value);
    }
}
