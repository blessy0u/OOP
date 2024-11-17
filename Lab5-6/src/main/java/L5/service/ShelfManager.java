package L5.service;

import L5.exception.OutOfStockException;
import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.shelf.Shelf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ShelfManager {
    private final Map<String, Shelf> shelves;
    private static final Logger logger = LoggerFactory.getLogger(ShelfManager.class);

    public ShelfManager(Map<String, Shelf> shelves) {
        this.shelves = shelves;
    }

    public void putProductOnShelf(Product product, Shelf shelf) throws OutOfStockException {
        logger.info("Кладем продукт на " + shelf.getShelfType().toValue() + " полку..");
        try {
            shelf.addProduct(product);
            logger.info("Менеджер положил продукт на полку!");
        } catch (InvalidProductTypeException e) {
            logger.error("Во время выполнения функции произошла ошибка: " + e.getMessage());
            throw new OutOfStockException("Не удалось добавить продукт на полку: " + e.getMessage());
        }
    }
}
