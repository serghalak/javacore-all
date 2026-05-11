package horsman14.v2ch07.retire2;

import module java.base;

import horsman14.v2ch07.util.Choices;

/**
 * This program shows a retirement calculator. The prompts are displayed in English, German, or
 * Chinese.
 */
class Retire2 {
    void main() throws Exception {
        // Get locale
        ResourceBundle bundle = ResourceBundle.getBundle("v2ch07.retire.RetireStrings",
                Locale.getDefault());
        IO.println(bundle.getString("language"));
        Locale[] locales = { Locale.US, Locale.CHINA, Locale.GERMANY };
        Locale currentLocale = Choices.choose(locales, Locale::getDisplayName);
        bundle = ResourceBundle.getBundle("v2ch07.retire.RetireStrings", currentLocale);
        NumberFormat integerFormat = NumberFormat.getIntegerInstance(currentLocale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(currentLocale);
        
        // Prompt for parameters
        double savings = numberFormat.parse(
                IO.readln(bundle.getString("savings") + ": ")).doubleValue();
        double contributions = numberFormat.parse(
                IO.readln(bundle.getString("contrib") + ": ")).doubleValue();
        double income = numberFormat.parse(
                IO.readln(bundle.getString("income") + ": ")).doubleValue();
        int currentAge = integerFormat.parse(
                IO.readln(bundle.getString("currentAge") + ": ")).intValue();
        int retireAge = integerFormat.parse(
                IO.readln(bundle.getString("retireAge") + ": ")).intValue();
        int deathAge = integerFormat.parse(
                IO.readln(bundle.getString("deathAge") + ": ")).intValue();
        double inflationPercent = numberFormat.parse(
                IO.readln(bundle.getString("inflationPercent") + ": ")).doubleValue();
        double investPercent = numberFormat.parse(
                IO.readln(bundle.getString("investPercent") + ": ")).doubleValue();

        // Show values
        var retireMsg = new MessageFormat("");
        retireMsg.setLocale(currentLocale);
        retireMsg.applyPattern(bundle.getString("retire"));

        double balance = savings;
        for (int year = currentAge; year <= deathAge; year++) {
            Object[] msgArgs = { year, balance };
            IO.println(retireMsg.format(msgArgs));
            if (year < retireAge)
                balance += contributions;
            else
                balance -= income;
            balance = balance * (1 + (investPercent - inflationPercent));
        }
    }
}
