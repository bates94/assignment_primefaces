package com.example.demo.web;

import com.example.demo.models.UserContactMessage;
import com.example.demo.repositories.UserContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "contactmessage")
@SessionScoped
public class ContactMessageController {
    private List<UserContactMessage> messageList;

    @Autowired
    private UserContactMessageRepository userContactMessageRepository;

    public List<UserContactMessage> getMessageList() {
        messageList = userContactMessageRepository.findAll();
        return messageList;
    }
}
