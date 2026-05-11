package horsman14.v1ch10;

import module java.base;

/**
 * This program demonstrates running a process and reading its output, and listing all Java
 * processes
 */
class ProcessDemo {
    void main() throws Exception {
        Process p = new ProcessBuilder("/bin/ls", "-l")
                .directory(Path.of("/tmp").toFile())
                .start();
        try (var in = new Scanner(p.getInputStream())) {
            while (in.hasNextLine())
                IO.println(in.nextLine());
        }
        IO.println("pid: " + p.toHandle().pid());
        int result = p.waitFor();
        IO.println("Exit value: " + result);

        ProcessHandle.allProcesses()
                .map(ProcessHandle::info)
                .filter(info -> info.command().filter(s -> s.contains("java")).isPresent())
                .forEach(info -> info.commandLine()
                        .ifPresent(IO::println));
    }
}
