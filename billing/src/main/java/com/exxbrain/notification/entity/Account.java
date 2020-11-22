package com.exxbrain.notification.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class Account {
    @Id
    private String userId;

    @Column
    private BigDecimal balance;
}
