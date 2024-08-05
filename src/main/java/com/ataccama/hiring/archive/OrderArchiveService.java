package com.ataccama.hiring.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderArchiveService extends AbstractRestCallService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderArchiveService() {
        super("http://api.orders.com");
    }

    @Transactional
    public OrderDetail getDetail(int orderId) {
        String sourceSystem = jdbcTemplate.queryForObject("SELECT sourceSystem FROM order_archive WHERE order_id =" + orderId, String.class);
        ResponseEntity<OrderDetail> response = callGet(orderId, sourceSystem);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // retry
            response = callGet(orderId, sourceSystem);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Api error!");
            }
        }
    }

    private ResponseEntity<OrderDetail> callGet(int orderId, String sourceSystem) {
        return callGet("/get/" + sourceSystem + "/" + orderId, OrderDetail.class);
    }
}
