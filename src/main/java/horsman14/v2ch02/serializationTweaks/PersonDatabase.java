package horsman14.v2ch02.serializationTweaks;

import module java.base;

public enum PersonDatabase {
    INSTANCE;

    private Map<Integer, Person> people = new ConcurrentHashMap<>();

    private PersonDatabase() {
        add(new Person(1, "Adam"));
        add(new Person(2, "Eve"));
    }

    public Person findById(int id) {
        return people.get(id);
    }

    public void add(Person p) {
        people.putIfAbsent(p.getId(), p);
    }
}
