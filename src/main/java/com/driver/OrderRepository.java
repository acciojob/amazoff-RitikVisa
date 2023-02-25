package com.driver;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {




    private HashMap<String,Integer> orderMap = new HashMap<>();
    private HashMap<String,Integer > deliveryMap =new HashMap<>();
    private HashMap<String,String> order_partner_pair = new HashMap<>();





    public void addOrder(Order order){
        orderMap.put(order.getId(), order.getDeliveryTime());

    }
    public void addPartner(String partnerId){
       if(deliveryMap.containsKey(partnerId)){
           deliveryMap.put(partnerId,deliveryMap.get(partnerId)+1);
       }else{
           deliveryMap.put(partnerId,0);
       }

    }

    public void add_order_partner(String partnerId,String orderId){
        order_partner_pair.put(partnerId,orderId);

    }

    public Order get_order_by_id(String orderId){
      Order order = new Order(orderId,orderMap.get(orderId).toString());

      return order;
    }
    public DeliveryPartner get_partner(String partnerId){
        DeliveryPartner partner= new DeliveryPartner(partnerId);

        return partner;
    }
}
