package com.example.demo.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "assignment_user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    @NotNull
    private String userRole;

    @Column(unique = true, name = "username")
    @NotNull
    private String username;

    public UserRole() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userRole='" + userRole + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole1 = (UserRole) o;
        return id == userRole1.id &&
                Objects.equals(userRole, userRole1.userRole) &&
                Objects.equals(username, userRole1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRole, username);
    }
}
