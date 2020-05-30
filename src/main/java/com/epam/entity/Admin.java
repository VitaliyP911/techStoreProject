package com.epam.entity;

import java.util.Objects;

public class Admin  extends Entity{

    private String email;

    public Admin(Long id, String email) {
        super(id);
        this.email = email;
    }

    public Admin(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(email, admin.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "email='" + email + '\'' +
                '}';
    }
}
