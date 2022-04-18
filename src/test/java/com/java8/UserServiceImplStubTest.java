package com.java8;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class UserServiceImplStubTest {

    UserStub userStub = new UserStub();
    UserService userService = new UserServiceImpl();

    @Test
    public void sortByAgeDescAndNameAsc() {
        List<User> users = userStub.getDummyUsersWithAllPrivileges();
        List<User> sortedUsers = userService.sortByAgeDescAndNameAsc(users);

        assertEquals(50, sortedUsers.get(0).getAge());
        assertEquals("dynamically", sortedUsers.get(0).getFirstName());
        assertEquals("blunt", sortedUsers.get(0).getLastName());

        assertEquals(32, sortedUsers.get(2).getAge());
        assertEquals("dynamically", sortedUsers.get(2).getFirstName());
        assertEquals("blunttech", sortedUsers.get(2).getLastName());
    }

    @Test
    public void getAverageAgeOfUsers(){
        List<User> users = userStub.getDummyUsersWithAllPrivileges();
        assertEquals(38, userService.getAverageAgeForUsers(users));
    }

    @Test
    public void countOfPrivilegesPerUser(){
        List<User> users = userStub.getDummyUsersWithReadPrivileges();
        Map<User, Integer> map = userService.countOfPrivilegesPerUser(users);
        Iterator<Map.Entry<User, Integer>> itr = map.entrySet().iterator();

        assertEquals(1, itr.next().getValue());
        assertEquals(1, itr.next().getValue());
        assertEquals(1, itr.next().getValue());
    }
}