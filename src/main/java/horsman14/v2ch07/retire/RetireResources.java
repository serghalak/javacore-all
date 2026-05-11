package horsman14.v2ch07.retire;

import module java.desktop;

/**
 * These are the English non-string resources for the retirement calculator.
 */
public class RetireResources extends java.util.ListResourceBundle {
    private static final Object[][] contents = {
        // BEGIN LOCALIZE
        { "colorPre", Color.blue }, { "colorGain", Color.white }, { "colorLoss", Color.red }
        // END LOCALIZE
    };

    public Object[][] getContents() {
        return contents;
    }
}
