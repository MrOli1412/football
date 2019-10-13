package com.pl.football.backend.exception;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
public class FootballException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public FootballException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
