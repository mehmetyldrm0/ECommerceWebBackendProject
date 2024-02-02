package com.project.ecommerce.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "cart_item")
public class CartItem extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    @Transient
    @Column(table = "getTotalPrice")
    public double getTotalPrice() {
        if (product != null && quantity != null) {
            return product.getPrice() * quantity;
        }
        return 0.0;
    }
}
