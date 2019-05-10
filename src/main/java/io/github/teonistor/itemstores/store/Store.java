package io.github.teonistor.itemstores.store;


import io.github.teonistor.itemstores.item.Item;

public class Store {

    private final Item item;

    public Store(final Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return String.format("Store{item=%s}", item);
    }
}
