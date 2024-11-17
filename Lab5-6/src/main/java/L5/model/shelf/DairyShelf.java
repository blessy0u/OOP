package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.product.ProductType;

public class DairyShelf extends Shelf {

    public DairyShelf() {
        super(ShelfType.DAIRY_SHELF);
    }

    @Override
    public void addProduct(Product product) throws InvalidProductTypeException {
        if (product.getType() != ProductType.DAIRY) {
            throw new InvalidProductTypeException("Продукт не является молочным.");
        }
        logAddProduct(product);
        products.add(product);
    }
}
