package com.pt2022_30423_chete_doru_assignment_3.BusinessLogic;

import com.pt2022_30423_chete_doru_assignment_3.DataAccess.OrderDAO;
import com.pt2022_30423_chete_doru_assignment_3.Model.Client;
import com.pt2022_30423_chete_doru_assignment_3.Model.Orders;
import com.pt2022_30423_chete_doru_assignment_3.Model.Product;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;

/** This is a business layer class for the order object. This is used to communicate with the order table in the database.
 */

public class OrderBLL {
    private final OrderDAO orderDataAccess;

    public OrderBLL() {orderDataAccess = new OrderDAO();}

    /** This retrieves data from the order table.
     * @return list of created order objects from retrieved rows
     */
    public List<Orders> getOrderDataFromDB() {
        return orderDataAccess.getTableDataFromDB();
    }

    /** This retrieves the column data
     * @return a JavaFx table with the order columns from the table
     * @throws SQLException
     */
    public TableView<Orders> getOrderColumnsFromDB() throws SQLException {
        return orderDataAccess.getColumnDataFromDB();
    }

    /** This searches for a row with the specified ID
     * @param id ID of the searched order
     * @return list that should contain 1 order that was found by ID
     */
    public List<Orders> findOrder(String id) {
        return orderDataAccess.find(id);
    }

    /** This adds an order row to the table
     * @param order Order object to be added
     * @return true if it was successful or false otherwise
     */
    public boolean addOrder(Orders order) {
        int success = orderDataAccess.add(order);
        return success > 0;
    }
}
