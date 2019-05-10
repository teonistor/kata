package io.github.teonistor.itemstores.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pear implements Item {

    private final float price;

    public Pear(final @Value("0.95") float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String simpleName() {
        return "pear";
    }

    @Override
    public String toString() {
        return String.format("Pear{£%.02f}", price);
    }
}
