package L6.comparator;

import L6.model.Country;

import java.util.Comparator;

/**
 * Сравнитель для стран по населению
 */
public class CountryPopulationComparator implements Comparator<Country> {
    @Override
    public int compare(Country o1, Country o2) {
        return Long.compare(o1.getPopulation(), o2.getPopulation());
    }
}
