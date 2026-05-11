package horsman14.v1ch05;

/**
 * This program demonstrates enumerated types.
 */
class EnumDemo {
    void main() {
        String input = IO
                .readln("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE): ")
                .toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        IO.println("size=" + size);
        IO.println("abbreviation=" + size.getAbbreviation());
        if (size == Size.EXTRA_LARGE)
            IO.println("Good job--you paid attention to the _.");
    }
}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    private String abbreviation;
}