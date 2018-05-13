package com.fengchaoli.ocenter.service;

import com.fengchaoli.ocenter.entity.OrderItem;
import com.fengchaoli.ocenter.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderItem save(OrderItem order) {
        orderRepository.save(order);
        return order;
    }

    public OrderItem getOne(String id) {
        return orderRepository.getOne(id);
    }
}
