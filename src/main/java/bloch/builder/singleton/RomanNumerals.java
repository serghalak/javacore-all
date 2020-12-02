package bloch.builder.singleton;

import java.util.regex.Pattern;

public class RomanNumerals {

    //явно компілюємо вираз,
    // який вимає багато ресурсу,
    // а потім тільки використ.
    //Цей ресур створиться тільки раз
    private static final Pattern ROMAN=
            Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeral(String s){
        return ROMAN.matcher(s).matches();
    }
}
