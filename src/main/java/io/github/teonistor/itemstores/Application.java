package io.github.teonistor.itemstores;

import io.github.teonistor.itemstores.store.StoreFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] arg) {
        new SpringApplication(Application.class).run(arg);
    }

    public Application(final StoreFactory storeFactory) {

        System.out.println(storeFactory.createManyStores(
                // Type your item names here
                "apple",
                "apple",
                "pear",
                "apple",
                "pear",
                "pear"
        ));
    }
}
