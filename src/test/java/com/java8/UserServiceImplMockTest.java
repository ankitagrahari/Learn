package com.java8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    Faker faker = new Faker();
    @Mock UserServiceImpl userService;

//    UserServiceImplMockTest(){
//
//        // MockitoAnnotations.openMocks(this); initializes fields annotated with Mockito annotations.
//        // - Allows shorthand creation of objects required for testing.
//        // - Minimizes repetitive mock creation code.
//        // - Makes the test class more readable.
//        // - Makes the verification error easier to read because field name is used to identify the mock.
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void sortByAgeDescAndNameAsc() {

        List<User> users = Arrays.asList(
                new User(faker.number().randomNumber(),
                        faker.name().firstName(), faker.name().lastName(), 50,
                        Arrays.asList(Privilege.CREATE, Privilege.DELETE)),
                new User(faker.number().randomNumber(),
                        faker.name().firstName(), faker.name().lastName(), 45,
                        Arrays.asList(Privilege.CREATE, Privilege.READ)),
                new User(faker.number().randomNumber(),
                        faker.name().firstName(), faker.name().lastName(), 32,
                        Arrays.asList(Privilege.READ))
        );
        when(userService.sortByAgeDescAndNameAsc(new ArrayList<>()))
                .thenReturn(users);

        List<User> sortedUsers = userService.sortByAgeDescAndNameAsc(new ArrayList<>());

        assertEquals(50, sortedUsers.get(0).getAge());
        assertEquals(32, sortedUsers.get(2).getAge());
    }

    @Test
    void getAllDistinctPrivileges() {

        List<Privilege> privileges = Arrays.asList(Privilege.READ, Privilege.CREATE, Privilege.DELETE);
        when(userService.getAllDistinctPrivileges(null)).thenReturn(privileges);

        assertEquals(Privilege.READ, userService.getAllDistinctPrivileges(null).get(0));
        assertEquals(Privilege.CREATE, userService.getAllDistinctPrivileges(null).get(1));
        assertEquals(Privilege.DELETE, userService.getAllDistinctPrivileges(null).get(2));


//        Arrays.asList(
//                new User(faker.number().randomNumber(),
//                        faker.name().firstName(), faker.name().lastName(),
//                        faker.number().numberBetween(1, 100),
//                        Arrays.asList(Privilege.CREATE, Privilege.DELETE)),
//                new User(faker.number().randomNumber(),
//                        faker.name().firstName(), faker.name().lastName(),
//                        faker.number().numberBetween(1, 100),
//                        Arrays.asList(Privilege.CREATE, Privilege.UPDATE, Privilege.READ)),
//                new User(faker.number().randomNumber(),
//                        faker.name().firstName(), faker.name().lastName(),
//                        faker.number().numberBetween(1, 100),
//                        Arrays.asList(Privilege.READ))
//        );


    }

    @Test
    void getUpdateUserWithAgeHigherThan() {
        List<User> users = Arrays.asList(
                new User(faker.number().randomNumber(),
                        "Dynamically", faker.name().lastName(),
                        faker.number().numberBetween(30, 100),
                        Arrays.asList(Privilege.CREATE, Privilege.UPDATE)),
                new User(faker.number().randomNumber(),
                        "Blunt-tech", faker.name().lastName(),
                        faker.number().numberBetween(30, 100),
                        Arrays.asList(Privilege.CREATE, Privilege.UPDATE, Privilege.READ)),
                new User(faker.number().randomNumber(),
                        faker.name().firstName(), faker.name().lastName(),
                        faker.number().numberBetween(1, 100),
                        Arrays.asList(Privilege.READ))
        );

        when(userService.getUpdateUserWithAgeHigherThan(anyList(), anyInt()))
                .thenReturn(Optional.ofNullable(users.get(0)))
                .thenReturn(Optional.ofNullable(users.get(1)));

        assertEquals("Dynamically",
                userService.getUpdateUserWithAgeHigherThan(Collections.emptyList(), 30).get().getFirstName());
        assertEquals("Blunt-tech",
                userService.getUpdateUserWithAgeHigherThan(new ArrayList<>(), 30).get().getFirstName());
    }

    @Test
    void getUpdateUserWithAgeHigherThan_Exception() {

        when(userService.getUpdateUserWithAgeHigherThan(null, 30))
                .thenThrow(RuntimeException.class);

        assertThrows(
                RuntimeException.class,
                () -> {userService.getUpdateUserWithAgeHigherThan(null, 30);},
                "Users list is empty. RuntimeException expected!!");

    }

    @Test
    void getMostFrequentLastName(){
        String lastName = "Singh";

        // Defined Expected Behaviour
        given(userService.getMostFrequentLastName(anyList())).willReturn(Optional.of(lastName));

        // When method is called
        // First Invocation
        userService.getMostFrequentLastName(Collections.emptyList()).get();

        // Then Verify, how many times it is called.
        verify(userService, times(1)).getMostFrequentLastName(anyList());

        // Returns true, as method is invoked at-least once.
        verify(userService, atLeastOnce()).getMostFrequentLastName(anyList());

        // Return true, as method is invoked max of 1 time.
        verify(userService, atMostOnce()).getMostFrequentLastName(anyList());

        // Second Invocation
        userService.getMostFrequentLastName(anyList());

        // Return true, as method is invoked at-least 2 times.
        verify(userService, atLeast(2)).getMostFrequentLastName(anyList());

        // Return true, as given method is never invoked.
        verify(userService, never()).getAverageAgeForUsers(any());
    }
}