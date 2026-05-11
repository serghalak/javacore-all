package horsman14.v2ch01.collecting;

import module java.base;

record Person(int id, String name) {}

class CollectingIntoMaps {
    void main() throws Exception {
        Map<Integer, String> idToName = people()
                .collect(Collectors.toMap(Person::id, Person::name));
        IO.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people()
                .collect(Collectors.toMap(Person::id, Function.identity()));
        IO.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(
                Collectors.toMap(Person::id, Function.identity(), (_, _) -> {
                    throw new IllegalStateException();
                }, TreeMap::new));
        IO.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Map<String, String> languageNames = Locale.availableLocales()
                .collect(Collectors.toMap(Locale::getDisplayLanguage,
                        l -> l.getDisplayLanguage(l),
                        (existingValue, _) -> existingValue));
        IO.println("languageNames: " + languageNames);

        Map<String, Set<String>> countryLanguageSets = Locale.availableLocales()
                .collect(Collectors.toMap(Locale::getDisplayCountry,
                        l -> Set.of(l.getDisplayLanguage()), (a, b) -> { // union of a and b
                            Set<String> union = new HashSet<>(a);
                            union.addAll(b);
                            return union;
                        }));
        IO.println("countryLanguageSets: " + countryLanguageSets);
    }
    
    static Stream<Person> people() {
        return Stream.of(new Person(1001, "Peter"), new Person(1002, "Paul"),
                new Person(1003, "Mary"));
    }    
}
