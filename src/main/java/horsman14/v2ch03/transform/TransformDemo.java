package horsman14.v2ch03.transform;

import module java.base;
import module java.xml;

import org.xml.sax.ContentHandler;
import horsman14.v2ch07.util.Choices;

/**
 * This program demonstrates XSL transformations. It applies a transformation to a set of
 * employee records. The records are stored in the file employee.dat and turned into XML
 * format.
 */
class TransformDemo {
    void main(String[] args) throws Exception {
        Path path;
        String choice = Choices.choose("HTML", "Properties").toLowerCase();
        if (choice.equals("html"))
            path = Path.of("v2ch03", "transform", "makehtml.xsl");
        else
            path = Path.of("v2ch03", "transform", "makeprop.xsl");
        try (InputStream styleIn = Files.newInputStream(path)) {
            var styleSource = new StreamSource(styleIn);

            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (InputStream docIn = Files
                    .newInputStream(Path.of("v2ch03", "transform", "employee.dat"))) {
                t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)),
                        new StreamResult(System.out));
            }
        }
    }
}

/**
 * This class reads the flat file employee.dat and reports SAX parser events to act as if it
 * was parsing an XML file.
 */
class EmployeeReader implements XMLReader {
    private ContentHandler handler;

    public void parse(InputSource source) throws IOException, SAXException {
        InputStream stream = source.getByteStream();
        var in = new BufferedReader(new InputStreamReader(stream));
        var atts = new AttributesImpl();

        if (handler == null) throw new SAXException("No content handler");

        handler.startDocument();
        handler.startElement("", "staff", "staff", atts);
        boolean done = false;
        while (!done) {
            String line = in.readLine();
            if (line == null)
                done = true;
            else {
                handler.startElement("", "employee", "employee", atts);
                var t = new StringTokenizer(line, "|");

                handler.startElement("", "name", "name", atts);
                String s = t.nextToken();
                handler.characters(s.toCharArray(), 0, s.length());
                handler.endElement("", "name", "name");

                handler.startElement("", "salary", "salary", atts);
                s = t.nextToken();
                handler.characters(s.toCharArray(), 0, s.length());
                handler.endElement("", "salary", "salary");

                atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
                atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
                atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
                handler.startElement("", "hiredate", "hiredate", atts);
                handler.endElement("", "hiredate", "hiredate");
                atts.clear();

                handler.endElement("", "employee", "employee");
            }
        }

        handler.endElement("", "staff", "staff");
        handler.endDocument();
    }

    public void setContentHandler(ContentHandler newValue) {
        handler = newValue;
    }

    public ContentHandler getContentHandler() {
        return handler;
    }

    // the following methods are just do-nothing implementations
    public void parse(String systemId) throws IOException, SAXException {
    }

    public void setErrorHandler(ErrorHandler handler) {
    }

    public ErrorHandler getErrorHandler() {
        return null;
    }

    public void setDTDHandler(DTDHandler handler) {
    }

    public DTDHandler getDTDHandler() {
        return null;
    }

    public void setEntityResolver(EntityResolver resolver) {
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public void setProperty(String name, Object value) {
    }

    public Object getProperty(String name) {
        return null;
    }

    public void setFeature(String name, boolean value) {
    }

    public boolean getFeature(String name) {
        return false;
    }
}
