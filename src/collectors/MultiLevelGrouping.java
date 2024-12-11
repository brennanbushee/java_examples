package collectors;

import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    String department;
    String role;

    Employee(String name, String department, String role) {
        this.name = name;
        this.department = department;
        this.role = role;
    }
}

public class MultiLevelGrouping {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", "Developer"),
                new Employee("Bob", "Engineering", "Developer"),
                new Employee("Charlie", "Engineering", "Manager"),
                new Employee("David", "HR", "Manager"),
                new Employee("Eve", "HR", "Recruiter")
        );

        Map<String, Map<String, List<String>>> groupedByDeptAndRole = employees.stream()
                .collect(Collectors.groupingBy(
                        e -> e.department,
                        Collectors.groupingBy(e -> e.role,
                                Collectors.mapping(e -> e.name, Collectors.toList())
                        )
                ));

        System.out.println(groupedByDeptAndRole);
        // Output: {HR={Manager=[David], Recruiter=[Eve]}, Engineering={Developer=[Alice, Bob], Manager=[Charlie]}}
    }
}
