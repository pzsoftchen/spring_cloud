package com.fengchaoli.ocenter.client;

import com.fengchaoli.ocenter.dto.OrderItemDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(serviceId = "ocenter")
public interface OrderClient {

    @GetMapping("/create")
    OrderItemDTO create();

}
