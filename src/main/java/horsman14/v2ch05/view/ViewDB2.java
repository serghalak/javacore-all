package horsman14.v2ch05.view;

import module java.base;
import module java.sql;
import module java.sql.rowset;
import horsman14.v2ch05.exec.ExecSQL;
import horsman14.v2ch07.util.Choices;

/**
 * This program uses metadata to display arbitrary tables in a database.
 */
class ViewDB2 {
    CachedRowSet crs;

    record Column(String label, int width, String columnClass) {
    }

    List<Column> columns = new ArrayList<>();

    void main() {
        try (Connection conn = ExecSQL.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            var tableNames = new ArrayList<String>();

            try (ResultSet mrs = meta.getTables(null, null, null, new String[]{ "TABLE" })) {
                while (mrs.next())
                    tableNames.add(mrs.getString(3));
            }
            IO.println("Choose a table");
            String selected = Choices.choose(tableNames);
            showTable(selected, conn);
            executeCommands();
            crs.acceptChanges(conn);
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prepares the columns list for showing a new table, and shows the first row.
     * @param tableName the name of the table to display
     * @param conn the database connection
     */
    void showTable(String tableName, Connection conn) throws SQLException {
        try (Statement stat = conn.createStatement();
             ResultSet result = stat.executeQuery("SELECT * FROM " + tableName)) {
            // copy into cached row set
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.setTableName(tableName);
            crs.populate(result);

            ResultSetMetaData rsmd = result.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columns.add(new Column(rsmd.getColumnLabel(i), rsmd.getColumnDisplaySize(i),
                        rsmd.getColumnClassName(i)));
            }

            showNextRow();
        }
    }

    void executeCommands() throws SQLException {
        while (true) {
            String selection = Choices.choose("Next", "Previous", "Edit", "Delete", "Quit");
            switch (selection) {
                case "Next" -> showNextRow();
                case "Previous" -> showPreviousRow();
                case "Edit" -> editRow();
                case "Delete" -> deleteRow();
                default -> {
                    return;
                }
            }
        }
    }

    /**
     * Shows a database row by populating all text fields with the column values.
     */
    void showRow(ResultSet rs) throws SQLException {
        for (int i = 1; i <= columns.size(); i++) {
            String format = "%%s [%%%ds]".formatted(columns.get(i - 1).width());
            IO.println(format.formatted(columns.get(i - 1).label(),
                    rs == null ? "" : rs.getString(i)));
        }
    }

    /**
     * Moves to the previous table row.
     */
    void showPreviousRow() throws SQLException {
        if (crs == null || crs.isFirst()) return;
        crs.previous();
        showRow(crs);
    }

    /**
     * Moves to the next table row.
     */
    void showNextRow() throws SQLException {
        if (crs == null || crs.isLast()) return;
        crs.next();
        showRow(crs);
    }

    /**
     * Deletes current table row.
     */
    void deleteRow() throws SQLException {
        if (crs == null) return;
        crs.deleteRow();
        if (crs.isAfterLast()) {
            if (!crs.last()) crs = null;
        }
        showRow(crs);
    }

    /**
     * Updates changed data into the current row of the row set.
     */
    void editRow() throws SQLException {
        List<String> editableColumns = columns.stream()
                .filter(c -> c.columnClass.equals("java.lang.String")).map(Column::label)
                .toList();
        IO.println("Choose column");
        String label = Choices.choose(editableColumns);
        String newValue = IO.readln("New value: ");
        crs.updateString(label, newValue);
        crs.updateRow();
    }
}
