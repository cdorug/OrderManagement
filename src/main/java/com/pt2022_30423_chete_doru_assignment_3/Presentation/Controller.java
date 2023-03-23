package com.pt2022_30423_chete_doru_assignment_3.Presentation;

import com.pt2022_30423_chete_doru_assignment_3.Application;
import com.pt2022_30423_chete_doru_assignment_3.BusinessLogic.ClientBLL;
import com.pt2022_30423_chete_doru_assignment_3.BusinessLogic.OrderBLL;
import com.pt2022_30423_chete_doru_assignment_3.BusinessLogic.ProductBLL;
import com.pt2022_30423_chete_doru_assignment_3.Model.Client;
import com.pt2022_30423_chete_doru_assignment_3.Model.Orders;
import com.pt2022_30423_chete_doru_assignment_3.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Controller class for the whole JavaFX application, the different views can switch between one another
 */
public class Controller {
    // Products View
    @FXML private TableView<Product> productsTable_fx;
    @FXML private TextField productId_fx;
    @FXML private TextField productName_fx;
    @FXML private TextField productQuantity_fx;
    @FXML private TextField productPrice_fx;
    @FXML private Label productsViewMsgBox_fx;
    // Clients View
    @FXML private TableView<Client> clientsTable_fx;
    @FXML private TextField clientId_fx;
    @FXML private TextField clientName_fx;
    @FXML private TextField clientAddress_fx;
    @FXML private TextField clientEmail_fx;
    @FXML private TextField clientAge_fx;
    @FXML private Label clientsViewMsgBox_fx;
    // Orders View
    @FXML private TableView<Orders> ordersTable_fx;
    @FXML private ChoiceBox<String> productList_fx;
    @FXML private ChoiceBox<String> clientList_fx;
    @FXML private TextField orderQuantity_fx;
    @FXML private Label ordersViewMsgBox_fx;
    @FXML private TextField orderId_fx;

    ClientBLL clientBLL = new ClientBLL();
    ProductBLL productBLL = new ProductBLL();
    OrderBLL orderBLL = new OrderBLL();

    public void switchToOrdersView() throws IOException {
        Application.changeWindow("Orders.fxml");
    }

    @FXML
    public void switchToProductsView() throws IOException {
        Application.changeWindow("Products.fxml");
    }

    @FXML
    public void switchToClientsView() throws IOException {
        Application.changeWindow("Clients.fxml");
    }

    /**
     * Retrieves data from the UI and tries to create a product object to be inserted in the database using the productBLL
     */
    @FXML private void addProduct() {
        productsViewMsgBox_fx.setText("Adding product...");
        int productId = Integer.parseInt(productId_fx.getText());
        String productName = productName_fx.getText();
        int productQuantity = Integer.parseInt(productQuantity_fx.getText());
        int productPrice = Integer.parseInt(productPrice_fx.getText());
        Product newProduct = new Product(productId, productName, productQuantity, productPrice);
        boolean insertedSuccessfully = productBLL.addProduct(newProduct);
        if(insertedSuccessfully) {
            productsViewMsgBox_fx.setText("Inserted Successfully");
        }
        else {
            productsViewMsgBox_fx.setText("Insertion error");
        }
    }

    /**
     * Retrieves data from the UI and tries to create a product object which has the ID of the row to be deleted
     */
    @FXML private void deleteProduct() {
        productsViewMsgBox_fx.setText("Deleting product...");
        int deletedProductId = Integer.parseInt(productId_fx.getText());
        Product deletedProduct = new Product(deletedProductId);
        boolean insertedSuccessfully = productBLL.deleteProduct(deletedProduct);
        if(insertedSuccessfully) {
            productsViewMsgBox_fx.setText("Deleted Successfully");
        }
        else {
            productsViewMsgBox_fx.setText("Deleted error");
        }
    }

