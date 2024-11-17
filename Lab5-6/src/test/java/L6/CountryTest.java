package L6;

import L6.model.Country;
import L6.comparator.CountryAreaComparator;
import L6.comparator.CountryPopulationComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CountryTest {

    @Test
    @DisplayName("Сортировка только по плотности.")
    void testDensityCalculation() {
        Country country = new Country().setName("CountryA").setArea(1000).setPopulation(500000);

        assertThat(country.getDensity()).isEqualTo(500.0);
    }

    @Test
    @DisplayName("Сортировка только по площади.")
    void testSortByArea() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country().setName("CountryA").setArea(1000).setPopulation(500000));
        countries.add(new Country().setName("CountryB").setArea(2000).setPopulation(300000));
        countries.add(new Country().setName("CountryC").setArea(1500).setPopulation(700000));

        Collections.sort(countries, new CountryAreaComparator());

        assertThat(countries.get(0).getName()).isEqualTo("CountryA");
        assertThat(countries.get(1).getName()).isEqualTo("CountryC");
        assertThat(countries.get(2).getName()).isEqualTo("CountryB");
    }

    @Test
    @DisplayName("Сортировка только по населению.")
    void testSortByPopulation() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country().setName("CountryA").setArea(1000).setPopulation(500000));
        countries.add(new Country().setName("CountryB").setArea(2000).setPopulation(300000));
        countries.add(new Country().setName("CountryC").setArea(1500).setPopulation(700000));

        Collections.sort(countries, new CountryPopulationComparator());

        assertThat(countries.get(0).getName()).isEqualTo("CountryB");
        assertThat(countries.get(1).getName()).isEqualTo("CountryA");
        assertThat(countries.get(2).getName()).isEqualTo("CountryC");
    }
}
