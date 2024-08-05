package CrearPDF;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author s_SEBAS
 */

public class EnvioCorreos2 {

    private String emailFrom = "mexicoinegi2004@gmail.com";
    private String passwordFrom = "gaemzhmalokafynv";
    String emailTo;
    String subject;
    String content;

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public EnvioCorreos2(String emailTo, String subject, String content) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.content = content;
        mProperties = new Properties();
        createEmail();
    }

    private void createEmail() {
        

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);

            // Crear el cuerpo del mensaje
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(content, "ISO-8859-1", "html");

            // Crear el cuerpo del adjunto
            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filePath = "C:\\Users\\marle\\OneDrive\\Documentos\\NetBeansProjects\\VistaInegi\\VistaInegi\\Bienvenida.pdf"; // Cambia esto a la ruta de tu archivo PDF
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayDataSource bds = new ByteArrayDataSource(fileInputStream, "application/pdf");
            attachmentPart.setDataHandler(new javax.activation.DataHandler(bds));
            attachmentPart.setFileName(MimeUtility.encodeText(file.getName()));

            // Combinar el texto y el adjunto en un solo mensaje
            MimeMultipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(textPart);
            multiPart.addBodyPart(attachmentPart);

            mCorreo.setContent(multiPart);

        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(null, "Correo Enviado ");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