    /**
     * Retrieves data from the UI and creates a product object that has fields which will edit the rows in the database
     */
    @FXML private void editProduct() {
        productsViewMsgBox_fx.setText("Editing product...");
        if(productId_fx.getText().equals("")) {
            productsViewMsgBox_fx.setText("Please enter ID of product to be edited!");
            return;
        }
        int productId = Integer.parseInt(productId_fx.getText());
        int productQuantity;
        int productPrice;
        String productName = productName_fx.getText();
        if(productQuantity_fx.getText().equals("")) {
            productQuantity = -1;
        }
        else {
            productQuantity = Integer.parseInt(productQuantity_fx.getText());
        }
        if(productPrice_fx.getText().equals("")) {
            productPrice = -1;
        }
        else {
            productPrice = Integer.parseInt(productQuantity_fx.getText());
        }
        Product editedProduct = new Product(productId, productName, productQuantity, productPrice);
        boolean editedSuccessfully = productBLL.editProduct(editedProduct);
        if(editedSuccessfully) {
            productsViewMsgBox_fx.setText("Edited Successfully");
        }
        else {
            productsViewMsgBox_fx.setText("Editing error");
        }
    }

    /**
     * Retrieves all rows from product table and displays it in the table view
     * @throws SQLException Throws exception when refreshing wasn't successful
     */
    @FXML private void refreshProducts() throws SQLException {
        productsViewMsgBox_fx.setText("Refreshing...");
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(productBLL.getProductDataFromDB());
        productsTable_fx.getColumns().setAll(productBLL.getProductColumnsFromDB().getColumns());
        productsTable_fx.getItems().setAll(products);
        productsViewMsgBox_fx.setText("Refreshed successfully");
    }

    /**
     * Retrieves data from the UI and tries to create a client object to be inserted in the database using the clientBLL
     */
    @FXML private void addClient() {
        clientsViewMsgBox_fx.setText("Adding client...");
        int newClientId = Integer.parseInt(clientId_fx.getText());
        String newClientName = clientName_fx.getText();
        String newClientAddress = clientAddress_fx.getText();
        String newClientEmail = clientEmail_fx.getText();
        int newClientAge = Integer.parseInt(clientAge_fx.getText());
        Client newClient = new Client(newClientId, newClientName, newClientAddress, newClientEmail, newClientAge);
        boolean insertedSuccessfully = clientBLL.addClient(newClient);
        if(insertedSuccessfully) {
            clientsViewMsgBox_fx.setText("Inserted Successfully");
        }
        else {
            clientsViewMsgBox_fx.setText("Insertion error");
        }
    }

    /**
     * Retrieves data from the UI and tries to create a client object which has the ID of the row to be deleted
     */
    @FXML private void deleteClient() {
        clientsViewMsgBox_fx.setText("Deleting client...");
        int deletedClientId = Integer.parseInt(clientId_fx.getText());
        Client deletedClient = new Client(deletedClientId);
        boolean insertedSuccessfully = clientBLL.deleteClient(deletedClient);
        if(insertedSuccessfully) {
            clientsViewMsgBox_fx.setText("Deleted Successfully");
        }
        else {
            clientsViewMsgBox_fx.setText("Deleted error");
        }
    }

    /**
     * Retrieves data from the UI and creates a client object that has fields which will edit the rows in the database
     */
    @FXML private void editClient() {
        clientsViewMsgBox_fx.setText("Editing client...");
        if(clientId_fx.getText().equals("")) {
            clientsViewMsgBox_fx.setText("Please enter a valid client ID to be edited.");
            return;
        }
        int editedClientId = Integer.parseInt(clientId_fx.getText());
        String editedClientName = clientName_fx.getText();
        String editedClientAddress = clientAddress_fx.getText();
        String editedClientEmail = clientEmail_fx.getText();
        int editedClientAge;
        if(!clientAge_fx.getText().equals("")){
            editedClientAge = Integer.parseInt(clientAge_fx.getText());
        }
        else {
            editedClientAge = -1;
        }
        Client editedClient = new Client(editedClientId, editedClientName, editedClientAddress, editedClientEmail, editedClientAge);
        boolean editedSuccessfully = clientBLL.editClient(editedClient);
        if(editedSuccessfully) {
            clientsViewMsgBox_fx.setText("Edited Successfully");
        }
        else {
            clientsViewMsgBox_fx.setText("Editing error");
        }
    }

