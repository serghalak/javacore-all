package horsman14.v2ch02.regex;

import module java.base;

/**
 * This program displays all hyperlinks in a web page by matching a regular expression that
 * describes the <a href=...> HTML tag.
 */
class HrefMatch {
    void main(String[] args) throws Exception {
        String urlString = args.length > 0 ? args[0] : "https://openjdk.org/";

        // read contents of URL
        InputStream in = URI.create(urlString).toURL().openStream();
        var input = new String(in.readAllBytes());

        // search for all occurrences of pattern
        String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        pattern.matcher(input).results().map(MatchResult::group).forEach(IO::println);
    }
}
