package L5.model.product;

public enum ProductType {
    MEAT, DAIRY, BEVERAGE, SWEET, VEGETARIAN;

    public String toValue() {
        return this.name();
    }
}
