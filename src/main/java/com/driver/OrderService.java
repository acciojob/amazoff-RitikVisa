package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        repository.addOrder_partner(partnerId,orderId);
    }

    public Order get_order_by_id(String orderId){

        Order order = repository.get_order_by_id(orderId);
        return order;
    }
    public DeliveryPartner getPartnerById(String PartnerId){

        DeliveryPartner partner = repository.get_partner(PartnerId);
        return partner;
    }

    public int getOrderCountByPartnerId(String partnerId){
       return repository.getOrderCountByPartnerId(partnerId);
    }
    public List<Order> getOrdersByPartnerId(String partnerId){
        return repository.getOrdersByPartnerId(partnerId);
    }
    public List<Order> getAllOrders(){
       return  repository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return repository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
            return repository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public int getLastDeliveryTimeByPartnerId(String partnerId){
        return repository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public void deletePartnerById(String partnerId){
        repository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        repository.deleteOrderById(orderId);
    }
}
