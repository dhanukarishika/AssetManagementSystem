import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {

    public static void sendEmail(String to, String subject, String body) {
        final String from = "dhanukarishika@gmail.com";
        final String password = "xleh ecvh pdyu netg"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("✅ Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
