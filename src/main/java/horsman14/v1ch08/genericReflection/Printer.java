package horsman14.v1ch08.genericReflection;

import module java.base;

public class Printer {
    public static void printClass(Class<?> cl) {
        IO.print(cl);
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            IO.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        IO.println();
    }

    public static void printMethod(Method m) {
        String name = m.getName();
        IO.print(Modifier.toString(m.getModifiers()));
        IO.print(" ");
        printTypes(m.getTypeParameters(), "<", ", ", "> ", true);

        printType(m.getGenericReturnType(), false);
        IO.print(" ");
        IO.print(name);
        IO.print("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        IO.println(")");
    }

    public static void printTypes(Type[] types, String prefix, String separator, String suffix,
            boolean isDefinition) {
        if (prefix.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class }))
            return;
        if (types.length > 0) IO.print(prefix);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) IO.print(separator);
            printType(types[i], isDefinition);
        }
        if (types.length > 0) IO.print(suffix);
    }

    public static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class<?> t) {
            IO.print(t.getName());
        }
        else if (type instanceof TypeVariable<?> t) {
            IO.print(t.getName());
            if (isDefinition) printTypes(t.getBounds(), " extends ", " & ", "", false);
        }
        else if (type instanceof WildcardType t) {
            IO.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false);
        }
        else if (type instanceof ParameterizedType t) {
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                IO.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ", ", ">", false);
        }
        else if (type instanceof GenericArrayType t) {
            IO.print("");
            printType(t.getGenericComponentType(), isDefinition);
            IO.print("[]");
        }
    }
}
