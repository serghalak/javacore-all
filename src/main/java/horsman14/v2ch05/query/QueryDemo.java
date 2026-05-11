package horsman14.v2ch05.query;

import module java.base;
import module java.sql;
import horsman14.v2ch05.exec.ExecSQL;

/**
 * This program demonstrates several complex database queries.
 */
class QueryDemo {
    String allQuery = "SELECT Books.Price, Books.Title FROM Books";

    String authorPublisherQuery = """
SELECT Books.Price, Books.Title
FROM Books, BooksAuthors, Authors, Publishers
WHERE Authors.Author_Id = BooksAuthors.Author_Id
    AND BooksAuthors.ISBN = Books.ISBN
    AND Books.Publisher_Id = Publishers.Publisher_Id
    AND Authors.Name = ?
    AND Publishers.Name = ?
""";

    String authorQuery = """
SELECT Books.Price, Books.Title FROM Books, BooksAuthors, Authors
WHERE Authors.Author_Id = BooksAuthors.Author_Id"
    AND BooksAuthors.ISBN = Books.ISBN"
    AND Authors.Name = ?
""";

    String publisherQuery = """
SELECT Books.Price, Books.Title FROM Books, Publishers
WHERE Books.Publisher_Id = Publishers.Publisher_Id
    AND Publishers.Name = ?
""";

    String priceUpdate = """
UPDATE Books SET Price = Price + ? "
WHERE Books.Publisher_Id =
    (SELECT Publisher_Id FROM Publishers WHERE Name = ?)
""";

    ArrayList<String> authors = new ArrayList<>();
    ArrayList<String> publishers = new ArrayList<>();

    void main() throws Exception {
        try (Connection conn = ExecSQL.getConnection()) {
            authors.add("Any");
            publishers.add("Any");
            try (Statement stat = conn.createStatement()) {
                // Fill the authors array list
                String query = "SELECT Name FROM Authors";
                try (ResultSet rs = stat.executeQuery(query)) {
                    while (rs.next())
                        authors.add(rs.getString(1));
                }

                // Fill the publishers array list
                query = "SELECT Name FROM Publishers";
                try (ResultSet rs = stat.executeQuery(query)) {
                    while (rs.next())
                        publishers.add(rs.getString(1));
                }
            }
            boolean done = false;
            while (!done) {
                String input = IO.readln("Q)uery C)hange prices E)xit: ").toUpperCase();
                if (input.equals("Q"))
                    executeQuery(conn);
                else if (input.equals("C"))
                    changePrices(conn);
                else
                    done = true;
            }
        }
        catch (SQLException e) {
            for (Throwable t : e)
                IO.println(t.getMessage());
        }
    }

    /**
     * Executes the selected query.
     * @param conn the database connection
     */
    void executeQuery(Connection conn) throws SQLException {
        String author = select("Authors:", authors);
        String publisher = select("Publishers:", publishers);
        PreparedStatement stat;
        if (!author.equals("Any") && !publisher.equals("Any")) {
            stat = conn.prepareStatement(authorPublisherQuery);
            stat.setString(1, author);
            stat.setString(2, publisher);
        }
        else if (!author.equals("Any") && publisher.equals("Any")) {
            stat = conn.prepareStatement(authorQuery);
            stat.setString(1, author);
        }
        else if (author.equals("Any") && !publisher.equals("Any")) {
            stat = conn.prepareStatement(publisherQuery);
            stat.setString(1, publisher);
        }
        else
            stat = conn.prepareStatement(allQuery);

        try (ResultSet rs = stat.executeQuery()) {
            while (rs.next())
                IO.println(rs.getString(1) + ", " + rs.getString(2));
        }
    }

    /**
     * Executes an update statement to change prices.
     * @param conn the database connection
     */
    void changePrices(Connection conn) throws SQLException {
        String publisher = select("Publishers:", publishers.subList(1, publishers.size()));
        double priceChange = Double.parseDouble(IO.readln("Change prices by: "));
        PreparedStatement stat = conn.prepareStatement(priceUpdate);
        stat.setDouble(1, priceChange);
        stat.setString(2, publisher);
        int r = stat.executeUpdate();
        IO.println(r + " records updated.");
    }

    /**
     * Asks the user to select a string.
     * @param prompt the prompt to display
     * @param options the options from which the user can choose
     * @return the option that the user chose
     */
    String select(String prompt, List<String> options) {
        while (true) {
            IO.println(prompt);
            for (int i = 0; i < options.size(); i++)
                IO.println("%2d) %s".formatted(i + 1, options.get(i)));
            int sel = Integer.parseInt(IO.readln());
            if (sel > 0 && sel <= options.size()) return options.get(sel - 1);
        }
    }
}
