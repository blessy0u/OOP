package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.product.ProductType;

public class BeverageShelf extends Shelf {

    public BeverageShelf() {
        super(ShelfType.BEVERAGE_SHELF);
    }

    @Override
    public void addProduct(Product product) throws InvalidProductTypeException {
        if (product.getType() != ProductType.BEVERAGE) {
            throw new InvalidProductTypeException("Продукт не является напитком.");
        }
        logAddProduct(product);
        products.add(product);
    }
}
