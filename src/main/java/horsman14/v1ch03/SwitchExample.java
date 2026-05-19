package horsman14.v1ch03;

public class SwitchExample {

    void main() {
        int seasonCode = 1;
        String seasonName = switch (seasonCode) {
            case 0 -> "Spring";
            case 1 -> "Summer";
            case 2 -> "Fall";
            case 3 -> "Winter";
            default -> "???";
        };
        IO.println(seasonName);

        //String seasonName = "Summer";
        int numLetters = switch (seasonName) {
            case "Spring", "Summer", "Winter" -> 6;
            case "Fall" -> 4;
            default -> -1;
        };
        IO.println(numLetters);
    }
}
