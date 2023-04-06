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
    private HashMap<String,DeliveryPartner > partnerMap =new HashMap<>();
    private HashMap<String, List<Order>> order_partner_pair = new HashMap<>();
    private String lastDeiveryTime = "";
    private HashMap<String , Order> unassignnedOrders = new HashMap<>();
//    private HashMap<String,String> orderPartner = new HashMap<>();





    public void addOrder(Order order){
        orderMap.put(order.getId(), order);
       unassignnedOrders.put(order.getId(),order);

    }
    public void addPartner(String partnerId){

          DeliveryPartner partner= new DeliveryPartner(partnerId);

           partnerMap.put(partnerId,partner);


    }



    public Order get_order_by_id(String orderId){
       Order order = orderMap.get(orderId);

      return order;
    }
    public DeliveryPartner get_partner(String partnerId){

        DeliveryPartner partner= new DeliveryPartner(partnerId);



        return partner;
    }
    public void addOrder_partner(String partnerId,String orderId){

        List<Order> list = new ArrayList<>();
        if(!order_partner_pair.containsKey(partnerId)){
            list.add(orderMap.get(orderId));
            order_partner_pair.put(partnerId,list);
        }else{
            order_partner_pair.get(partnerId).add(orderMap.get(orderId));
        }

            if(unassignnedOrders.containsKey(orderId)){
                unassignnedOrders.remove(orderId);
            }

        }


    public int getOrderCountByPartnerId(String partnerId){
        int count = order_partner_pair.get(partnerId).size();

        return count;
    }

//    public List<Order> order_List (List<String> list,String partnerId){
//        List<Order> orderList = new ArrayList<>();
//        list = order_partner_pair.get(partnerId);
//        String orderId ="";
//        for(int i=0;i< list.size();i++){
//            orderId = list.get(i);
//            orderList.add(get_order_by_id(orderId));
//
//        }
//        return orderList;
//    }

    public List<Order> getOrdersByPartnerId(String partnerId){
        List<Order> orderList = new ArrayList<>();
        if(order_partner_pair.containsKey(partnerId)){
            orderList = order_partner_pair.get(partnerId);
        }
//        List<Order> orderList = new ArrayList<>();
//        orderList = order_List(order_partner_pair.get(partnerId),partnerId);
//
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

       List<Order> list = order_partner_pair.get(partnerId);


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
      List<Order> list = order_partner_pair.get(partnerId);
      for(int i=0;i<list.size();i++){
         int time = list.get(i).getDeliveryTime();
          if(time > max){
              max = time;
          }

      }
       return max;
   }

   public void deletePartnerById(String partnerId){

       List<Order> list = order_partner_pair.get(partnerId);




       for(int i=0;i < list.size();i++){
           String s = list.get(i).getId();
            unassignnedOrders.put(s, get_order_by_id(s));
        }
       order_partner_pair.remove(partnerId);
       partnerMap.remove(partnerId);

   }


   public void deleteOrderById(String orderId){
        Order o = orderMap.get(orderId);
        for(Map.Entry<String,List<Order>> entry : order_partner_pair.entrySet()){

          if(entry.getValue().contains(o)){
              entry.getValue().remove(o);
          }
        }
        orderMap.remove(orderId);
        unassignnedOrders.remove(orderId);
   }


    }

