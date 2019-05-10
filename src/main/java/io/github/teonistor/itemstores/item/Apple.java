package io.github.teonistor.itemstores.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Apple implements Item {

    private final float price;

    public Apple(final @Value("1.1") float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String simpleName() {
        return "apple";
    }

    @Override
    public String toString() {
        return String.format("Apple{£%.02f}", price);
    }
}
