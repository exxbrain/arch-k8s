package com.exxbrain.notification.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private BigDecimal price;

    @Column
    private String userId;
}
