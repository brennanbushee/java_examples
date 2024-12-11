package collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Car {
    String name;
    String color;

    Car(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("Toyota", "Red"),
                new Car("Honda", "Blue"),
                new Car("Ford", "Red"),
                new Car("BMW", "Black"),
                new Car("Audi", "Blue"),
                new Car("Mercedes", "Black"),
                new Car("Tesla", "Red")
        );

        // Group by color and count the number of cars in each color
        Map<String, Long> colorCounts = cars.stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));

        List<String> redCars = cars.stream()
                .filter(car -> "RED".equalsIgnoreCase(car.getColor()))
                .map(Car::getName)
                .toList();
        redCars.forEach(System.out::println);
        System.out.println(colorCounts);
    }
}
