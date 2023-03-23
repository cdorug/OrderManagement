package com.pt2022_30423_chete_doru_assignment_3.Model;

/** This class models a product entity.
 *  It has the same fields as the columns in the DB.
 *  Only has getters and setters.
 */
public class Product {
    private int id;
    private String productName;
    private int quantity;
    private int price;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String productName, int quantity, int price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
