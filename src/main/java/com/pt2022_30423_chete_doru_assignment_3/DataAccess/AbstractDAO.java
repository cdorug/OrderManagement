package com.pt2022_30423_chete_doru_assignment_3.DataAccess;

import com.pt2022_30423_chete_doru_assignment_3.Connection.ConnectionFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.beans.PropertyDescriptor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** This class implements the methods that are used for reflection
 *  Same methods for client, product or order objects
 * @param <T> A client, product or order object
 */
public class AbstractDAO<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /** Creates a query that selects all rows
     * @return the generated query
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Creates a query that selects 1 row based on the id column
     * @param field the ID field
     * @return The generated query
     */
    private String createSelectByIdQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE id LIKE ");
        sb.append(field);
        return sb.toString();
    }

    /**
     * Creates a query that inserts a row in the database
     * @param object Client, product or order object
     * @return The generated query
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private String createInsertionQuery(T object) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        Field[] allFields = type.getDeclaredFields();
        String separator = "";
        for(Field field: allFields) {
            sb.append(separator);
            sb.append(field.getName());
            separator = ",";
        }
        sb.append(") VALUES (");
        separator = "";
        for(Field field: allFields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            Object fieldValue = method.invoke(object);
            sb.append(separator);
            sb.append("'");
            sb.append(fieldValue.toString());
            sb.append("'");
            separator = ",";
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Creates a query that updates a row in the database
     * @param object Client, product or order object
     * @return The generated query
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private String createEditingQuery(T object) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        Field[] allFields = type.getDeclaredFields();
        String separator = "";
        for(Field field: allFields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            Object fieldValue = method.invoke(object);
            if(!fieldValue.toString().equals("") && !fieldValue.toString().equals("-1")) {
                sb.append(separator);
                sb.append(field.getName()).append("=");
                sb.append("'");
                sb.append(fieldValue.toString());
                sb.append("'");
                separator = ",";
            }
        }
        sb.append(" WHERE ");
        sb.append(allFields[0].getName().toString());
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(allFields[0].getName(), type);
        Method method = propertyDescriptor.getReadMethod();
        Object fieldValue = method.invoke(object);
        sb.append("= '");
        sb.append(fieldValue.toString());
        sb.append("' ");
        return sb.toString();
    }

    /**
     * Creates a query that deletes a row in the database
     * @param object Client, product or order object
     * @return The generated query
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private String createDeletionQuery(T object) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        Field[] allFields = type.getDeclaredFields();
        sb.append(allFields[0].getName().toString());
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(allFields[0].getName(), type);
        Method method = propertyDescriptor.getReadMethod();
        Object fieldValue = method.invoke(object);
        sb.append("=");
        sb.append(fieldValue.toString());
        return sb.toString();
    }

    /**
     * Creates objects out of retrieved rows from the database
     * @param resultSet The result set of a query
     * @return The list of generated client, product or order objects
     * @throws InvocationTargetException
     */
    private List<T> createObjects(ResultSet resultSet) throws InvocationTargetException {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | SQLException | IntrospectionException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Retrieves rows from the database and then creates objects out of each row
     * @return The list of generated client, product or order objects
     */
    public List<T> getTableDataFromDB() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a TableView based on the columns of the table in the query
     * @return The initialized TableView object
     */
    public TableView<T> getColumnDataFromDB() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData rsMetaData = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            rsMetaData = resultSet.getMetaData();

            TableView<T> tableView = new TableView<>();

            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                TableColumn<T, ?> newColumn = new TableColumn<>();
                newColumn.setText(rsMetaData.getColumnName(i));
                newColumn.setCellValueFactory(new PropertyValueFactory<>(rsMetaData.getColumnName(i)));
                tableView.getColumns().add(newColumn);
            }

            return tableView;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Connects to the database and finds a row matching the given ID
     * @param id The ID that is being searched
     * @return List that should contain 1 object
     */
    public List<T> find(String id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String find = createSelectByIdQuery(id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(find);
            System.out.println(find);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Connects to the database and adds a row with the fields of the given object.
     * @param object The object that is being added
     * @return Positive if successful, otherwise -1
     */
    public int add(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String update = null;
        try {
            update = createInsertionQuery(object);
            System.out.println(update);
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(update);
            return statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Connects to the database and updates one row
     * @param object The object that has fields which will be used to edit the row
     * @return Positive if successful, -1 otherwise
     */
    public int edit(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String edit = null;
        try {
            edit = createEditingQuery(object);
            System.out.println(edit);
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(edit);
            return statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    /**
     * Connects to the database and deletes one row
     * @param object The object that has ID of the row to be deleted
     * @return Positive if successful, -1 otherwise
     */
    public int delete(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String delete = null;
        try {
            delete = createDeletionQuery(object);
            System.out.println(delete);
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(delete);
            return statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

}
