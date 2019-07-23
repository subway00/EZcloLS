/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.servlet.http.HttpSession;

public class EmailModel {
    private String email="";
    private String pwd = "";
    
    private String from=email;
    private String to="";
    private String subject = "";
    private String context = "";

    public EmailModel() {
    }

    public Session getSession(Properties props) {
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pwd);
            }
        });
    }
    
    public Transport getTransport(Session session) throws MessagingException{
        Transport tp = session.getTransport();
        tp.connect(email, pwd);
        return tp;
    }
    
    public void generate(){
        
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
