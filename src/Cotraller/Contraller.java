package Cotraller;

import DataBaseConnection.DataBaseConnection;
import Model.Item;
import java.sql.*;
import java.util.ArrayList;

public class Contraller {

    public static boolean addItemMethod(Item item) throws ClassNotFoundException, SQLException {
        String SQL = "Insert into Item Values(?,?,?,?)";
        Connection connection = (Connection) DataBaseConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, item.getId());
        stm.setObject(2, item.getName());
        stm.setObject(3, item.getPrice());
        stm.setObject(4, item.getQty());
        int count = stm.executeUpdate();
        return count > 0;
    }

    public static Item searchItemMethod(String id) throws ClassNotFoundException, SQLException {
        String SQL = "Select * From item where code='" + id + "'";
        Connection connection = DataBaseConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        if (rst.next()) {
            return new Item(id, rst.getString(2), (rst.getDouble(3)), (rst.getInt(4)));
        }
        return null;
    }

    public static boolean updateItemMethod(Item item) throws ClassNotFoundException, SQLException {
        String SQL = "update item set description = ? , unitPrice = ? , qtyOnHand = ? where code = ?";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
        PreparedStatement stm = connection.prepareCall(SQL);
        stm.setObject(1, item.getName());
        stm.setObject(2, item.getPrice());
        stm.setObject(3, item.getQty());
        stm.setObject(4, item.getId());
        int count = stm.executeUpdate();
        return count > 0;
    }

    public static boolean deleteItemMethod(String code) throws ClassNotFoundException, SQLException {
        String SQL = "delete from item where code = '" + code + "' ";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
        Statement stm = connection.createStatement();
        int count = stm.executeUpdate(SQL);
        return count > 0;
    }

    public static ArrayList<Item> viewItemMethod() throws ClassNotFoundException, SQLException {
        String SQL = "select * from item ";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<Item> arrayList = new ArrayList<>();
        while (rst.next()) {
            arrayList.add(new Item(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return arrayList;
    }
}
