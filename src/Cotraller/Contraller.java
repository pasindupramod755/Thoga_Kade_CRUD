package Cotraller;

import Model.Item;
import java.sql.*;

public class Contraller {

    public static boolean addItemMethod(Item item) throws ClassNotFoundException, SQLException {
        String SQL = "Insert into Item Values(?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade", "root", "1234");
        PreparedStatement stm = connection.prepareStatement(SQL);
        stm.setObject(1, item.getId());
        stm.setObject(2, item.getName());
        stm.setObject(3, item.getPrice());
        stm.setObject(4, item.getQty());
        int count = stm.executeUpdate();
        return count>0;
    }
}
