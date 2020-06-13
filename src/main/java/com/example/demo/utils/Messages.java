package com.example.demo.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

    public static void addMessageError(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public static void addMessage(String message) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}
