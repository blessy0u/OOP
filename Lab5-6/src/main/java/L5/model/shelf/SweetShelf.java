package L5.model.shelf;

import L5.exception.InvalidProductTypeException;
import L5.model.product.Product;
import L5.model.product.ProductType;

public class SweetShelf extends Shelf {

    public SweetShelf() {
        super(ShelfType.SWEET_SHELF);
    }

    @Override
    public void addProduct(Product product) throws InvalidProductTypeException {
        if (product.getType() != ProductType.SWEET) {
            throw new InvalidProductTypeException("Продукт не является кондитерским.");
        }
        logAddProduct(product);
        products.add(product);
    }
}
