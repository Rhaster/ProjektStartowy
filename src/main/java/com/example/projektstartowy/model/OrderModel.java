package com.example.projektstartowy.model;

import jakarta.persistence.*;
import java.util.Date;
 // Model dla Zamówień, zawiera klucz obcy do tabeli Users
@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long orderID;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "rent_date_start")
    @Temporal(TemporalType.DATE)
    private Date rentDateStart;
    @Column(name = "rate_date_end")
    @Temporal(TemporalType.DATE)
    private Date rateDateEnd;
    @ManyToOne  // Wielu do jednego bo jeden klient moze miec wiele zamówien
    @JoinColumn(name = "customer_id", nullable = false,updatable = false)
    private UserModel customer;
    // Getty i Setty
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
    public UserModel getCustomer() {
        return customer;
    }
    public void setCustomer(UserModel customer) {
        this.customer = customer;
    }
    @Override
     public String toString() {
        return " orderID = " + orderID + " name =" + name + " rentDateStart = "
                + rentDateStart + " rateDateEnd = " + rateDateEnd + " customer = " + customer;
    }

}