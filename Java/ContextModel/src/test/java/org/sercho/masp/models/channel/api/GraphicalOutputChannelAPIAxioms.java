package org.sercho.masp.models.channel.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

public class GraphicalOutputChannelAPIAxioms {

    /**
     * newPointingX(a) ( (a < -1) => throw IllegalArgumentException ) \and ( (a
     * >= -1) => void )
     */
    public static void newPointingXParameters(final GraphicalOutputChannelAPI subject, final int a) {
        try {
            subject.newPointingX(a);
        }
        catch(final IllegalArgumentException e) {
            // Exception only allowed on values lower than -1
            if(a < -1) {
                return;
            } else {
                throw e;
            }
        }
        // error if no exception on values >-1
        assertTrue("For values (" + a + ") lower than -1 an IllegalArgumentException must be thrown. (class tested: " + subject.getClass() + ")", a >= -1);
    }

    /**
     * newPointingY(a) ( (a < -1) => throw IllegalArgumentException ) \and ( (a
     * >= -1) => void )
     */
    public static void newPointingYParameters(final GraphicalOutputChannelAPI subject, final int a) {

        try {
            subject.newPointingY(a);
        }
        catch(final IllegalArgumentException e) {
            // Exception only allowed on values lower than -1
            if(a < -1) {
                return;
            } else {
                throw e;
            }
        }
        // error if no exception on values >-1
        assertTrue("For values (" + a + ") lower than -1 an IllegalArgumentException must be thrown. (class tested: " + subject.getClass() + ")", a >= -1);
    }

    /**
     * newHeight(id, a) ( (id == null) => throw IllegalArgumentException ) \and
     * ( (a < 0) => throw IllegalArgumentException ) \and ( (a >= 0) =>
     * undefined )
     */
    public static void newHeightParameters(final GraphicalOutputChannelAPI subject, final String s, final int a) {
        try {
            subject.newHeight(s, a);
        }
        catch(final IllegalArgumentException iae) {
            // Exception only allowed on values lower than 0
            if(a < 0) {
                return;
            } else if(s == null) {
                return;
            }
        }
        // error if no exception on values >0 - s==null is covered by
        // ReflectionTest
        assertFalse("For values lower than 0 an IllegalArgumentException must be thrown.", (a < 0));
    }

    /**
     * newWidth(id, a) ( (id == null) => throw IllegalArgumentException ) \and (
     * (a < 0) => throw IllegalArgumentException ) \and ( (a >= 0) => undefined
     * )
     */
    public static void newWidthParameters(final GraphicalOutputChannelAPI subject, final String s, final int a) {
        try {
            subject.newWidth(s, a);
        }
        catch(final IllegalArgumentException iae) {
            // Exception only allowed on values lower than 0
            if(a < 0) {
                return;
            } else if(s == null) {
                return;
            }
        }
        // error if no exception on values >0 - s==null is covered by
        // ReflectionTest
        assertFalse("For values lower than 0 an IllegalArgumentException must be thrown.", (a < 0));
    }

    /**
     * newText(id, text) ( (id == null) => throw IllegalArgumentException ) \and
     * ( (text == null) => throw IllegalArgumentExcpetion ) \and ( ((id != null)
     * \and (text != null)) => undefined )
     */
    public static void newTextParameters(final GraphicalOutputChannelAPI subject, final String id, final String text) {
        try {
            subject.newText(id, text);
        }
        catch(final IllegalArgumentException e) {
            if(id == null) {
                return;
            } else if(text == null) {
                return;
            }
        }
    }

    /**
     * nextURL(id, url) ( (id == null) => throw IllegalArgumentException ) \and
     * ( (url == null) => throw IllegalArgumentException ) \and ( ((id != null)
     * \and (url != null)) => undefined )
     * 
     * @TODO add IllegalArgumentExcpetion on malformed url
     */
    public static void newURLParameters(final GraphicalOutputChannelAPI subject, final String id, final String url) {
        try {
            subject.newURL(id, url);
        }
        catch(final IllegalArgumentException e) {
            if(id == null) {
                return;
            } else if(url == null) {
                return;
            }
        }
    }

    /**
     * addButton(id, styleId, x, y, z, width, height, text) ( ((id == null) \or
     * (styleID == null) \or (text == null)) => throw IllegalArgumentException )
     * \and ( ((width < 0) \or (height < 0)) => throw IllegalArgumentException )
     * \and ( ((id != null) \and (styleID != null) \and (text != null)) =>
     * undefined ) \and ( ((width >= 0) \and (height >= 0)) => undefined )
     */
    public static void addButtonParameters(final GraphicalOutputChannelAPI subject, final String id, final String styleId, final int x, final int y, final int z, final int width, final int height, final String text) {
        try {
            subject.addButton(id, styleId, x, y, z, width, height, text);
        }
        catch(final IllegalArgumentException iae) {
            if((id == null) || (styleId == null) || (text == null)) {
                return;
            } else if((width < 0) || (height < 0)) {
                return;
            }
        }

        assertFalse("If width or hight are lower than 0 an IllegalArgumentException must be thrown!", (width < 0) || (height < 0));
    }

