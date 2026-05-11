package horsman14.v1ch10;

import module java.base;
import module java.desktop;

import java.util.List;

class CompletableFutureDemo {
    Pattern IMG_PATTERN = Pattern.compile(
            "[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]");
    ExecutorService executor = Executors.newCachedThreadPool();
    URI uriToProcess = URI.create("http://horstmann.com/index.html");

    void main() throws Exception {
        CompletableFuture.completedFuture(uriToProcess)
                .thenComposeAsync(this::readPage, executor)
                .thenApply(this::getImageLinks)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);

        // or use the HTTP client:
        /*
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(uriToProcess).GET().build();
        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(this::getImageLinks)
            .thenCompose(this::getImages)
            .thenAccept(this::saveImages);
        */
    }

    CompletableFuture<String> readPage(URI uri) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var contents = new String(uri.toURL().openStream().readAllBytes());
                IO.println("Read page from " + uri);
                return contents;
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    List<URI> getImageLinks(String webpage) // not blocking
    {
        var result = new ArrayList<URI>();
        Matcher matcher = IMG_PATTERN.matcher(webpage);
        while (matcher.find()) {
            URI uri = URI.create(uriToProcess + "/" + matcher.group(1));
            result.add(uri);
        }
        IO.println("Found links: " + result);
        return result;
    }

    CompletableFuture<List<BufferedImage>> getImages(List<URI> uris) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var result = new ArrayList<BufferedImage>();
                for (URI uri : uris) {
                    result.add(ImageIO.read(uri.toURL()));
                    IO.println("Loaded " + uri);
                }
                return result;
            }
            catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }, executor);
    }

    void saveImages(List<BufferedImage> images) {
        IO.println("Saving " + images.size() + " images");
        try {
            for (int i = 0; i < images.size(); i++) {
                String filename = "/tmp/image" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        executor.shutdown();
    }
}
