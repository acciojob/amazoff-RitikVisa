package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order() {
    }

    public Order(String id, String deliveryTime) {

        this.id= id;
        String hh= "";
        String mm="";
       hh+= deliveryTime.charAt(0);
       hh+=deliveryTime.charAt(1);
        for(int i=2;i<deliveryTime.length();i++){
            if(deliveryTime.charAt(i)!=':'){
                mm += deliveryTime.charAt(i);
            }
        }
        this.deliveryTime = (Integer.parseInt(hh) * 60) + Integer.parseInt(mm);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
