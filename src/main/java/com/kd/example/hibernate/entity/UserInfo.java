/**
 * 
 */
package com.kd.example.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/** @author kuldeep.singh */
@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "USER_NAME", nullable = false, unique = true)
    private String username;

    @Column(name = "FIRST_NAME", nullable = false, unique = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, unique = false)
    private String lastName;

    @Column(name = "AGE", nullable = true, unique = false)
    private int age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Address.class)
    private Address address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
