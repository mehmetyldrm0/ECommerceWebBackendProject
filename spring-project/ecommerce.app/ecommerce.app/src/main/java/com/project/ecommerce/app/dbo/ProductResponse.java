package com.project.ecommerce.app.dbo;


import com.project.ecommerce.app.entities.ProductPriceHistory;
import com.project.ecommerce.app.entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String productName;

    private double price;

    private List<ProductPriceHistory> priceHistory;

    private Stock stock;
}
