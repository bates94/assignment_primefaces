package com.example.demo.web;

import com.example.demo.models.UserContactMessage;
import com.example.demo.repositories.UserContactMessageRepository;
import com.example.demo.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "contactmessage")
@SessionScoped
public class ContactMessageController implements Serializable {
    private List<UserContactMessage> messageList;

    @Autowired
    private UserContactMessageRepository userContactMessageRepository;

    public List<UserContactMessage> getMessageList() {
        messageList = userContactMessageRepository.findAll();
        return messageList;
    }

    @Transactional
    public void deleteMessage(String username, String message) {
        userContactMessageRepository.deleteMessageForUsername(username,message);
        Messages.addMessage("Message deleted. for:" + username);
    }

}
