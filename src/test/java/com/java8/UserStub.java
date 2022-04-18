package com.java8;

import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;

public class UserStub {

    Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }

    public Integer getAge(){
        return faker.number().numberBetween(1, 100);
    }

    public List<Privilege> getReadPrivileges(){
        return Arrays.asList(Privilege.READ);
    }

    public List<Privilege> getCreateUpdatePrivileges(){
        return Arrays.asList(Privilege.READ, Privilege.UPDATE, Privilege.CREATE);
    }

    public List<Privilege> getCRUDPrivileges(){
        return Arrays.asList(Privilege.READ, Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE);
    }

    public User getDummyUserWithAllPrivileges(){
        return new User(faker.number().randomNumber(),
                getFirstName(), getLastName(), getAge(),
                getCRUDPrivileges());
    }

    public User getDummyUserWithCreatePrivileges(){
        return new User(faker.number().randomNumber(),
                getFirstName(), getLastName(), getAge(),
                getCreateUpdatePrivileges());
    }

    public User getDummyUserWithReadPrivileges(){
        return new User(faker.number().randomNumber(),
                getFirstName(), getLastName(), getAge(),
                getReadPrivileges());
    }

    public List<User> getDummyUsersWithReadPrivileges(){
        return Arrays.asList(
                new User(faker.number().randomNumber(),
                        getFirstName(), getLastName(), getAge(),
                        getReadPrivileges()),
                new User(faker.number().randomNumber(),
                        getFirstName(), getLastName(), getAge(),
                        getReadPrivileges()),
                new User(faker.number().randomNumber(),
                        getFirstName(), getLastName(), getAge(),
                        getReadPrivileges())
                );
    }

    public List<User> getDummyUsersWithAllPrivileges(){
        return Arrays.asList(
                new User(faker.number().randomNumber(),
                        "dynamically", "blunt", 50,
                        getCRUDPrivileges()),
                new User(faker.number().randomNumber(),
                        "dynamically", "blunttech", 32,
                        getCRUDPrivileges()),
                new User(faker.number().randomNumber(),
                        "Dynamically", "BluntTech", 32,
                        getCRUDPrivileges())
                );
    }
}
