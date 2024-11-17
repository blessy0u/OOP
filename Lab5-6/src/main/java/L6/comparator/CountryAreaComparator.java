package L6.comparator;

import L6.model.Country;

import java.util.Comparator;

/**
 * Сравнитель для стран по площади
 */
public class CountryAreaComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}

