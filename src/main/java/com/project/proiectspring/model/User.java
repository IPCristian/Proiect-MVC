package com.project.proiectspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static java.lang.System.in;


@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final Set<String> admins = new HashSet<>();

    static {
        admins.add("emailadmin@gmail.com");
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="phone_number")
    @NotNull
    private String phoneNumber;

    @Column(name="password")
    @JsonIgnore
    @NotNull
    private String password;

    public User() {
    }

    public User(String lastName, String firstName, String email, String phoneNumber, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(long id, String lastName, String firstName, String email, String phoneNumber, String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (admins.contains(getEmail()))
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @JsonIgnore
    public boolean isAdmin() {
        for (GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().contains("ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public void grantAccess() {
        admins.add(this.getEmail());
    }

    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
