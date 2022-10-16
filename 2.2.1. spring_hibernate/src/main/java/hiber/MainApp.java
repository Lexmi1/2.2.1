package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car lada = new Car("Lada", 69);
        Car toyota = new Car("Toyota", 14);

        User user1 = new User("Sasha", "Strukov", "Sasha@mail.ru");
        user1.setCar(lada);

        User user2 = new User("Vlad", "Tsel", "Vlad@mail.ru");
        user2.setCar(toyota);

        userService.add(user1);
        userService.add(user2);

        System.out.println("---------------------------------------");

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        System.out.println("---------------------------------------");

        try {
            User userGetByCar = userService.getUserByCar("Lada", 69);
            System.out.println(userGetByCar);
        } catch (Exception e) {
            System.out.println("There is no user with such a machine in the database");
        }
        context.close();
    }
}
