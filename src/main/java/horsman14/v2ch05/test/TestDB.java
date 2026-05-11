package horsman14.v2ch05.test;

import module java.base;
import module java.sql;
import horsman14.v2ch05.exec.ExecSQL;

/**
 * This program tests that the database and the JDBC driver are correctly configured.
 */
class TestDB {
    void main() throws Exception {
        try {
            runTest();
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    /**
     * Runs a test by creating a table, adding a value, showing the table contents, and
     * removing the table.
     */
    void runTest() throws SQLException, IOException {
        try (Connection conn = ExecSQL.getConnection()) {
            Statement stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE Greetings (Message VARCHAR(20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next()) System.out.println(result.getString(1));
            }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }
}
