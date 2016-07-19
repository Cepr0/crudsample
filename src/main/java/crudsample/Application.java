package crudsample;

import crudsample.console.ConsoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Cepro on 15.07.2016.
 */
@SpringBootApplication
@EnableJpaRepositories
public class Application {

    @Autowired
    ConsoleView consoleView;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
