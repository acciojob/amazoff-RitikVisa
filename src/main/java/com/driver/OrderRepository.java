package com.driver;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {




    private HashMap<String,Order> orderMap = new HashMap<>();
    private HashMap<String,Integer > partnerMap =new HashMap<>();
    private HashMap<String, List<String>> order_partner_pair = new HashMap<>();
    private String lastDeiveryTime = "";
    private HashMap<String , Order> unassignnedOrders = new HashMap<>();
//    private HashMap<String,String> orderPartner = new HashMap<>();





    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
       unassignnedOrders.put(order.getId(),order);

    }
    public void addPartner(String partnerId){

           partnerMap.put(partnerId,10);


    }



    public Order get_order_by_id(String orderId){
       Order order = orderMap.get(orderId);

      return order;
    }
    public DeliveryPartner get_partner(String partnerId){
        DeliveryPartner partner= new DeliveryPartner(partnerId);
        partner.setNumberOfOrders(partnerMap.get(partnerId));


        return partner;
    }
    public void addOrder_partner(String partnerId,String orderId){

        if(order_partner_pair.containsKey(partnerId)){
       order_partner_pair.get(partnerId).add(orderId);
        }
        else{
            List<String> list = new ArrayList<>();
            list.add(orderId);
            order_partner_pair.put(partnerId,list);

            if(unassignnedOrders.containsKey(orderId)){
                unassignnedOrders.remove(orderId);
            }

        }
    }

    public int getOrderCountByPartnerId(String partnerId){
        int count = order_partner_pair.get(partnerId).size();

        return count;
    }

    public List<Order> order_List (List<String> list,String partnerId){
        List<Order> orderList = new ArrayList<>();
        list = order_partner_pair.get(partnerId);
        String orderId ="";
        for(int i=0;i< list.size();i++){
            orderId = list.get(i);
            orderList.add(get_order_by_id(orderId));

        }
        return orderList;
    }

    public List<Order> getOrdersByPartnerId(String partnerId){
        List<Order> orderList = new ArrayList<>();
        orderList = order_List(order_partner_pair.get(partnerId),partnerId);

        return orderList;

    }

   public List<Order> getAllOrders(){
        List<Order> list = new ArrayList<>();

       for (Map.Entry<String,Order> entry : orderMap.entrySet()){
           list.add(entry.getValue());
   }
        return list;
   }

   public int getCountOfUnassignedOrders(){
       return unassignnedOrders.size();
   }

   public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
        int count =0;


       String hh= "";
       String mm="";
       hh+= time.charAt(0);
       hh+=time.charAt(1);
       for(int i=2;i<time.length();i++){
           if(time.charAt(i)!=':'){
               mm += time.charAt(i);
           }
       }


       int currenttime = (Integer.parseInt(hh)*60) + Integer.parseInt(mm);

       List<Order> list = new ArrayList<>();
      list = order_List(order_partner_pair.get(partnerId),partnerId);

       for(int i=0;i < list.size();i++){
           if(list.get(i).getDeliveryTime() > currenttime){
               count++;
           }else{

               list.remove(i);


           }
       }
       return count;
   }

   public int getLastDeliveryTimeByPartnerId(String partnerId){
        int max =Integer.MIN_VALUE;
      List<String> list = order_partner_pair.get(partnerId);
      for(int i=0;i<list.size();i++){
         int time = get_order_by_id(list.get(i)).getDeliveryTime();
          if(time > max){
              max = time;
          }

      }
       return max;
   }

   public void deletePartnerById(String partnerId){

       List<String> list = order_partner_pair.get(partnerId);
       String s ="";

       for(int i=0;i < list.size();i++){
           s = list.get(i);
            unassignnedOrders.put(get_order_by_id(s).getId(), get_order_by_id(s));
        }
       order_partner_pair.remove(partnerId);
   }


   public void deleteOrderById(String orderId){
        for(Map.Entry<String,List<String>> entry : order_partner_pair.entrySet()){
          if(entry.getValue().contains(orderId)){
              entry.getValue().remove(orderId);
          }
        }
        orderMap.remove(orderId);
        unassignnedOrders.remove(orderId);
   }


    }

