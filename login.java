import java.sql.*;
import java.util.*;

public class login extends main{
    public static void log(String args[]) {
        Scanner sc = new Scanner(System.in);
        String Username = "";
        String Password = "";
        System.out.println("Enter Username: ");
        String name = sc.next();
        System.out.println("Enter Password: ");
        String password = sc.next();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?verifyServerCertificate=false&useSSL=true&requireSSL=true", "root", "root");

        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE Usersname='" + name + "' && Password='" + password + "'");

        while (rs.next()) {
            Username = rs.getString("Usersname");
            Password = rs.getString("Password");
        }

        if (name.equals(Username) && password.equals(Password)) {
               main obj = new main();
               obj.main();

        } else {
            System.out.println("Invalid UserPassword \n");
        }
    }