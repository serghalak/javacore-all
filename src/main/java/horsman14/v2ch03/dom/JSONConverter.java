package horsman14.v2ch03.dom;

import module java.base;
import module java.xml;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;

/**
 * This program displays an XML document as a tree in JSON format.
 */
class JSONConverter {
    void main(String[] args) throws Exception {
        String filename = args.length == 0 ? IO.readln("Input file: ") : args[0];
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(filename);
        Element root = doc.getDocumentElement();
        IO.println(convert(root, 0));
    }

    StringBuilder convert(Node node, int level) {
        if (node instanceof Element elem) {
            return elementObject(elem, level);
        }
        else if (node instanceof CharacterData cd) {
            return characterString(cd, level);
        }
        else {
            return pad(new StringBuilder(), level)
                    .append(jsonEscape(node.getClass().getName()));
        }
    }

    Map<Character, String> replacements = Map.of('\b', "\\b", '\f', "\\f", '\n',
            "\\n", '\r', "\\r", '\t', "\\t", '"', "\\\"", '\\', "\\\\");

    StringBuilder jsonEscape(String str) {
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

    StringBuilder characterString(CharacterData node, int level) {
        var result = new StringBuilder();
        StringBuilder data = jsonEscape(node.getData());
        if (node instanceof Comment) data.insert(1, "Comment: ");
        pad(result, level).append(data);
        return result;
    }

    StringBuilder elementObject(Element elem, int level) {
        var result = new StringBuilder();
        pad(result, level).append("{\n");
        pad(result, level + 1).append("\"name\": ");
        result.append(jsonEscape(elem.getTagName()));
        NamedNodeMap attrs = elem.getAttributes();
        if (attrs.getLength() > 0) {
            pad(result.append(",\n"), level + 1).append("\"attributes\": ");
            result.append(attributeObject(attrs));
        }
        NodeList children = elem.getChildNodes();
        if (children.getLength() > 0) {
            pad(result.append(",\n"), level + 1).append("\"children\": [\n");
            for (int i = 0; i < children.getLength(); i++) {
                if (i > 0) result.append(",\n");
                result.append(convert(children.item(i), level + 2));
            }
            result.append("\n");
            pad(result, level + 1).append("]\n");
        }
        pad(result, level).append("}");
        return result;
    }

    StringBuilder pad(StringBuilder builder, int level) {
        for (int i = 0; i < level; i++)
            builder.append("  ");
        return builder;
    }

    StringBuilder attributeObject(NamedNodeMap attrs) {
        var result = new StringBuilder("{");
        for (int i = 0; i < attrs.getLength(); i++) {
            if (i > 0) result.append(", ");
            result.append(jsonEscape(attrs.item(i).getNodeName()));
            result.append(": ");
            result.append(jsonEscape(attrs.item(i).getNodeValue()));
        }
        result.append("}");
        return result;
    }
}
