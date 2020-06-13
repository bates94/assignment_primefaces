package com.example.demo.web;

import com.example.demo.models.User;
import com.example.demo.models.UserRole;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named("register")
@SessionScoped
public class RegisterController implements Serializable {
    private String username;
    private boolean usernameValidationStyle = true;
    private String password;
    private boolean passwordValidationStyle = true;
    private String confirmPassword;
    private boolean confirmPasswordValidationStyle = true;
    private String country;
    private boolean countryValidationStyle = true;
    private String phone;
    private boolean phoneValidationStyle = true;
    private String email;
    private boolean emailValidationStyle = true;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isUsernameValidationStyle() {
        return usernameValidationStyle;
    }

    public void setUsernameValidationStyle(boolean usernameValidationStyle) {
        this.usernameValidationStyle = usernameValidationStyle;
    }

    public boolean isPasswordValidationStyle() {
        return passwordValidationStyle;
    }

    public void setPasswordValidationStyle(boolean passwordValidationStyle) {
        this.passwordValidationStyle = passwordValidationStyle;
    }

    public boolean isConfirmPasswordValidationStyle() {
        return confirmPasswordValidationStyle;
    }

    public void setConfirmPasswordValidationStyle(boolean confirmPasswordValidationStyle) {
        this.confirmPasswordValidationStyle = confirmPasswordValidationStyle;
    }

    public boolean isCountryValidationStyle() {
        return countryValidationStyle;
    }

    public void setCountryValidationStyle(boolean countryValidationStyle) {
        this.countryValidationStyle = countryValidationStyle;
    }

    public boolean isPhoneValidationStyle() {
        return phoneValidationStyle;
    }

    public void setPhoneValidationStyle(boolean phoneValidationStyle) {
        this.phoneValidationStyle = phoneValidationStyle;
    }

    public boolean isEmailValidationStyle() {
        return emailValidationStyle;
    }

    public void setEmailValidationStyle(boolean emailValidationStyle) {
        this.emailValidationStyle = emailValidationStyle;
    }

    public String usernameValidation() {
        if (username == null) {
            return "";
        }
        if (username.length() == 0) {
            usernameValidationStyle = false;
            return "User name is required!";
        } else if (username.length() >= 8) {
            usernameValidationStyle = false;
            return "User name can not have more than 8 characters!";
        } else if (characterValidation(username)) {
            usernameValidationStyle = false;
            return "Only letters are allowed in the user name!";
        } else {
            usernameValidationStyle = true;
            return "Good";
        }
    }

    public boolean characterValidation(String username) {
        Pattern regex = Pattern.compile("[^a-zA-Z]+");
        Matcher matcher = regex.matcher(username);
        return matcher.find() ? true : false;
    }

    public String passwordValidation() {
        if (password == null) {
            return "";
        }

        if (password.length() < 8 || password.length() > 16) {
            passwordValidationStyle = false;
            return "Password must be 8-16 characters!";
        } else if (characterValidationForPassword(password)) {
            passwordValidationStyle = false;
            return "Only letters and numbers are allowed in the password!";
        } else {
            passwordValidationStyle = true;
            return "Good";
        }
    }

    public boolean characterValidationForPassword(String password) {
        Pattern regex = Pattern.compile("[^a-zA-Z0-9]+");
        Matcher matcher = regex.matcher(password);
        return matcher.find() ? true : false;
    }

    public String confirmPasswordValidation() {
        if (password == null) {
            return "";
        }
        if (!password.equals(confirmPassword)) {
            confirmPasswordValidationStyle = false;
            return "Passwords doesn't match!";
        } else {
            confirmPasswordValidationStyle = true;
            return "Good";
        }
    }

    public String emailValidation() {
        if (email == null) {
            return "";
        }
        if (email.equals("")) {
            emailValidationStyle = false;
            return "Email is required!";
        } else {
            emailValidationStyle = true;
            return "Good";
        }
    }

    public String phoneValidation() {
        if (phone == null) {
            return "";
        }
        if (phone.length() != 11) {
            phoneValidationStyle = false;
            return "Phone must be 11 characters!";
        } else {
            phoneValidationStyle = true;
            return "Good";
        }
    }

    public String countryValidation() {
        if (country == null) {
            return "";
        }
        if (country.equals("")) {
            countryValidationStyle = false;
            return "Country must be required!";
        } else {
            countryValidationStyle = true;
            return "Good";
        }
    }

    public void signUp() throws IOException {
        if (usernameValidationStyle && passwordValidationStyle && confirmPasswordValidationStyle
            && emailValidationStyle && phoneValidationStyle && countryValidationStyle) {
            if (userRepository.findByUserName(username) == null) {
                User user = new User();
                user.setId(null);
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setEmail(email);
                user.setCountry(country);
                user.setPhone(phone);
                user.setEnabled(true);

                UserRole userRole = new UserRole();
                userRole.setId(null);
                userRole.setUsername(username);
                userRole.setUserRole("ROLE_USER");

                userRepository.save(user);
                userRoleRepository.save(userRole);

                Messages.addMessage("Register succesfull");

                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

                HttpServletRequest httpServletRequest = (HttpServletRequest) externalContext.getRequest();
                try {
                    autoLogin(httpServletRequest,username,password);
                } catch (ServletException e) {
                    e.printStackTrace();
                }

                externalContext.redirect("/index.xhtml");
            } else {
                Messages.addMessage("Register unsuccesfull");
            }
        } else {
            Messages.addMessage("Register unsuccesfull");
        }
    }

    public void autoLogin(HttpServletRequest request, String username, String password) throws ServletException {
        request.login(username,password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterController that = (RegisterController) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(country, that.country) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(userRepository, that.userRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, country, phone, email, userRepository);
    }
}
