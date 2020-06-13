package com.example.demo.web;

import com.example.demo.models.UserContactMessage;
import com.example.demo.repositories.UserContactMessageRepository;
import com.example.demo.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named(value = "home")
@RequestScoped
public class HomeController {

    private String subject;
    private String message;

    @Autowired
    UserContactMessageRepository userContactMessageRepository;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String movePage() {
        return "/index.xhtml";
    }

    public void sendMessage() throws IOException, InterruptedException {
        if (!message.equals("") || message.equals(null)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            UserContactMessage userContactMessage = new UserContactMessage();
            userContactMessage.setId(null);
            userContactMessage.setMessage(message);
            userContactMessage.setSubject(subject);
            userContactMessage.setUsername(username);
            userContactMessageRepository.save(userContactMessage);
            this.message = null;
            this.subject = null;
            Messages.addMessage("Contact message sent!");
        } else {
            Messages.addMessageError("Contact message didn't send!");
        }
    }
}
