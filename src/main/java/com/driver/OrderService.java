package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {



    @Autowired
   OrderRepository repository;

    public void addOrder(Order order){

        repository.addOrder(order);
    }
    public void addPartner(String partnerId){

        repository.addPartner(partnerId);
    }
    public void add_order_partner(String partnerId,String  orderId){

        repository.add_order_partner(orderId,partnerId);
    }

    public Order get_order_by_id(String orderId){

        Order order = repository.get_order_by_id(orderId);
        return order;
    }
    public DeliveryPartner getPartnerById(String PartnerId){

        DeliveryPartner partner = repository.get_partner(PartnerId);
        return partner;
    }
}
