package horsman14.v2ch02.randomAccess;

import module java.base;

import horsman14.com.horstmann.corejava.*;

/*
 * This version uses a file channel.
 */
class RandomAccessDemo2 {
    final int NAME_SIZE = 40;
    final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;

    void main() throws Exception {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        Path path = Path.of("employee.dat");
        ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            // save all employee records to the file employee.dat
            for (Employee e : staff) {
                buffer.clear();
                writeData(buffer, e);
                buffer.flip();
                channel.write(buffer);
            }
        }

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            // compute the array size
            int n = (int) (Files.size(path)) / RECORD_SIZE;
            var newStaff = new Employee[n];

            // read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                channel.position(i * RECORD_SIZE);
                buffer.clear();
                channel.read(buffer);
                buffer.flip();
                newStaff[i] = readData(buffer);
            }

            // print the newly read employee records
            for (Employee e : newStaff)
                IO.println(e);
        }
    }

    /**
     * Writes employee data to a byte buffer
     * 
     * @param out the buffer
     * @param e the employee
     */
    void writeData(ByteBuffer out, Employee e) throws IOException {
        String name = e.getName();
        int length = Math.min(name.length(), NAME_SIZE - 1);
        // for (int i = 0; i < length; i++) out.putChar(name.charAt(i));
        out.asCharBuffer().put(name.substring(0, length)).put('\0');
        out.position(2 * NAME_SIZE);
        out.putDouble(e.getSalary());
        LocalDate hireDay = e.getHireDay();
        out.putInt(hireDay.getYear());
        out.putInt(hireDay.getMonthValue());
        out.putInt(hireDay.getDayOfMonth());
    }

    /**
     * Reads employee data from a byte buffer
     * 
     * @param in the buffer
     * @return the employee
     */
    Employee readData(ByteBuffer in) throws IOException {
        var name = new StringBuilder();
        boolean done = false;
        while (!done) {
            char ch = in.getChar();
            if (ch == '\0')
                done = true;
            else
                name.append(ch);
        }
        in.position(2 * NAME_SIZE);
        double salary = in.getDouble();
        int y = in.getInt();
        int m = in.getInt();
        int d = in.getInt();
        return new Employee(name.toString(), salary, y, m, d);
    }
}
