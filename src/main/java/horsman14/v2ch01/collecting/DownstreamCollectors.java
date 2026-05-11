package horsman14.v2ch01.collecting;

import static java.util.stream.Collectors.*;

import module java.base;

record City(String name, String state, int population) {}

class DownstreamCollectors {

    void main() throws Exception {
        Map<String, Set<Locale>> countryToLocaleSet = Locale.availableLocales()
                .collect(groupingBy(Locale::getCountry, toSet()));
        IO.println("countryToLocaleSet: " + countryToLocaleSet);

        Map<String, Map<String, List<Locale>>> countryAndLanguageToLocale = Locale
                .availableLocales()
                .collect(groupingBy(Locale::getCountry, groupingBy(Locale::getLanguage)));
        IO.println(
            "Hindi locales in India: " + countryAndLanguageToLocale.get("IN").get("hi"));

        Map<String, Long> countryToLocaleCounts = Locale.availableLocales()
                .collect(groupingBy(Locale::getCountry, counting()));
        IO.println("countryToLocaleCounts: " + countryToLocaleCounts);

        Stream<City> cities = readCities("v2ch01/cities.txt");
        Map<String, Integer> stateToCityPopulation = cities
                .collect(groupingBy(City::state, summingInt(City::population)));
        IO.println("stateToCityPopulation: " + stateToCityPopulation);

        cities = readCities("v2ch01/cities.txt");
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(groupingBy(
            City::state, mapping(City::name, maxBy(Comparator.comparing(String::length)))));
        IO.println("stateToLongestCityName: " + stateToLongestCityName);

        Map<String, Set<String>> countryToLanguages = Locale.availableLocales()
                .collect(groupingBy(Locale::getDisplayCountry,
                    mapping(Locale::getDisplayLanguage, toSet())));
        IO.println("countryToLanguages: " + countryToLanguages);

        cities = readCities("v2ch01/cities.txt");
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
                .collect(groupingBy(City::state, summarizingInt(City::population)));
        IO.println(stateToCityPopulationSummary.get("NY"));

        cities = readCities("v2ch01/cities.txt");
        Map<String, String> stateToCityNames = cities.collect(groupingBy(City::state,
            reducing("", City::name, (s, t) -> s.length() == 0 ? t : s + ", " + t)));

        cities = readCities("v2ch01/cities.txt");
        stateToCityNames = cities
                .collect(groupingBy(City::state, mapping(City::name, joining(", "))));
        IO.println("stateToCityNames: " + stateToCityNames);

        cities = readCities("v2ch01/cities.txt");
        record Pair<S, T>(S first, T second) {
        }
        Pair<List<String>, Double> result = cities.filter(c -> c.state().equals("NV")).collect(
            teeing(mapping(City::name, toList()), averagingDouble(City::population),
                (names, avg) -> new Pair<>(names, avg)));
        IO.println(result);
    }

    Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Path.of(filename)).map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }
}
