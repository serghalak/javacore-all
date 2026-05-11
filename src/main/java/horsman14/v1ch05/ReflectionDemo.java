package horsman14.v1ch05;

import module java.base;

/**
 * This program uses reflection to print all features of a class.
 */
class ReflectionDemo {
    void main(String[] args) throws Exception {
        // read class name from command line args or user input
        String name = args.length > 0 ? args[0] :
            IO.readln("Enter class name (e.g. java.util.Date): ");

        // print class modifiers, name, and superclass name (if != Object)
        Class cl = Class.forName(name);
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) IO.print(modifiers + " ");
        if (cl.isSealed()) IO.print("sealed ");
        if (cl.isEnum())
            IO.print("enum " + name);
        else if (cl.isRecord())
            IO.print("record " + name);
        else if (cl.isInterface())
            IO.print("interface " + name);
        else
            IO.print("class " + name);
        Class supercl = cl.getSuperclass();
        if (supercl != null && supercl != Object.class)
            IO.print(" extends " + supercl.getName());

        printInterfaces(cl);
        printPermittedSubclasses(cl);

        IO.print(" {\n");
        printConstructors(cl);
        IO.println();
        printMethods(cl);
        IO.println();
        printFields(cl);
        IO.println("}");
    }

    /**
     * Prints all constructors of a class
     * @param cl a class
     */
    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            IO.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) IO.print(modifiers + " ");
            IO.print(name + "(");

            // print parameter types
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) IO.print(", ");
                IO.print(paramTypes[j].getName());
            }
            IO.println(");");
        }
    }

    /**
     * Prints all methods of a class
     * @param cl a class
     */
    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();

            IO.print("    ");
            // print modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) IO.print(modifiers + " ");
            IO.print(retType.getName() + " " + name + "(");

            // print parameter types
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) IO.print(", ");
                IO.print(paramTypes[j].getName());
            }
            IO.println(");");
        }
    }

    /**
     * Prints all fields of a class
     * @param cl a class
     */
    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            IO.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) IO.print(modifiers + " ");
            IO.println(type.getName() + " " + name + ";");
        }
    }

    /**
     * Prints all permitted subtypes of a sealed class
     * @param cl a class
     */
    public static void printPermittedSubclasses(Class cl) {
        if (cl.isSealed()) {
            Class<?>[] permittedSubclasses = cl.getPermittedSubclasses();
            for (int i = 0; i < permittedSubclasses.length; i++) {
                if (i == 0)
                    IO.print(" permits ");
                else
                    IO.print(", ");
                IO.print(permittedSubclasses[i].getName());
            }
        }
    }

    /**
     * Prints all directly implemented interfaces of a class
     * @param cl a class
     */
    public static void printInterfaces(Class cl) {
        Class<?>[] interfaces = cl.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (i == 0)
                IO.print(cl.isInterface() ? " extends " : " implements ");
            else
                IO.print(", ");
            IO.print(interfaces[i].getName());
        }
    }
}
