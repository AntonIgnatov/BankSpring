package net.bigmir.venzor;

import net.bigmir.venzor.enteties.Transactions;
import net.bigmir.venzor.enteties.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final BankService bankService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                bankService.init();
            }
        };
    }
}