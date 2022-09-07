import java.sql.SQLException;
import java.util.TimerTask;

class access extends TimerTask {
    public void run(){
        main obj1= new main();
        String val= obj1.link;
        Data obj=new Data();
        try {
            obj.sub(val);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
