package horsman14.v2ch02.regex;

/**
 * This program tests regular expression matching. Enter a pattern and strings to match, 
 * or hit Enter to exit. If the pattern contains groups, the group boundaries are displayed 
 * in the match.
 */

import module java.base;

class RegexDemo {
    void main() throws Exception {
        String patternString = IO.readln("Enter pattern: ");

        Pattern pattern = Pattern.compile(patternString);

        while (true) {
            String input = IO.readln("Enter string to match: ");
            if (input == null || input.equals("")) return;
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                IO.println("Match");
                int g = matcher.groupCount();
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        // Print any empty groups
                        for (int j = 1; j <= g; j++)
                            if (i == matcher.start(j) && i == matcher.end(j)) IO.print("()");
                        // Print ( for non-empty groups starting here
                        for (int j = 1; j <= g; j++)
                            if (i == matcher.start(j) && i != matcher.end(j)) IO.print("(");
                        IO.print(input.charAt(i));
                        // Print ) for non-empty groups ending here
                        for (int j = 1; j <= g; j++)
                            if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j))
                                IO.print(")");
                    }
                    IO.println();
                }
            }
            else
                IO.println("No match");
        }
    }
}
