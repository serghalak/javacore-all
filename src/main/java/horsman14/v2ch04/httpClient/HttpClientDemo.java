package horsman14.v2ch04.httpClient;

import module java.base;
import module java.net.http;
import java.net.http.HttpRequest.BodyPublisher;

/**
 * This program demonstrates the HTTP client
 */
class HttpClientDemo {
    void main(String[] args) throws Exception {
        System.setProperty("jdk.httpclient.HttpClient.log", "headers,requests,content");
        String propsFilename = args.length > 0 ? args[0] : "v2ch04/httpClient/json.properties";
        Path propsPath = Path.of(propsFilename);
        var props = new Properties();
        try (Reader in = Files.newBufferedReader(propsPath)) {
            props.load(in);
        }
        String urlString = "" + props.remove("url");
        String contentType = "" + props.remove("Content-Type");
        BodyPublisher publisher = null;
        if (contentType.equals("application/x-www-form-urlencoded"))
            publisher = MoreBodyPublishers.ofFormData(props);
        else if (contentType.equals("multipart/form-data")) {
            // Split each value along commas, replace strings starting with
            // file:// with Path objects
            var data = new HashMap<String, List<?>>();
            for (Map.Entry<Object, Object> entry : props.entrySet()) {
                data.put(entry.getKey().toString(),
                    Stream.of(entry.getValue().toString().split("\\s*,\\s*"))
                        .map(s -> s.startsWith("file://")
                                ? propsPath.getParent().getParent()
                                        .resolve(Path.of(s.substring(7)))
                                : s)
                        .toList());
            }
            String boundary = UUID.randomUUID().toString().replace("-", "");
            contentType += "; boundary=" + boundary;
            publisher = MoreBodyPublishers.ofMimeMultipartData(data, boundary);
        }
        else {
            contentType = "application/json";
            publisher = MoreBodyPublishers.ofSimpleJSON(props);
        }

        String result = doPost(urlString, contentType, publisher);
        System.out.println(result);
    }

    String doPost(String url, String contentType, BodyPublisher publisher)
            throws IOException, URISyntaxException, InterruptedException {
        try (HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS).build()) {

            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                    .header("Content-Type", contentType).POST(publisher).build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
    }
}
