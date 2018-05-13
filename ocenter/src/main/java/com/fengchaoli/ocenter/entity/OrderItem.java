package com.fengchaoli.ocenter.entity;

import com.fengchaoli.basic.entity.BaseEntity;
import com.fengchaoli.ocenter.statemachine.OrderState;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class OrderItem extends BaseEntity {
   @Column
   @Enumerated(EnumType.STRING)
   private OrderState state;
}
