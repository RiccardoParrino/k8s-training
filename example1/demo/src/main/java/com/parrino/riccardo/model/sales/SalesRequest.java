package com.parrino.riccardo.model.sales;

import java.util.List;

import com.parrino.riccardo.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequest {
    private Long requestId;
    private List<Product> products;
}
