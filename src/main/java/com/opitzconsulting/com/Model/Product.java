package com.opitzconsulting.com.Model;




/**
 * Created by OFI on 27.06.2016.
 */

public class Product{
    private String data_mId;
    private String data_status;
    private String data_brandid;
    private int data_amount;
    private boolean field_online;
    private double field_change_remaining;

    public Product(){}
    public String getData_status() {return data_status;}
    public String getData_mId() {return data_mId;}
    public String getField_brand_id() {return data_brandid;}
    public int getField_amount() {
        return data_amount;
    }
    public boolean getField_online() {
        return field_online;
    }
    public double getField_change_remaining(){return field_change_remaining;}

    public void setData_status(String data_status) {this.data_status=data_status;}
    public void setData_mId(String data_mId) {this.data_mId=data_mId;}
    public void setField_brand_id(String field_brand_id) {this.data_brandid=field_brand_id;}
    public void setField_amount(int field_amount) {this.data_amount=field_amount;}
    public void setField_online(boolean field_online) {this.field_online=field_online;}
    public void setField_change_remaining(double field_change_remaining){this.field_change_remaining= field_change_remaining;}
}
