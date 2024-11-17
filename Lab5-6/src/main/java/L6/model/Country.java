package L6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Country {
    private String name;
    private double area; // Площадь в квадратных километрах
    private long population; // Население

    public double getDensity() {
        return population / area; // Плотность населения
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Double.compare(country.area, area) == 0 &&
                population == country.population &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, population);
    }
}
