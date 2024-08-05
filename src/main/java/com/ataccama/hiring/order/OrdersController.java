package com.ataccama.hiring.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    @Autowired
    private OrderService service;

    @GetMapping(value = "/finalize")
    public void finalizeOrder(@RequestParam("orderId") int orderId) {
        service.finalizeOrder(orderId);
    }

}
