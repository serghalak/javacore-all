package horsman14.v2ch08.script;

import module java.base;
import module java.scripting;

/**
 * This program demonstrates the Java scripting engine.
 */
class ScriptDemo {
    void main(String[] args) throws Exception {
        var manager = new ScriptEngineManager();
        String language;
        if (args.length == 0) {
            IO.println("Available factories: ");
            for (ScriptEngineFactory factory : manager.getEngineFactories())
                IO.println(factory.getEngineName());
            return;
        }
        else
            language = args[0];

        ScriptEngine engine = manager.getEngineByName(language);
        if (engine == null) {
            System.err.println("No engine for " + language);
            System.exit(1);
        }

        String sheetName = args[1];
        boolean verbatim = false;
        var verbatimCode = new StringBuilder();
        for (String line : Files.readAllLines(Path.of(sheetName))) {
            if (line.equals("```")) {
                verbatim = !verbatim;
                if (!verbatim) {
                    engine.eval(verbatimCode.toString());
                    verbatimCode.delete(0, verbatimCode.length());
                }
            }
            else if (verbatim)
                verbatimCode.append(line).append("\n");
            else {
                String[] fragments = line.split("`");
                for (int i = 0; i < fragments.length; i++) {
                    IO.print(i % 2 == 0 ? fragments[i] : engine.eval(fragments[i]));
                }
                IO.println();
            }
        }
    }
}
