package com.pt2022_30423_chete_doru_assignment_3.BusinessLogic;

import com.pt2022_30423_chete_doru_assignment_3.DataAccess.ClientDAO;
import com.pt2022_30423_chete_doru_assignment_3.DataAccess.ProductDAO;
import com.pt2022_30423_chete_doru_assignment_3.Model.Client;
import com.pt2022_30423_chete_doru_assignment_3.Model.Orders;
import com.pt2022_30423_chete_doru_assignment_3.Model.Product;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;

/** This is a business layer product for the client object. This is used to communicate with the product table in the database.
 */
public class ProductBLL {
    private final ProductDAO productDataAccess;

    public ProductBLL() {productDataAccess = new ProductDAO();}

    /** This retrieves data from the product table.
     * @return list of created product objects from retrieved rows
     */
    public List<Product> getProductDataFromDB() {
        return productDataAccess.getTableDataFromDB();
    }

    /** This retrieves the column data
     * @return a JavaFx table with the product columns from the table
     * @throws SQLException
     */
    public TableView<Product> getProductColumnsFromDB() throws SQLException {
        return productDataAccess.getColumnDataFromDB();
    }

    /** This adds a product row to the table
     * @param product Product object to be added
     * @return true if it was successful or false otherwise
     */
    public boolean addProduct(Product product) {
        int success = productDataAccess.add(product);
        return success > 0;
    }

    /** This edits a row in the product table
     * @param product Product object that is edited
     * @return true if it was
     */
    public boolean editProduct(Product product) {
        int success = productDataAccess.edit(product);
        return success > 0;
    }

    /** This deletes a row from the product table
     * @param product Product object to be deleted
     * @return true if it was successful false otherwise
     */
    public boolean deleteProduct(Product product) {
        int success = productDataAccess.delete(product);
        return success > 0;
    }

    /** This searches for a row with the specified ID
     * @param id ID of the searched product
     * @return list that should contain 1 product that was found by ID
     */
    public List<Product> findProduct(String id) {
        return productDataAccess.find(id);
    }
}
