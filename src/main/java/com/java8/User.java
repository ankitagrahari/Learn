package com.java8;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Privilege> privileges;

    public User(final Long id,
                final String firstName,
                final String lastName,
                final Integer age,
                final List<Privilege> privileges) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.privileges = Collections.unmodifiableList(privileges);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString(){
        return this.id+"_"
                + this.firstName + "_" + this.lastName
                + "_" + this.age + "_("
                + ((this.privileges.size()==4)
                    ? "All"
                    : this.privileges.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(","))
                )
                + ")";
    }
}
