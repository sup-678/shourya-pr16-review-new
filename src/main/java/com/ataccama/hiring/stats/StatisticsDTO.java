package com.ataccama.hiring.stats;

public class StatisticsDTO {

    private final double averagePrice;
    private final double averagePricePerItem;

    public StatisticsDTO(double averagePrice, double averagePricePerItem) {
        this.averagePrice = averagePrice;
        this.averagePricePerItem = averagePricePerItem;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public double getAveragePricePerItem() {
        return averagePricePerItem;
    }
}
