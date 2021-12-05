package com.example.transferhall.models.views;

import java.math.BigDecimal;

public class UserStatisticsView {

    private Long numberOfUsers;
    private Long totalNumberOfOrders;
    private Integer numberOfPendingOrders;

    public UserStatisticsView() {
    }

    public Long getNumberOfUsers() {
        return numberOfUsers;
    }

    public UserStatisticsView setNumberOfUsers(Long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        return this;
    }

    public Long getTotalNumberOfOrders() {
        return totalNumberOfOrders;
    }

    public UserStatisticsView setTotalNumberOfOrders(Long totalNumberOfOrders) {
        this.totalNumberOfOrders = totalNumberOfOrders;
        return this;
    }

    public Integer getNumberOfPendingOrders() {
        return numberOfPendingOrders;
    }

    public UserStatisticsView setNumberOfPendingOrders(Integer numberOfPendingOrders) {
        this.numberOfPendingOrders = numberOfPendingOrders;
        return this;
    }

}
