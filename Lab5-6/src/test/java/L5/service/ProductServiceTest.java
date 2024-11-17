package L5.service;

import L5.exception.OutOfStockException;
import L5.model.product.Product;
import L5.model.product.ProductType;
import L5.model.shelf.DairyShelf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @Test
    @DisplayName("Добавление продукта на молочную полку с правильным типом")
    void addProductToDairyShelfPositiveTest() {
        Product product = new Product().setName("Сыр").setType(ProductType.DAIRY).setQuantity(10);
        DairyShelf shelf = new DairyShelf();

        assertDoesNotThrow(() -> productService.addProductToShelf(product, shelf));
        assertEquals(1, shelf.getProducts().size());
        assertEquals("Cheese", shelf.getProducts().get(0).getName());
    }

    @Test
    @DisplayName("Попытка добавить продукт на молочную полку с неправильным типом")
    void addProductToDairyShelfNegativeTest() {
        Product product = new Product().setName("Курица").setType(ProductType.MEAT).setQuantity(5);
        DairyShelf shelf = new DairyShelf();

        assertThrows(OutOfStockException.class, () -> productService.addProductToShelf(product, shelf));
        assertEquals(0, shelf.getProducts().size());
    }
}
