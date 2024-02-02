package com.project.ecommerce.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "order_table")
public class Order extends BaseEntity{
        @JsonIgnore
        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private Customer customer;

        @JsonIgnore
        @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
        private List<OrderItem> orderItems;




}
