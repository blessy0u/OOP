package L5.model.shelf;

public enum ShelfType {
    MEAT_SHELF("мясную"),
    DAIRY_SHELF("молочную"),
    BEVERAGE_SHELF("питьевую"),
    SWEET_SHELF("сладкую"),
    VEGETARIAN_SHELF("вегетарианскую");

    private final String displayName;

    ShelfType(String displayName) {
        this.displayName = displayName;
    }

    public String toValue() {
        return displayName;
    }
}
