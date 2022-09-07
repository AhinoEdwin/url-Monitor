import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;


public class Email{

    static String username = "samplezoho5@gmail.com";
    static String password= "Sample@55";

    public static  void mail(String recepient,String text) {
        String to = recepient;
        String from = "samplezoho55@gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.setProperty("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Status of the Website");
            message.setText(text);
            Transport.send(message);
            System.out.println("Message Delivered");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
