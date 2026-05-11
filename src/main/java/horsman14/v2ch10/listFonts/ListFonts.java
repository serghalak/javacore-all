package horsman14.v2ch10.listFonts;

import module java.desktop;

class ListFonts {
    void main() {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        for (String fontName : fontNames)
            IO.println(fontName);
    }
}
