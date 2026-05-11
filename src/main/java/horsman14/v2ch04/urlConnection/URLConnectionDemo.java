package horsman14.v2ch04.urlConnection;

import module java.base;

/**
 * This program connects to an URL and displays the response header data and the first 10 lines
 * of the requested data.
 * 
 * Supply the URL and an optional username and password (for HTTP basic authentication) on the
 * command line.
 */
class URLConnectionDemo {
    void main(String[] args) throws Exception {
        String urlName = args.length > 0 ? args[0] : "http://horstmann.com";

        var url = new URI(urlName).toURL();
        URLConnection connection = url.openConnection();

        // set username, password if specified on command line

        if (args.length > 2) {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            Base64.Encoder encoder = Base64.getEncoder();
            String encoding = encoder.encodeToString(input.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + encoding);
        }

        connection.connect();

        // print header fields

        Map<String, List<String>> headers = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue())
                IO.println(key + ": " + value);
        }

        // print convenience functions

        IO.println("----------");
        IO.println("getContentType: " + connection.getContentType());
        IO.println("getContentLength: " + connection.getContentLength());
        IO.println("getContentEncoding: " + connection.getContentEncoding());
        IO.println("getDate: " + connection.getDate());
        IO.println("getExpiration: " + connection.getExpiration());
        IO.println("getLastModifed: " + connection.getLastModified());
        IO.println("----------");

        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";
        try (var in = new Scanner(connection.getInputStream(), encoding)) {
            // print first ten lines of contents

            for (int n = 1; in.hasNextLine() && n <= 10; n++)
                IO.println(in.nextLine());
            if (in.hasNextLine()) IO.println(". . .");
        }
    }
}
