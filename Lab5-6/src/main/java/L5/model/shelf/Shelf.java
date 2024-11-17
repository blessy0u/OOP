package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Shelf {
    protected List<Product> products = new ArrayList<>();
    protected ShelfType shelfType;
    private static final Logger logger = LoggerFactory.getLogger(Shelf.class);

    public Shelf(ShelfType shelfType) {
        this.shelfType = shelfType;
    }

    public ShelfType getShelfType() {
        return shelfType;
    }

    public abstract void addProduct(Product product) throws InvalidProductTypeException;

    public List<Product> getProducts() {
        return products;
    }

    public void clearShelf() {
        products.clear();
    }

    protected void logAddProduct(Product product) {
        logger.info("Кладем продукт на " + shelfType.toValue() + " полку: " + product.getName());
    }
}