package com.pt2022_30423_chete_doru_assignment_3.BusinessLogic;

import com.pt2022_30423_chete_doru_assignment_3.DataAccess.ClientDAO;
import com.pt2022_30423_chete_doru_assignment_3.Model.Client;
import javafx.scene.control.TableView;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/** This is a business layer class for the client object. This is used to communicate with the client table in the database.
 */

public class ClientBLL {
    private final ClientDAO clientDataAccess;

    public ClientBLL() {clientDataAccess = new ClientDAO();}

    /** This retrieves data from the client table.
     *  @return a list of client objects that were created from the retrieved rows in the database
     */
    public List<Client> getClientDataFromDB() {
        return clientDataAccess.getTableDataFromDB();
    }

    /** This retrieves the column data
     * @return a JavaFx table with the client columns from the table
     * @throws SQLException
     */
    public TableView<Client> getClientColumnsFromDB() throws SQLException {
        return clientDataAccess.getColumnDataFromDB();
    }

    /** This adds a client row to the table
     * @param client Client object to be added
     * @return true if it was successful or false otherwise
     */
    public boolean addClient(Client client) {
        int success = clientDataAccess.add(client);
        return success > 0;
    }

    /** This edits a row in the client table
     * @param client Client object that is edited
     * @return true if it was
     */
    public boolean editClient(Client client) {
        int success = clientDataAccess.edit(client);
        return success > 0;
    }

    /** This deletes a row from the client table
     * @param client Client object to be deleted
     * @return true if it was successful false otherwise
     */
    public boolean deleteClient(Client client) {
        int success = clientDataAccess.delete(client);
        return success > 0;
    }

    /** This searches for a row with the specified ID
     * @param id ID of the searched client
     * @return list that should contain 1 client that was found by ID
     */
    public List<Client> findClient(String id) {
        return clientDataAccess.find(id);
    }

}
