package com.example.demo.web;

import com.example.demo.models.UserRole;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "management")
@SessionScoped
public class ManagementController {

    private String privilege;

    private String username;
//
//    private List<String> userNameList;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

//    @PostConstruct
//    public void init() {
//        userNameList = userRepository.findAllUserName();
//    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void givePrivilege() {
        UserRole role = userRoleRepository.findByUsername(username);

        if (privilege.equals("ROLE_ADMIN")) {
            role.setUserRole("ROLE_ADMIN");
        } else {
            role.setUserRole("ROLE_USER");
        }

        userRoleRepository.setFixedRole(role.getUserRole(), role.getUsername());

        addMessage("Successful!");
    }

    public void addMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
