package L5.model.product;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Product {
    private String name;
    private ProductType type;
    private int quantity;
}
