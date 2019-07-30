package tools;


import Member.RegisterController;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.AccModel;
import model.EmailModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class utils {

    public static void setWarning(PrintWriter out, String text, String path) {
        out.println("<script>");
        out.println("alert('" + text + "');");
        out.println("window.location.href='" + path + "'");
        out.println("</script>");
    }
    
    public static String encodedWord(String text) {
        try {
            return MimeUtility.encodeWord(text, "UTF-8", "Q");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
   public static void sendEmail(EmailModel em) {

        String host = "smtp.gmail.com";
        int port = 587;
        
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        
        Session session = em.getSession(props);
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(em.getFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(em.getTo()));
            message.setSubject(em.getSubject());
            message.setContent(em.getContext(),"text/html;charset=utf-8");
            Transport transport = em.getTransport(session);
            
            Transport.send(message);

            System.out.println("寄送email結束.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String generatePassCode(int id){
        //yy mm dd domain id
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd-hh-mm-ss");
        String d = sdf.format(new Date(time));
        String[] d_s = d.split("-");
        Random rdValue = new Random(time);
        int a = rdValue.nextInt(50)+10;
        
         return String.format("%s:%s:%s:%s:%s:%s:%d:%d", d_s[5],d_s[3],d_s[4],d_s[2],d_s[1],d_s[0],id%a,id/a,a);        
    }
    
    private static  String[] switchValues(String[] arr,int a, int b){
        String temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return arr;
    }
    
    public static void html2img(String html) throws IOException{
        JEditorPane ed = new JEditorPane(html);
        ed.setSize(200,200);
        
        BufferedImage image = new BufferedImage(ed.getWidth(),ed.getHeight(),BufferedImage.TYPE_INT_ARGB);
        
        SwingUtilities.paintComponent(image.createGraphics(),ed, new JPanel(), 0, 0, image.getWidth(), image.getHeight());
        
        ImageIO.write((RenderedImage)image,"png",new File("origin,ong"));
    }
    
}
