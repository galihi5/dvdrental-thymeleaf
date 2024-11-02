package com.gaw.dvdrental.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String actorId;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private LocalDateTime lastUpdate;

    private String lastUpdateStr;

    public void setLastUpdateStr(){
        this.lastUpdateStr = lastUpdate.format(FORMATTER);
    }

    public void setLastUpdate(){
        if (!Objects.isNull(lastUpdateStr)) {
            this.lastUpdate = LocalDateTime.parse(lastUpdateStr, FORMATTER);
        }
    }

}
