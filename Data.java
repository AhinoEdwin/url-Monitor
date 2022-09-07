import com.sun.jdi.Value;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Data extends main {
     static int res;
     String Dt="";
    public void sub(String val)throws SQLException, ClassNotFoundException {
        String value=val;
        System.out.println("URL Found...");
        Data obj2= new Data();

        HttpURLConnection connection = null;
        try {
            URL u = new URL(value);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("HEAD");
            obj2.res= connection.getResponseCode();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
            Date date = new Date();
            obj2.Dt = simpleDateFormat.format(date);


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

        try{

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/urldata?verifyServerCertificate=false&useSSL=true&requireSSL=true", "root", "root");
        PreparedStatement pst = connect.prepareStatement("INSERT INTO Data values(?,?,?);");

        pst.setInt(1, obj2.res);
        pst.setString(2, obj2.link);
        pst.setString(3, obj2.Dt);

        pst.execute();

        }catch (Exception e){
            System.out.println(e);
        }
    }

}
