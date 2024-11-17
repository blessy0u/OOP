package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.product.ProductType;

public class VegetarianShelf extends Shelf {

    public VegetarianShelf() {
        super(ShelfType.VEGETARIAN_SHELF);
    }

    @Override
    public void addProduct(Product product) throws InvalidProductTypeException {
        if (product.getType() != ProductType.VEGETARIAN) {
            throw new InvalidProductTypeException("Продукт не является вегетарианским.");
        }
        logAddProduct(product);
        products.add(product);
    }
}
