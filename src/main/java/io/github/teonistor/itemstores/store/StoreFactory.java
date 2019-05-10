package io.github.teonistor.itemstores.store;

import io.github.teonistor.itemstores.item.Item;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Component
public class StoreFactory {

    private final List<Item> items;

    public StoreFactory(final List<Item> items) {
        this.items = items;
    }

    public Store createStore(final String itemSimpleName) {
        return items.stream()
                    .filter(item -> itemSimpleName.equals(item.simpleName()))
                    .map(Store::new)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
    }

    public List<Store> createManyStores(final String... itemSimpleNames) {
        return stream(itemSimpleNames).map(this::createStore).collect(toList());
    }
}
