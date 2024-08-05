package com.ataccama.hiring.archive;

public class OrderDetail {
    private final int orderId;
    private final double price;
    private final int itemCount;

    public OrderDetail(int orderId, double price, int itemCount) {
        this.orderId = orderId;
        this.price = price;
        this.itemCount = itemCount;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getPrice() {
        return price;
    }

    public int getItemCount() {
        return itemCount;
    }
}