    /**
     * addImage(id, styleId, url, x, y, z, width, height) ( ((id == null) \or
     * (styleID == null) \or (url == null)) => throw IllegalArgumentException )
     * \and ( ((width < 0) \or (height < 0)) => throw IllegalArgumentException )
     * \and ( ((id != null) \and (styleID != null) \and (url != null)) =>
     * undefined ) \and ( ((width >= 0) \and (height >= 0)) => undefined )
     * 
     * @TODO check url-schema
     */
    public static void addImageParameters(final GraphicalOutputChannelAPI subject, final String id, final String styleId, final String url, final int x, final int y, final int z, final int width, final int height) {
        try {
            subject.addImage(id, styleId, url, x, y, z, width, height);
        }
        catch(final IllegalArgumentException iae) {
            if((id == null) || (styleId == null) || (url == null)) {
                return;
            } else if((width < 0) || (height < 0)) {
                return;
            }
        }

        assertFalse("If width or hight are lower than 0 an IllegalArgumentException must be thrown!", (width < 0) || (height < 0));
    }

    /**
     * addTextLabel(id, styleId, x, y, z, width, height, text) ( ((id == null)
     * \or (styleID == null) \or (text == null)) => throw
     * IllegalArgumentException ) \and ( ((width < 0) \or (height < 0)) => throw
     * IllegalArgumentException ) \and ( ((id != null) \and (styleID != null)
     * \and (text != null)) => undefined ) \and ( ((width >= 0) \and (height >=
     * 0)) => undefined )
     */
    public static void addTextLabelParameters(final GraphicalOutputChannelAPI subject, final String id, final String styleId, final int x, final int y, final int z, final int width, final int height, final String text) {
        try {
            subject.addTextLabel(id, styleId, x, y, z, width, height, text);
        }
        catch(final IllegalArgumentException iae) {
            if((id == null) || (styleId == null) || (text == null)) {
                return;
            } else if((width < 0) || (height < 0)) {
                return;
            }
        }

        assertFalse("If width or hight are lower than 0 an IllegalArgumentException must be thrown!", (width < 0) || (height < 0));
    }

    /**
     * removeLookAndFeel(id) ((id == null) => throw IllegalArgumentException)
     * \and ((id != null) => undefined)
     */
    public static void removeLookAndFeelParameters(final GraphicalOutputChannelAPI subject, final String id) {
        // is covered completely by ReflectionTest
    }

    /**
     * setCallback(c) ((c == null) => throw IllegalArgumentException) \and ((c
     * != null) => undefined)
     */
    public static void setCallbackParameters(final GraphicalOutputChannelAPI subject, final TwoDimensionalCallback c) {
        // is covered completely by ReflectionTest
    }

    /**
     * setLookAndFeel(id, bgColor, fColor, fName, fSize, fStyle, bColor, bWidth)
     * color := /([0-9a-fA-F]{3}){1,2}/ style :=
     * /(Normal|Bold|Italic|BoldItalic)/ ( ((id == null) \or (bgColor == null)
     * \or (fColor == null) \or (fName == null) \or (fStyle == null)) => throw
     * IllegalArgumentException ) \and ( ((fSize <= 0) \or (bWidth < 0) \or
     * !(bgColor =~ color) \or !(fColor =~ color) \or !(fStyle =~ style) \or
     * !(bColor =~ color)) => throw IllegalArgumentException ) \and ( ((id !=
     * null) \and (bgColor =~ color) \and (fColor =~ color) \and (fName != null)
     * \and (fSize > 0) \and (fStyle =~ style) \and ((bColor == null) \or
     * (bColor =~ color)) \and (bWidth >= 0)) => void)
     */
    public static void setLookAndFeelParameters(final GraphicalOutputChannelAPI subject, final String id, final String bgColor, final String fColor, final String fName, final int fSize, final String fStyle, final String bColor, final int bWidth) {
        final Pattern color = Pattern.compile("([0-9a-fA-F]{3}){1,2}");
        final Pattern style = Pattern.compile("(Normal|Bold|Italic|BoldItalic)");

        try {
            subject.setLookAndFeel(id, bgColor, fColor, fName, fSize, fStyle, bColor, bWidth);
        }
        catch(final IllegalArgumentException iae) {
            if((id == null) || (bgColor == null) || (fColor == null) || (fName == null) || (fStyle == null)) {
                return;
            } else if((fSize <= 0) || (bWidth < 0) || (!color.matcher(bgColor).matches()) || (!color.matcher(fColor).matches()) || (!style.matcher(fStyle).matches()) || (!color.matcher(bColor).matches())) {
                return;
            } else if((id != null) && (color.matcher(bgColor).matches()) && (color.matcher(fColor).matches()) && (fName != null) && (fSize > 0) && ((bColor == null) || (color.matcher(bColor).matches())) && (bWidth >= 0)) {
                throw iae;
            }
        }

        assertFalse("Fontsize must be tested to not being negative!", fSize <= 0);
        assertFalse("Borderwidth must be tested to not being zero!", bWidth == 0);
        assertFalse("Borderwidth must be tested to not being negative!", bWidth < 0);
        assertFalse("Backgroundcolor can be non-hex value (not allowed)!", !color.matcher(bgColor).matches());
        assertFalse("Fontcolor can be non-hex value (not allowed)!", !color.matcher(fColor).matches());
        assertFalse("Testing for Fontstyle is wrong!", !style.matcher(fStyle).matches());
        assertFalse("Bordercolor can be non-hex value (not allowed)!", !color.matcher(bColor).matches());
    }

    /**
     * setLookAndFeelOfElement(id, styleId) ( ( (id == null) \or (styleId ==
     * null) ) => throw IllegalArgumentException ) \and ( ( (id != null) \and
     * (styleId != null) ) => undefined )
     */
    public static void setLookAndFeelOfElementParameters(final GraphicalOutputChannelAPI subject, final String id, final String styleId) {
        try {
            subject.setLookAndFeelOfElement(id, styleId);
        }
        catch(final IllegalArgumentException iae) {
            if((id == null) || (styleId == null)) {
                return;
            }
        }

        assertFalse((id == null) || (styleId == null));
    }
}
