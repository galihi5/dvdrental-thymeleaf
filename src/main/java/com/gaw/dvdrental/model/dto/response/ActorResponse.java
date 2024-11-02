package com.gaw.dvdrental.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponse {
    int totalPages;
    long totalElements;
    int currentPage; //page number, index starts with 0
    int size;
    boolean first;
    boolean last;
    int numberOfElements;
    boolean empty;

    List<Actor> data;

    @Data
    public class Actor {
        private String actorId;
        private String firstName;
        private String lastName;

        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
        private LocalDateTime lastUpdate;

        /* public void setLastUpdate(){
            if (!Objects.isNull(lastUpdateStr)) {
                this.lastUpdate = LocalDateTime.parse(lastUpdateStr, FORMATTER);
            }
        } */
    }
}
