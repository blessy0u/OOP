package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.product.ProductType;

public class MeatShelf extends Shelf {

    public MeatShelf() {
        super(ShelfType.MEAT_SHELF);
    }

    @Override
    public void addProduct(Product product) throws InvalidProductTypeException {
        if (product.getType() != ProductType.MEAT) {
            throw new InvalidProductTypeException("Продукт не является мясным.");
        }
        logAddProduct(product);
        products.add(product);
    }
}
