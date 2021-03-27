package com.ssbsoft.library.controller;


import com.ssbsoft.library.common.Constants;
import com.ssbsoft.library.model.User;
import com.ssbsoft.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping( value = "/reset" , method = RequestMethod.POST)
    public String reset(@ModelAttribute("user") User user, ModelMap modelMap) {
        User userEmail = userService.getUserEmail(user.getEmail_id());

        String toEmail =user.getEmail_id();
        String fromEmail = "arulselvan844@gmail.com";
        String password = "Arul@3697";

        try {
            // your host email smtp server details
            Properties pr = new Properties();
            pr.setProperty("mail.smtp.host", "smtp.gmail.com");
            pr.setProperty("mail.smtp.port", "465");
            pr.setProperty("mail.smtp.auth", "true");
            pr.setProperty("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.socketFactory.port", "465");
            pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
            mess.setSubject("Your Password");

            //set message text
            mess.setText(" Your account password: " + userEmail.getPassword());
            //send the message
            Transport.send(mess);

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.addAttribute("error", Constants.INVALID_MAIL);
            return "resetPassword";
        }

        modelMap.addAttribute("reset", Constants.SEND_EMAIL);
        return "login";
    }

    @RequestMapping(value = "/resetPassword")
    public String resetPassword() {
        return "resetPassword";
    }

}
