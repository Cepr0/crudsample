package crudsample.console;

import crudsample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Cepro on 16.07.2016.
 */

@Component
public class ConsoleViewImpl extends Thread implements ConsoleView {

    private final UserService userService;

    @Autowired
    public ConsoleViewImpl(UserService userService) {
        this.userService = userService;
        start();
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ignore) {}
//
//        if (userService != null) {
//            System.out.println();
//            System.out.println("-------------------------------------");
//            User user = new User("Qwerty", 18);
//
//            userService.save(user);
//            System.out.println("Added: " + user);
//
//            System.out.println();
//            userService.getAll().forEach(System.out::println);
//            System.out.println("-------------------------------------");
//            System.out.println();
//
//        } else {
//            System.out.println("-------------------------------------");
//            System.out.println("userService is null");
//            System.out.println("-------------------------------------");
//        }
    }
}
