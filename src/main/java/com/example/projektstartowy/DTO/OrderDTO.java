package com.example.projektstartowy.DTO;

import java.util.Date;

public class OrderDTO {
    private Long orderID;
    private String name;
    private Date rentDateStart;
    private Date rateDateEnd;
    private CustomerDTO customer;

    // Getters and Setters
    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRentDateStart() {
        return rentDateStart;
    }

    public void setRentDateStart(Date rentDateStart) {
        this.rentDateStart = rentDateStart;
    }

    public Date getRateDateEnd() {
        return rateDateEnd;
    }

    public void setRateDateEnd(Date rateDateEnd) {
        this.rateDateEnd = rateDateEnd;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}