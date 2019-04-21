package com.example.exerecise.Models;

public class TransactionListItem {

    private String id;
    private String title;
    private int price;
    private String image;
    private int order_num;

    public TransactionListItem(String id, String title, int price, String image, int order_num) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.order_num = order_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }
}
