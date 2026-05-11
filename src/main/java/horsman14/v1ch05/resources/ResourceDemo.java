package horsman14.v1ch05.resources;

import module java.base;
import module java.desktop;

/**
 */
class ResourceDemo {
    void main() throws Exception {
        URL aboutURL = getClass().getResource("/images/about.gif");
        var icon = new ImageIcon(aboutURL);

        InputStream stream = getClass().getResourceAsStream("data/about.txt");
        var about = new String(stream.readAllBytes());

        InputStream stream2 = getClass().getResourceAsStream("data/title.txt");
        var title = new String(stream2.readAllBytes()).strip();

        JOptionPane.showMessageDialog(null, about, title, JOptionPane.INFORMATION_MESSAGE,
                icon);
    }
}
