package com.ataccama.hiring.stats;

import com.ataccama.hiring.archive.OrderArchiveService;
import com.ataccama.hiring.archive.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private OrderArchiveService orderArchive;

    @Override
    @Transactional
    public StatisticsDTO getStats(String name) {
        String sql = "SELECT * FROM orders WHERE buyer = '" + name + "'";
        List<Order> orders = jdbcTemplate.query(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                int orderId = rs.getInt(0);
                return new Order(orderId);
            }
        });
        List<OrderDetail> details = loadOrderDetails(orders);
        double priceTotal = 0;
        double averagePerItem = 0;
        for (OrderDetail detail : details) {
            priceTotal += detail.getPrice();
            averagePerItem += detail.getPrice() / detail.getItemCount();
        }
        return new StatisticsDTO(priceTotal/orders.size(), averagePerItem/ orders.size());
    }

    // load details in parallel so its faster
    private List<OrderDetail> loadOrderDetails(List<Order> orders) {
        List<OrderDetail> details = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(orders.size());
        for (Order order : orders) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    details.add(orderArchive.getDetail(order.orderId));
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(); // wait until every detail is fetched
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return details;
    }
}
