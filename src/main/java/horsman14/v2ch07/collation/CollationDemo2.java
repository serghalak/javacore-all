package horsman14.v2ch07.collation;

import module java.base;
import horsman14.v2ch07.util.Choices;

/**
 * This program demonstrates collating strings under various locales.
 */
class CollationDemo2 {
   void main() {
      var locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
      Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
      Locale locale = Choices.choose(locales, Locale::getDisplayName);

      Collator collator = Collator.getInstance(locale);
      int strength = Choices.choose(Collator.class, 
         "Primary", "Secondary", "Tertiary", "Identical");
      int decomposition = Choices.choose(Collator.class, 
         "Canonical Decomposition", "Full Decomposition", "No Decomposition");

      List<String> strings = new ArrayList<>();
      strings.add("America");
      strings.add("able");
      strings.add("Zulu");
      strings.add("zebra");
      strings.add("\u00C5ngstr\u00F6m");
      strings.add("A\u030angstro\u0308m");
      strings.add("Angstrom");
      strings.add("Able");
      strings.add("office");
      strings.add("o\uFB03ce");
      strings.add("Java\u2122");
      strings.add("JavaTM");

      collator.setStrength(strength);
      collator.setDecomposition(decomposition);

      strings.sort(collator);

      for (int i = 0; i < strings.size(); i++) {
         String s = strings.get(i);
         if (i > 0 && collator.compare(s, strings.get(i - 1)) == 0)
            System.out.print("= ");
         System.out.println(s);
      }
   }
}
