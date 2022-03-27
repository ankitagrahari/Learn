package com.java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserServiceImpl implements UserService{

    @Override
    public List<User> sortByAgeDescAndNameAsc(List<User> users) {
        return users.stream()
                .sorted(
                        Comparator.comparing(User::getFirstName)
                                .thenComparing(User::getLastName)
                )
                .peek(System.out::print)
                .sorted(
                        Comparator.comparingInt(User::getAge)
                                .reversed()
                )
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    @Override
    public List<Privilege> getAllDistinctPrivileges(List<User> users) {
        return users.stream()
                .map(user -> user.getPrivileges())
                .peek(System.out::print)
                .flatMap(List::stream)
                .distinct()
                .peek(System.out::println)
                .toList();
    }

    @Override
    public Optional<User> getUpdateUserWithAgeHigherThan(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge()>age && user.getPrivileges().contains(Privilege.UPDATE))
                .peek(System.out::println)
                .findFirst();           //Can use both findFirst() or findAny()
    }

    @Override
    public Map<Integer, List<User>> groupByCountOfPrivileges(List<User> users) {
        return users.stream()
                .collect(
                        Collectors.groupingBy(user -> user.getPrivileges().size())
                );
    }

    @Override
    public Map<User, Integer> countOfPrivilegesPerUser(List<User> users) {
        return users.stream()
                .collect(
                        Collectors.groupingBy(user -> user,
                                Collectors.summingInt(value -> value.getPrivileges().size()))
                );
    }

    @Override
    public double getAverageAgeForUsers(List<User> users) {
        return users.stream()
                .mapToDouble(User::getAge)
                .average()
                .getAsDouble();
    }

    @Override
    public Optional<String> getMostFrequentLastName(List<User> users) {
        Map<String, Long> map =
                users.stream()
                        .collect(Collectors.groupingBy(
                                User::getLastName,
                                Collectors.counting()
                        ));
        return Optional.of(
                map.entrySet()
                        .stream()
                        .max((e1, e2) -> e1.getValue()<e2.getValue()?-1:1)
                        .get()
                        .getKey()
        );
    }

    @Override
    public List<User> filterBy(List<User> users, Predicate<User>... predicates) {
        Predicate<User> allPredicates = Arrays.stream(predicates).reduce(w->true, Predicate::and);

        return users.stream()
                .filter(allPredicates)
                .collect(Collectors.toList());
    }

    @Override
    public String convertTo(List<User> users, String delimiter, Function<User, String> mapFun) {
        return users.stream()
                .map(mapFun)
                .peek(System.out::println)
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Referred this Post for Solution: https://stackoverflow.com/a/55145947
     * @param users list of users to group
     * @return
     */
    @Override
    public Map<Privilege, List<User>> groupByPrivileges(List<User> users) {
//        Map<Privilege, List<User>> map = new HashMap<>();
//        for(User user: users){
//            for(Privilege p: user.getPrivileges()){
//                if(map.containsKey(p)) {
//                    List<User> tmp = map.get(p);
//                    tmp.add(user);
//                    map.put(p, tmp);
//                } else {
//                    map.put(p, new ArrayList<>(Arrays.asList(user)));
//                }
//            }
//        }
//        return map;


        return users.stream()
                .flatMap(
                        user -> user.getPrivileges().stream()
                                .map(privilege -> new AbstractMap.SimpleEntry<>(privilege, user))
                )
                .peek(System.out::println)
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                        )
                );
    }

    @Override
    public Map<String, Long> getNumberOfLastNames(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(User::getLastName, Collectors.counting()));
    }
}
