import java.sql.*;
import java.util.TimerTask;
class Database extends TimerTask{
    public void run(){
        try {
            boolean bool=false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/urldata?verifyServerCertificate=false&useSSL=true&requireSSL=true", "root", "root");
            Statement statement=connection.createStatement();


            ResultSet rt=statement.executeQuery("select code from Data where code=404");
            if(rt.next()){
                int id=rt.getInt(1);
                System.out.println("Code "+id+" found");
            }


            StringBuffer text=new StringBuffer();
            StringBuffer recepient=new StringBuffer("ahino.eece@sece.ac.in");
            ResultSet res=statement.executeQuery("select Actualtime from Data where code=404");
            if(res.next()){

                String time=res.getString(1);
                bool=true;
                text.append("Server is down "+time);
                System.out.println(text);
            }
            PreparedStatement pst = connection.prepareStatement("UPDATE Data set code = 208 where code=404 LIMIT 1");
            pst.execute();

            if(bool){
                Email.mail(recepient.toString(),text.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}