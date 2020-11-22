package com.exxbrain.notification.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Order {
    @Id
    private String id;

    @Column
    private BigDecimal price;

    @Column
    private String userId;
}
