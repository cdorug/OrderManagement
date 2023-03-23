package com.pt2022_30423_chete_doru_assignment_3.Model;

/** This class models an order entity.
 *  It has the same fields as the columns in the DB.
 *  Only has getters and setters.
 */
public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int orderedQuantity;

    public Orders() {}

    public Orders(int id, int clientId, int productId, int orderedQuantity) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.orderedQuantity = orderedQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}
