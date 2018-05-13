package com.fengchaoli.ocenter.repository;

import com.fengchaoli.ocenter.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {

}
