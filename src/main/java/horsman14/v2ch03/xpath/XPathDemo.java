package horsman14.v2ch03.xpath;

import module java.base;
import module java.xml;

/**
 * This program evaluates XPath expressions.
 */
class XPathDemo {
    void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Avoid a delay in parsing an XHTML file--see the first note in Section 3.4.1
        builder.setEntityResolver(CatalogManager.catalogResolver(CatalogFeatures.defaults(),
                Path.of("v2ch03", "xpath", "catalog.xml").toAbsolutePath().toUri()));

        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath path = xpfactory.newXPath();

        String urlString;
        if (args.length == 0) {
            urlString = "https://www.horstmann.com/index.html";
            IO.println("Using " + urlString);
        }
        else
            urlString = args[0];
        Document doc = builder.parse(urlString);
        boolean done = false;
        while (!done) {
            IO.print("XPath expression (empty line to exit): ");
            String expression = IO.readln();
            if (expression.strip().isEmpty()) {
                done = true;
            }
            else {
                try {
                    XPathEvaluationResult<?> result = path.evaluateExpression(expression, doc);
                    if (result.type() == XPathEvaluationResult.XPathResultType.NODESET) {
                        for (Node n : (XPathNodes) result.value())
                            IO.println(description(n));
                    }
                    else if (result.type() == XPathEvaluationResult.XPathResultType.NODE)
                        IO.println((Node) result.value());
                    else
                        IO.println(result.value());
                }
                catch (XPathExpressionException e) {
                    IO.println(e.getMessage());
                }
            }
        }
    }

    String description(Node n) {
        return switch (n) {
            case Element _ -> "Element " + n.getNodeName();
            case Attr _ -> "Attribute " + n;
            default -> n.toString();
        };
    }
}
