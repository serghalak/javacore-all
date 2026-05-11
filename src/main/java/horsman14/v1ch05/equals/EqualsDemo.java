package horsman14.v1ch05.equals;

/**
 * This program demonstrates the equals method.
 */
class EqualsDemo {
    void main() {
        var alice1 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var alice2 = alice1;
        var alice3 = new Employee("Alice Adams", 75000, 1987, 12, 15);
        var bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);

        IO.println("alice1 == alice2: " + (alice1 == alice2));

        IO.println("alice1 == alice3: " + (alice1 == alice3));

        IO.println("alice1.equals(alice3): " + alice1.equals(alice3));

        IO.println("alice1.equals(bob): " + alice1.equals(bob));

        IO.println("bob.toString(): " + bob);

        var carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        var boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);
        IO.println("boss.toString(): " + boss);
        IO.println("carl.equals(boss): " + carl.equals(boss));
        IO.println("alice1.hashCode(): " + alice1.hashCode());
        IO.println("alice3.hashCode(): " + alice3.hashCode());
        IO.println("bob.hashCode(): " + bob.hashCode());
        IO.println("carl.hashCode(): " + carl.hashCode());
    }
}
