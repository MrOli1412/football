package com.pl.football.backend.exception;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Data
public class FootballException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public FootballException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public FootballException(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballException)) return false;
        FootballException that = (FootballException) o;
        return Objects.equals(errCode, that.errCode) &&
                Objects.equals(errMsg, that.errMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errCode, errMsg);
    }
}
