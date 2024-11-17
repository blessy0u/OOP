package L5.service;


import L5.exception.OutOfStockException;
import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.shelf.Shelf;

public class ProductService {
    public void addProductToShelf(Product product, Shelf shelf) {
        try {
            shelf.addProduct(product);
        } catch (InvalidProductTypeException e) {
            throw new OutOfStockException("Не удалось добавить продукт на полку: " + e.getMessage());
        }
    }
}
