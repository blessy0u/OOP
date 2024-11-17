package L5.service;

import L5.exception.OutOfStockException;
import L5.model.product.Product;
import L5.model.product.ProductType;
import L5.model.shelf.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShelfManagerTest {

    private static final Map<String, Shelf> shelves = new HashMap<>();
    private final ShelfManager shelfManager = new ShelfManager(shelves);

    @BeforeAll
    static void setUp() {
        shelves.put(ShelfType.DAIRY_SHELF.toValue(), new DairyShelf());
        shelves.put(ShelfType.MEAT_SHELF.toValue(), new MeatShelf());
        shelves.put(ShelfType.VEGETARIAN_SHELF.toValue(), new VegetarianShelf());
    }

    @AfterEach
    void clearShelves() {
        shelves.values().forEach(Shelf::clearShelf);
    }

    @Test
    @DisplayName("Проверка, что продукты добавляются на правильные полки")
    void addProductsToCorrectShelvesTest() {
        Product dairyProduct = new Product().setName("Йогурт").setType(ProductType.DAIRY).setQuantity(5);
        Product meatProduct = new Product().setName("Стейк").setType(ProductType.MEAT).setQuantity(3);

        assertDoesNotThrow(() -> shelfManager.putProductOnShelf(dairyProduct, shelves.get(ShelfType.DAIRY_SHELF.toValue())));
        assertDoesNotThrow(() -> shelfManager.putProductOnShelf(meatProduct, shelves.get(ShelfType.MEAT_SHELF.toValue())));

        assertEquals(1, shelves.get(ShelfType.DAIRY_SHELF.toValue()).getProducts().size());
        assertEquals(1, shelves.get(ShelfType.MEAT_SHELF.toValue()).getProducts().size());
    }

    @Test
    @DisplayName("Проверка, что неправильный тип продукта не добавляется на полку")
    void addIncorrectProductTypeToShelfTest() {
        Product vegetableProduct = new Product().setName("Морковка").setType(ProductType.VEGETARIAN).setQuantity(10);
        Product dairyProduct = new Product().setName("Крем").setType(ProductType.DAIRY).setQuantity(5);

        assertDoesNotThrow(() -> shelfManager.putProductOnShelf(vegetableProduct, shelves.get(ShelfType.VEGETARIAN_SHELF.toValue())));

        assertThrows(OutOfStockException.class, () -> shelfManager.putProductOnShelf(dairyProduct, shelves.get(ShelfType.VEGETARIAN_SHELF.toValue())));
        assertEquals(1, shelves.get(ShelfType.VEGETARIAN_SHELF.toValue()).getProducts().size());
    }
}