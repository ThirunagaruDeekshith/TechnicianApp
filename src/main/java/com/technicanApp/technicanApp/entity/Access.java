package com.technicanApp.technicanApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="authorities")
public class Access {
    @Id
    @Column(name = "username")
    private String customer_name;
    @Column(name = "authority")
    private String authority;
    public Access(){}

    public Access(String customer_name, String authority) {
        customer_name = customer_name;
        this.authority = authority;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
