package horsman14.v2ch03.sax;

import module java.base;
import module java.xml;
import org.xml.sax.Attributes;

/**
 * This program demonstrates how to use a SAX parser. The program prints all hyperlinks of an
 * XHTML web page.
 */
class SAXDemo {
    void main(String[] args) throws Exception {
        String url;
        if (args.length == 0) {
            url = "https://horstmann.com/corejava/index.html";
            IO.println("Using " + url);
        }
        else
            url = args[0];

        var handler = new DefaultHandler() {
            public void startElement(String namespaceURI, String lname, String qname,
                    Attributes attrs) {
                if (lname.equals("a") && attrs != null) {
                    String href = attrs.getValue("href");
                    if (href != null) IO.println(href);
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
                false);
        SAXParser saxParser = factory.newSAXParser();
        InputStream in = new URI(url).toURL().openStream();
        saxParser.parse(in, handler);
    }
}
