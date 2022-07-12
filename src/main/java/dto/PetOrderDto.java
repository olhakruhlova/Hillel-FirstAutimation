package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

    @Data
    @Getter
    @Builder
    @Jacksonized
    @JsonIgnoreProperties(ignoreUnknown = true)

    public class PetOrderDto {
        private long id;
        private long petId;
        private int quantity;
        private String shipDate;
        private String status;
        private boolean complete;
}
