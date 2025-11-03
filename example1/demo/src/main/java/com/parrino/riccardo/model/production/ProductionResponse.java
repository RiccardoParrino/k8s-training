package com.parrino.riccardo.model.production;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionResponse {
    private Long productionRequestId;
    private Integer state; // in coda:0, in lavorazione:1, terminato:2
}
