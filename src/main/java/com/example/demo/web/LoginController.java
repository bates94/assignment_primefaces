package com.example.demo.web;

import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("login")
@SessionScoped
public class LoginController implements Serializable {

    private String username = "";
    private String password = "";

    @Autowired
    private UserRepository userRepository;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String control() {
//        User user = userRepository.findUser(getUsername(),getPassword());
//        if (user != null) {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            return "Hello " + authentication.getName() + "!";
//        }
//        else {
//            return "Unsuccessful";
//        }
    }

}
