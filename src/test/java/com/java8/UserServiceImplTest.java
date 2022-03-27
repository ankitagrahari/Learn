package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    private UserService userService;

    private static final List<Privilege> ALL_PRIVILEGES = Arrays.asList(Privilege.values());

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @AfterEach
    void addSeparator() {
        System.out.println("---------------------------------------------->>");
    }

    @Test
    @DisplayName("Sort Users by Name in ascending and then by Age in descending order")
    void sortByAgeDescAndNameAsc() {
        final User user1 = new User(1L, "John", "Doe", 26, ALL_PRIVILEGES);
        final User user2 = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User user3 = new User(3L, "Alex", "Smith", 13, ALL_PRIVILEGES);
        final User user4 = new User(4L, "Dynamic", "Blunt", 30, Collections.singletonList(Privilege.READ));
        final User user5 = new User(5L, "Dynamic", "BluntTech", 30, ALL_PRIVILEGES);

        final List<User> sortedUsers =
                userService.sortByAgeDescAndNameAsc(Arrays.asList(user1, user2, user3, user4, user5));
        System.out.println(sortedUsers);
        Assertions.assertArrayEquals(sortedUsers.toArray(), new User[]{ user4, user5, user2, user1, user3});
    }

    @Test
    @DisplayName("Return all distinct privilege in the list")
    void getAllDistinctPrivileges() {
        final User createUser = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30, Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20, Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        final List<Privilege> distinctPrivileges =
                userService.getAllDistinctPrivileges(Arrays.asList(createUser, updateUser, updateUser1, deleteUser));
        System.out.println(distinctPrivileges);
        Assertions.assertEquals(distinctPrivileges.size(), 3);
    }

    @Test
    @DisplayName("Return User with higher age than X of Update Privilege")
    void getUpdateUserWithAgeHigherThan() {
        final User updateUser = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(2L, "Greg", "Smith", 30, Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        Optional<User> user = userService.getUpdateUserWithAgeHigherThan(
                Arrays.asList(updateUser1, updateUser, deleteUser), 29
        );
        System.out.println(user.get());
        Assertions.assertEquals(user.get(), updateUser1);
    }

    @Test
    @DisplayName("Count of Users for each Privilege")
    void groupByCountOfPrivileges() {
        final int ONE_PRIVILEGE = 1;
        final int TWO_PRIVILEGES = 2;
        final int FOUR_PRIVILEGES = 4;

        final User userWith2Privileges = new User(1L, "John", "Doe", 26, Arrays.asList(Privilege.UPDATE, Privilege.CREATE));
        final User userWith4Privileges = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User userWith4Privileges1 = new User(2L, "Anton", "Smith", 34, ALL_PRIVILEGES);
        final User userWith1Privileges1 = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));
        final User userWith1Privileges2 = new User(3L, "Neo", "Smith", 13, Collections.singletonList(Privilege.UPDATE));

        Map<Integer, List<User>> privilegesCountUsersMap = userService.groupByCountOfPrivileges(
                Arrays.asList(userWith1Privileges1, userWith1Privileges2, userWith2Privileges, userWith4Privileges, userWith4Privileges1)
        );
        System.out.println(privilegesCountUsersMap);

        Assertions.assertArrayEquals(
                new int[]{2, 1, 2},
                new int[]{
                        privilegesCountUsersMap.get(ONE_PRIVILEGE).size(),
                        privilegesCountUsersMap.get(TWO_PRIVILEGES).size(),
                        privilegesCountUsersMap.get(FOUR_PRIVILEGES).size()
                }
        );
    }

    @Test
    @DisplayName("Count of Privileges per User")
    void countOfPrivilegesPerUser() {
        final User user1With2Privileges = new User(1L, "John", "Doe", 26, Arrays.asList(Privilege.UPDATE, Privilege.CREATE));
        final User user2With4Privileges = new User(2L, "Greg", "Smith", 30, ALL_PRIVILEGES);
        final User user3With4Privileges = new User(2L, "Anton", "Smith", 34, ALL_PRIVILEGES);
        final User user4With1Privilege = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));
        final User user5With1Privilege = new User(3L, "Neo", "Smith", 13, Collections.singletonList(Privilege.UPDATE));

        Map<User, Integer> privilegesCountPerUserMap = userService.countOfPrivilegesPerUser(
                Arrays.asList(user4With1Privilege, user5With1Privilege, user1With2Privileges, user2With4Privileges, user3With4Privileges)
        );

        System.out.println(privilegesCountPerUserMap);
        Assertions.assertArrayEquals(
                new int[]{2, 4, 4, 1, 1},
                new int[]{
                        privilegesCountPerUserMap.get(user1With2Privileges),
                        privilegesCountPerUserMap.get(user2With4Privileges),
                        privilegesCountPerUserMap.get(user3With4Privileges),
                        privilegesCountPerUserMap.get(user4With1Privilege),
                        privilegesCountPerUserMap.get(user5With1Privilege)
                }
        );
    }

    @Test
    @DisplayName("Return average age of Users")
    void getAverageAgeForUsers() {
        final User createUser = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30, Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20, Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        double averageAge = userService.getAverageAgeForUsers(
                Arrays.asList(createUser, updateUser, updateUser1, deleteUser)
        );

        System.out.println(averageAge);
        Assertions.assertEquals(averageAge, 22.25);
    }

    @Test
    @DisplayName("Return most frequent occurring last name from the list")
    void getMostFrequentLastName() {
        final User createUser = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.CREATE));
        final User updateUser = new User(2L, "Greg", "Smith", 30, Collections.singletonList(Privilege.UPDATE));
        final User updateUser1 = new User(3L, "Greg", "Smith", 20, Collections.singletonList(Privilege.UPDATE));
        final User deleteUser = new User(4L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        String lastName = userService.getMostFrequentLastName(
                Arrays.asList(createUser, updateUser, updateUser1, deleteUser)
        ).get();

        System.out.println(lastName);
        Assertions.assertEquals(lastName, "Smith");
    }

    @Test
    @DisplayName("Filter Users by first name length > 3 and privileges > 2")
    void filterBy() {
        final User user1 = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, Arrays.asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        Predicate<User> firstNameLongerThan3 = user -> user.getFirstName().length()>3;
        Predicate<User> privilegeGreaterThan2 = user -> user.getPrivileges().size()>2;

        List<User> users = userService.filterBy(
                Arrays.asList(user1, user2, user3),
                firstNameLongerThan3,
                privilegeGreaterThan2
        );

        System.out.println(users);
        Assertions.assertEquals(users.size(), 1);
    }

    @Test
    @DisplayName("Return same list if found no filterBy conditions(predicates)")
    void emptyFilter() {
        final User user1 = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, Arrays.asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        List<User> users = userService.filterBy(
                Arrays.asList(user1, user2, user3)
        );

        System.out.println(users);
        Assertions.assertEquals(users.size(), 3);
    }

    @Test
    @DisplayName("Convert Users by applying Function and delimited by |")
    void convertTo() {
        final User user1 = new User(1L, "John", "Doe", 26, Collections.emptyList());
        final User user2 = new User(2L, "Greg", "Jonson", 30, Collections.emptyList());
        final User user3 = new User(3L, "Alex", "Smith", 13, Collections.emptyList());

        Function<User, String> function = User::getLastName;

        String convertedStr = userService.convertTo(
                Arrays.asList(user1, user2, user3),
                "|",
                function
        );

        System.out.println(convertedStr);
        Assertions.assertEquals(convertedStr, "Doe|Jonson|Smith");
    }

    @Test
    void groupByPrivileges() {
        User user1 = new User(1L, "John", "Doe", 26, Collections.singletonList(Privilege.UPDATE));
        User user2 = new User(2L, "Greg", "Jonson", 30, Arrays.asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        User user3 = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        Map<Privilege, List<User>> privilegesMap = userService.groupByPrivileges(
                new ArrayList<>(Arrays.asList(user1, user2, user3))
        );

        Assertions.assertArrayEquals(
                new int[]{0, 1, 2, 2},
                new int[]{
                        privilegesMap.containsKey(Privilege.READ) ? privilegesMap.get(Privilege.READ).size() : 0,
                        privilegesMap.containsKey(Privilege.CREATE) ? privilegesMap.get(Privilege.CREATE).size() : 0,
                        privilegesMap.containsKey(Privilege.UPDATE) ? privilegesMap.get(Privilege.UPDATE).size() : 0,
                        privilegesMap.containsKey(Privilege.DELETE) ? privilegesMap.get(Privilege.DELETE).size() : 0
                }
        );
    }

    @Test
    @DisplayName("Return count on group by Last Name")
    void getNumberOfLastNames() {
        final User user1 = new User(1L, "John", "Smith", 26, Collections.singletonList(Privilege.UPDATE));
        final User user2 = new User(2L, "Greg", "Jonson", 30, Arrays.asList(Privilege.UPDATE, Privilege.CREATE, Privilege.DELETE));
        final User user3 = new User(3L, "Alex", "Smith", 13, Collections.singletonList(Privilege.DELETE));

        final Map<String, Long> numberOfLastNames = userService.getNumberOfLastNames(Arrays.asList(user1, user2, user3));
        System.out.println(numberOfLastNames);
        Assertions.assertArrayEquals(
                new Long[]{2L, 1L},
                new Long[]{
                        numberOfLastNames.get("Smith"),
                        numberOfLastNames.get("Jonson")
                }
        );
    }
}