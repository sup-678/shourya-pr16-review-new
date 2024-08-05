package com.ataccama.hiring.order;

import com.ataccama.hiring.stats.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Spy
    RabbitMqService rabbitMqService;

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void finalizeOrderTest() {
        LocalDateTime fakeDate = LocalDateTime.parse("1986-04-08 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMocked.when(LocalDateTime::now).thenReturn(fakeDate);

        Order order = new Order(42);
        when(jdbcTemplate.queryForObject(any(String.class), any(Class.class))).thenReturn(order);

        orderService.finalizeOrder(42);

        assertEquals(order.getFinalizedAt(), fakeDate);
        assertEquals(order.isFinalized(), true);
        verify(rabbitMqService, times(1)).send(any(FinalizeOrder.class));
    }
}
