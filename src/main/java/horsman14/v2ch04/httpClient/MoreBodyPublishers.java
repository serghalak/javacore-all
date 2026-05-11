package horsman14.v2ch04.httpClient;

import module java.base;
import java.net.http.HttpRequest.*;

public class MoreBodyPublishers {
    public static BodyPublisher ofFormData(Map<Object, Object> data) {
        boolean first = true;
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first)
                first = false;
            else
                builder.append("&");
            builder.append(
                    URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(
                    URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return BodyPublishers.ofString(builder.toString());
    }

    public static BodyPublisher ofMimeMultipartData(Map<String, List<?>> data, String boundary)
            throws IOException {
        var bps = new ArrayList<BodyPublisher>();
        var header = new StringBuilder();
        for (Map.Entry<String, List<?>> entry : data.entrySet()) {
            for (Object value : entry.getValue()) {
                header.append("--%s\r\nContent-Disposition: form-data; name=%s"
                        .formatted(boundary, entry.getKey()));

                if (value instanceof Path path) {
                    header.append("; filename=\"%s\"\r\n".formatted(path.getFileName()));
                    String mimeType = Files.probeContentType(path);
                    if (mimeType != null)
                        header.append("Content-Type: %s\r\n".formatted(mimeType));
                    header.append("\r\n");
                    bps.add(BodyPublishers.ofString(header.toString()));
                    bps.add(BodyPublishers.ofFile(path));
                }
                else {
                    header.append("\r\n\r\n%s\r\n".formatted(value));
                    header.append("\r\n");
                    bps.add(BodyPublishers.ofString(header.toString()));
                }
                header = new StringBuilder("\r\n");
            }
        }
        bps.add(BodyPublishers.ofString("\r\n--%s--\r\n".formatted(boundary)));
        return BodyPublishers.concat(bps.toArray(BodyPublisher[]::new));
    }

    public static BodyPublisher ofSimpleJSON(Map<Object, Object> data) {
        var builder = new StringBuilder();
        builder.append("{");
        var first = true;
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (first)
                first = false;
            else
                builder.append(",");
            builder.append(jsonEscape(entry.getKey().toString())).append(": ")
                    .append(jsonEscape(entry.getValue().toString()));
        }
        builder.append("}");
        return BodyPublishers.ofString(builder.toString());
    }

    private static Map<Character, String> replacements = Map.of('\b', "\\b", '\f', "\\f", '\n',
            "\\n", '\r', "\\r", '\t', "\\t", '"', "\\\"", '\\', "\\\\");

    private static StringBuilder jsonEscape(String str) {
        var result = new StringBuilder("\"");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String replacement = replacements.get(ch);
            if (replacement == null)
                result.append(ch);
            else
                result.append(replacement);
        }
        result.append("\"");
        return result;
    }
}