    /**
     * Retrieves all rows from client table and displays it in the table view
     * @throws SQLException Throws exception when refreshing wasn't successful
     */
    @FXML private void refreshClients () throws SQLException {
        clientsViewMsgBox_fx.setText("Refreshing...");
        ObservableList<Client> clients = FXCollections.observableArrayList();
        clients.addAll(clientBLL.getClientDataFromDB());
        clientsTable_fx.getColumns().setAll(clientBLL.getClientColumnsFromDB().getColumns());
        clientsTable_fx.getItems().setAll(clients);
        clientsViewMsgBox_fx.setText("Refreshed successfully");
    }

    /**
     * Retrieves all rows from order table and displays it in the table view
     * Refreshes both ChoiceBoxes with all the rows in the client and product tables respectively
     * @throws SQLException Throws exception when refreshing wasn't successful
     */
    @FXML private void refreshOrders() throws SQLException {
        ordersViewMsgBox_fx.setText("Refreshing...");
        ObservableList<Orders> orders = FXCollections.observableArrayList();
        orders.addAll(orderBLL.getOrderDataFromDB());
        ordersTable_fx.getColumns().setAll(orderBLL.getOrderColumnsFromDB().getColumns());
        ordersTable_fx.getItems().setAll(orders);
        productList_fx.getItems().clear();
        clientList_fx.getItems().clear();
        List<Client> clients = FXCollections.observableArrayList();
        List<Product> products = FXCollections.observableArrayList();
        clients.addAll(clientBLL.getClientDataFromDB());
        for(Client client: clients) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ");
            sb.append(String.valueOf(client.getId()));
            sb.append(" Name: ");
            sb.append(client.getName());
            clientList_fx.getItems().add(sb.toString());
        }
        products.addAll(productBLL.getProductDataFromDB());
        for(Product product: products) {
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ");
            sb.append(String.valueOf(product.getId()));
            sb.append(" Name: ");
            sb.append(product.getProductName());
            sb.append(" Price: ");
            sb.append(product.getPrice());
            productList_fx.getItems().add(sb.toString());
        }
        ordersViewMsgBox_fx.setText("Refreshed successfully");
    }

    /**
     * Searches client by given ID and displays it in the label
     */
    @FXML private void findClientById() {
        clientsViewMsgBox_fx.setText("Searching...");
        List<Client> client = FXCollections.observableArrayList();
        try {
            client.addAll(clientBLL.findClient(clientId_fx.getText()));
            clientsViewMsgBox_fx.setText("Found client! \nID: " + client.get(0).getId() + ", Name: " + client.get(0).getName());
        } catch(RuntimeException e) {
            clientsViewMsgBox_fx.setText("Client not found!");
        }
    }

    /**
     * Searches product by given ID and displays it in the label
     */
    @FXML private void findProductById() {
        productsViewMsgBox_fx.setText("Searching...");
        List<Product> product = FXCollections.observableArrayList();
        try {
            product.addAll(productBLL.findProduct(productId_fx.getText()));
            productsViewMsgBox_fx.setText("Found product! \nName: " + product.get(0).getProductName() + ", Quantity: " + product.get(0).getQuantity() + ", Price: " + product.get(0).getPrice());
        } catch(RuntimeException e) {
            clientsViewMsgBox_fx.setText("Product not found!");
        }
    }

    /**
     * Searches order by given ID and displays it in the label
     */
    @FXML private void findOrderById() {
        ordersViewMsgBox_fx.setText("Searching...");
        List<Orders> order = FXCollections.observableArrayList();
        try {
            order.addAll(orderBLL.findOrder(orderId_fx.getText()));
            ordersViewMsgBox_fx.setText("Found order! \nClient ID: " + order.get(0).getClientId() + ", Product ID: " + order.get(0).getProductId() + ", Quantity ordered: " + order.get(0).getOrderedQuantity());
        } catch(RuntimeException e) {
            ordersViewMsgBox_fx.setText("Order not found!");
        }
    }

    /**
     * Retrieves data from the UI and tries to set up an order, if there is enough stock
     * If there isn't enough stock or if there is an error retrieving data from the UI a message will be displayed in the label
     * @throws IOException
     */
    @FXML private void addOrder() throws IOException {
        if(clientList_fx.getItems().isEmpty() || productList_fx.getItems().isEmpty() || orderQuantity_fx.getText().equals("")) {
            ordersViewMsgBox_fx.setText("Please select client, product and quantity to be ordered");
        }
        else {
            StringTokenizer tokenizer = new StringTokenizer(productList_fx.getValue());
            tokenizer.nextToken();
            String searchedProductID = tokenizer.nextToken();
            tokenizer = new StringTokenizer(clientList_fx.getValue());
            tokenizer.nextToken();
            String searchedClientID = tokenizer.nextToken();
            List <Product> product = productBLL.findProduct(searchedProductID);
            int quantityToBeOrdered = Integer.parseInt(orderQuantity_fx.getText());
            if(quantityToBeOrdered < 0) {
                ordersViewMsgBox_fx.setText("Please insert valid quantity.");
                return;
            }
            Product productToBeOrdered = product.get(0);
            if(productToBeOrdered.getQuantity() < quantityToBeOrdered) {
                ordersViewMsgBox_fx.setText("Out of stock! " + String.valueOf(productToBeOrdered.getQuantity()) + " piece(s) left.");
            }
            else {
                int orderID;
                if(!orderId_fx.getText().equals("")) {
                    orderID = Integer.parseInt(orderId_fx.getText());
                    if(orderID < 1) {
                        ordersViewMsgBox_fx.setText("Please insert a valid order ID");
                        return;
                    }
                }
                else {
                    ordersViewMsgBox_fx.setText("Please insert new order's ID");
                    return;
                }
                List <Client> client = clientBLL.findClient(searchedClientID);
                if(!client.isEmpty()) {
                    int clientOrdering = client.get(0).getId();
                    Orders newOrder = new Orders(orderID, clientOrdering, productToBeOrdered.getId(), quantityToBeOrdered);
                    boolean addedSuccessfully = orderBLL.addOrder(newOrder);
                    if(!addedSuccessfully) {
                        ordersViewMsgBox_fx.setText("Could not add order to database!");
                    }
                    else {
                        product.get(0).setQuantity(productToBeOrdered.getQuantity() - quantityToBeOrdered);
                        productBLL.editProduct(productToBeOrdered);
                        // generate bill
                        FileWriter bill = new FileWriter(client.get(0).getName() + "'s Bill.txt");
                        bill.write("Name of client: " + client.get(0).getName() + "\n");
                        bill.write("Address: " + client.get(0).getAddress() + "\n");
                        bill.write("E-mail: " + client.get(0).getEmail() + "\n");
                        bill.write("Ordered product: " + productToBeOrdered.getProductName() + ", quantity: " + quantityToBeOrdered + "\n");
                        double total = productToBeOrdered.getPrice() *  quantityToBeOrdered;
                        total = 1.19 * total;
                        String totalAsString = String.format("%.2f", total);
                        bill.write("Total: " + totalAsString + ", VAT included\n");
                        bill.close();
                        ordersViewMsgBox_fx.setText("Ordered successfully");
                    }
                }
                else {
                    ordersViewMsgBox_fx.setText("Client no longer exists!");
                }
            }
        }

    }
}