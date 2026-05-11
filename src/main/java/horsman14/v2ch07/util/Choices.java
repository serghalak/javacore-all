package horsman14.v2ch07.util;

import module java.base;

public class Choices {
    /**
     * Choose from an array of choices.
     * @param choices the objects to choose from
     * @param formatter a function that produces a description from a choice
     * @return the chosen object
     */
    public static <T> T choose(T[] choices, Function<T, String> formatter) {
        for (int i = 0; i < choices.length; i++) {
            IO.println("%2d: %s".formatted(i + 1, formatter.apply(choices[i])));
        }
        while (true) {
            try {
                int choice = Integer.parseInt(IO.readln("Your choice: "));
                if (0 < choice && choice <= choices.length) { return choices[choice - 1]; }
            }
            catch (NumberFormatException _) {
                // Try again
            }
        }
    }

    /**
     * Chooses among static fields of a class.
     * @param cl a class
     * @param labels an array of strings describing static field names of cl that have type T
     * @return the chosen value
     */
    public static <T> T choose(Class<?> cl, String... labels) {
        String label = choose(labels, Function.identity());
        String name = label.toUpperCase().replace(' ', '_');
        try {
            java.lang.reflect.Field f = cl.getField(name);
            @SuppressWarnings("unchecked")
            T value = (T) f.get(cl);
            return value;
        }
        catch (Exception e) {
            System.err.println("Unmatched label " + label);
            return null;
        }
    }

    /**
     * Choose from a map of choices to descriptions.
     * @param choices a map from choices to description strings
     * @return the chosen object
     */
    public static <T> T choose(Map<T, String> choices) {
        Object[] objects = choices.keySet().toArray();
        @SuppressWarnings("unchecked")
        T choice = (T) choose(objects, o -> choices.get((T) o));
        return choice;
    }

    /**
     * Choose from strings.
     * @param choices the string choices
     * @return the chosen string
     */
    public static String choose(String... choices) {
        return choose(choices, Function.identity());
    }

    /**
     * Choose from a list of choices.
     * @param choices the strings to choose from
     * @return the chosen string
     */
    public static String choose(List<String> choices) {
        return choose(choices.toArray(String[]::new));
    }
}
