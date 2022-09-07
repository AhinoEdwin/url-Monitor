import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class main {
    String Datetime = "";
    int rescode;
    String link = "";

    public static void main(String args[]) throws SQLException, ClassNotFoundException {

        main obj = new main();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Url to be monitored :");
        obj.link=sc.nextLine();
        HttpURLConnection connection = null;
        try {
            URL u = new URL(obj.link);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("HEAD");
            obj.rescode = connection.getResponseCode();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
            Date date = new Date();
            obj.Datetime = simpleDateFormat.format(date);

            System.out.println(obj.rescode);
            System.out.println(obj.Datetime);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/urldata?verifyServerCertificate=false&useSSL=true&requireSSL=true", "root", "root");

            Statement statement = connect.createStatement();

            statement.execute("CREATE TABLE Data(code int,urlName varchar(256),Actualtime varchar(25))");

            PreparedStatement pst = connect.prepareStatement("INSERT INTO Data values(?,?,?);");

            pst.setInt(1, obj.rescode);
            pst.setString(2, obj.link);
            pst.setString(3, obj.Datetime);

            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Timer time2 = new Timer();
            access sub = new access();
            time2.schedule(sub, 0, 60 * 2 * 1000);
        }

        try {
            Timer time = new Timer();
            Database database = new Database();
            time.schedule(database, 0, 60 * 10 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


