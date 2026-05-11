package horsman14.v2ch04.httpServer;

import module java.base;
import module jdk.httpserver;

/**
 * This program implements an HTTP server that listens to port 8189 or a port given as
 * argument, and echoes back all client input.
 */
class EchoServer {
    void main(String[] args) throws Exception {
        int port = args.length >= 1 ? Integer.parseInt(args[0]) : 8189;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.createContext("/", HttpHandlers.of(301,
                Headers.of("Location", "https://horstmann.com/corejava"), ""));
        server.createContext("/echo", (HttpExchange exchange) -> {
            var headers = new StringBuilder();
            exchange.getRequestHeaders().forEach(
                    (k, vs) -> vs.forEach(v -> headers.append("%s: %s\n".formatted(k, v))));

            String requestBody = new String(exchange.getRequestBody().readAllBytes());
            String response = "%s %s\n%s\n%s\n".formatted(exchange.getRequestMethod(),
                    exchange.getRequestURI(), headers, requestBody);
            byte[] responseBytes = response.getBytes();

            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseBytes);
            responseBody.close();
        });
        server.start();
    }
}
