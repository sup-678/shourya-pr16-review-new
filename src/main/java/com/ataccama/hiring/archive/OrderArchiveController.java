package com.ataccama.hiring.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderArchiveController {

    @Autowired
    private OrderArchiveService orderArchiveService;

    @GetMapping(value = "/detail")
    @ResponseBody
    public OrderDetail orderDetail(@RequestParam("orderId") int orderId) {
        return orderArchiveService.getDetail(orderId);
    }

}
