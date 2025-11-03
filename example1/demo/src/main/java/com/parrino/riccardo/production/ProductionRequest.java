package com.parrino.riccardo.production;

import java.util.List;

import com.parrino.riccardo.model.Product;

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
public class ProductionRequest {
    private Long productionRequestId;
    private List<Product> productList;
}
