package horsman14.v2ch03.stax;

import module java.base;
import module java.xml;

/**
 * This program demonstrates how to use a StAX parser. The program prints all hyperlinks of an
 * XHTML web page.
 */
class StAXDemo {
    void main(String[] args) throws Exception {
        String urlString;
        if (args.length == 0) {
            urlString = "https://www.horstmann.com/index.html";
            IO.println("Using " + urlString);
        }
        else
            urlString = args[0];
        InputStream in = new URI(urlString).toURL().openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(in);
        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("a")) {
                    String href = parser.getAttributeValue(null, "href");
                    if (href != null) IO.println(href);
                }
            }
        }
    }
}
