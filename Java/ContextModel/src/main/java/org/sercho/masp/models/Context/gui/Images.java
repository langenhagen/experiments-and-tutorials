package org.sercho.masp.models.Context.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.Context.PhysicalDevice;
import org.sercho.masp.models.Context.User;

/**
 * Central place to hold images for the GUI.
 * 
 * @author Andre Schulz
 * 
 */
public class Images {

    private static final transient Log LOG = LogFactory.getLog(Images.class);

    /**
     * Path to the images of the {@link PhysicalDevice}s.
     */
    private static final String DEVICE_IMAGE_PATH = "org/sercho/masp/models/Context/gui/images/PhysicalDeviceImages/";

    /**
     * Path to the images of the {@link User}s.
     */
    private static final String USER_IMAGE_PATH = "org/sercho/masp/models/Context/gui/images/UserImages/";

    /**
     * Path to the images for the icons.
     */
    private static final String ICON_PATH = "org/sercho/masp/models/Context/gui/images/Icons/";

    /**
     * {@link Map} of all available images for {@link PhysicalDevice}s (where
     * the device is on).
     */
    private static final Map<String, Image> DEVICE_IMAGES_ON;

    /**
     * {@link Map} of all available user images.
     */
    private static final Map<String, Image> USER_IMAGES;

    /**
     * {@link Map} of all icons.
     */
    private static final Map<String, ImageIcon> ICONS;

    /**
     * {@link Map} of all available images for {@link PhysicalDevice}s (where
     * the device is off).
     */
    private static final Map<String, Image> DEVICE_IMAGES_OFF;

    private Images() {
        // reduce visibility
    }

    static {
        DEVICE_IMAGES_OFF = new HashMap<String, Image>();
        DEVICE_IMAGES_ON = new HashMap<String, Image>();
        USER_IMAGES = new HashMap<String, Image>();
        ICONS = new HashMap<String, ImageIcon>();

        String[] fileNames;

        // +++++ device images +++++
        try {
            fileNames = getResourceListing(Images.class, DEVICE_IMAGE_PATH);

            for(final String fileName : fileNames) {

                if(fileName.isEmpty()) {
                    continue;
                }

                final InputStream is = Images.class.getClassLoader().getResourceAsStream(DEVICE_IMAGE_PATH + fileName);

                try {
                    if(fileName.contains("glow")) {
                        DEVICE_IMAGES_ON.put(fileName.substring(0, fileName.indexOf("_glow")), ImageIO.read(is));
                    } else {
                        DEVICE_IMAGES_OFF.put(fileName.substring(0, fileName.lastIndexOf(".")), ImageIO.read(is));
                    }
                }
                catch(final Exception e) {
                    LOG.warn("could not load device image file " + DEVICE_IMAGE_PATH + fileName, e);
                }
            }
        }
        catch(final URISyntaxException e) {
            VisualizerManager.error(null, "failed to load device images: " + e.getMessage());
        }
        catch(final IOException e) {
            VisualizerManager.error(null, "failed to load device images: " + e.getMessage());
        }
        catch(final UnsupportedOperationException e) {
            VisualizerManager.error(null, "failed to load device images: " + e.getMessage());
        }
        // ----- device images -----

        // +++++ user images +++++

        try {
            fileNames = getResourceListing(Images.class, USER_IMAGE_PATH);
            for(final String fileName : fileNames) {

                if(fileName.isEmpty()) {
                    continue;
                }

                final InputStream is = Images.class.getClassLoader().getResourceAsStream(USER_IMAGE_PATH + fileName);

                try {
                    USER_IMAGES.put(fileName.substring(0, fileName.lastIndexOf(".")), ImageIO.read(is));
                }
                catch(final Exception e) {
                    LOG.warn("could not load user image file " + USER_IMAGE_PATH + fileName, e);
                }
            }
        }
        catch(final URISyntaxException e) {
            VisualizerManager.error(null, "failed to load user images: " + e.getMessage());
        }
        catch(final IOException e) {
            VisualizerManager.error(null, "failed to load user images: " + e.getMessage());
        }
        catch(final UnsupportedOperationException e) {
            VisualizerManager.error(null, "failed to load user images: " + e.getMessage());
        }
        // ----- user images -----

        // +++++ icons +++++
        try {
            fileNames = getResourceListing(Images.class, ICON_PATH);

            for(final String fileName : fileNames) {

                if(fileName.isEmpty()) {
                    continue;
                }

                final InputStream is = Images.class.getClassLoader().getResourceAsStream(ICON_PATH + fileName);

                try {
                    ICONS.put(fileName.substring(0, fileName.lastIndexOf(".")), new ImageIcon(ImageIO.read(is)));
                }
                catch(final Exception e) {
                    LOG.warn("could not load icon image file " + ICON_PATH + fileName, e);
                }
            }
        }
        catch(final URISyntaxException e) {
            VisualizerManager.error(null, "failed to load icon images: " + e.getMessage());
        }
        catch(final IOException e) {
            VisualizerManager.error(null, "failed to load icon images: " + e.getMessage());
        }
        catch(final UnsupportedOperationException e) {
            VisualizerManager.error(null, "failed to load icon images: " + e.getMessage());
        }
        // ----- icons -----

        if(LOG.isDebugEnabled()) {
            LOG.debug("device images loaded: " + DEVICE_IMAGES_ON.size() + " + " + DEVICE_IMAGES_OFF.size());
            LOG.debug("user images loaded: " + USER_IMAGES.size());
            LOG.debug("icons loaded: " + ICONS.size());
        }
    }

    public static ImageIcon getImageIcon(final String key) {

        if(ICONS.containsKey(key)) {
            return ICONS.get(key);
        }

        if(LOG.isWarnEnabled()) {
            LOG.warn("no icon for " + key);
        }

        return ICONS.get("default");
    }

    /**
     * Method is looking for the right image of a <code>PhysicalDevice</code>.
     * The matching is dony by the given ID. This method is returning the image
     * for the off state of the device.
     * 
     * @param id
     *            The ID of the <code>PhysicalDevice</code>. Normaly it is
     *            <code>eClass.getName()</code>.
     */
    public static Image getPhysicalDeviceImageOff(final String id) {
        // System.out.println("getPhysicalDeviceImageOff " + id);
        Image image = DEVICE_IMAGES_OFF.get(id);

        if(image == null) {
            LOG.warn("no PhysicalDeviceImageOff for " + id);
            image = DEVICE_IMAGES_OFF.get("default");
        }

        return image;
    }// getPhysicalDeviceImageOff

    /**
     * Method is looking for the right image of a <code>PhysicalDevice</code>.
     * The matching is done by the given ID. This method is returning the image
     * for the on state of the device.
     * 
     * @param id
     *            The ID of the <code>PhysicalDevice</code>. Normally it is
     *            <code>eClass.getName()</code>.
     */
    public static Image getPhysicalDeviceImageOn(final String id) {
        // System.out.println("getPhysicalDeviceImageOn " + id);
        Image image = DEVICE_IMAGES_ON.get(id);

        if(image == null) {
            LOG.warn("no PhysicalDeviceImageOn for " + id);
            image = DEVICE_IMAGES_ON.get("default");
        }

        return image;
    }// getPhysicalDeviceImageOn

    /**
     * List directory contents for a resource folder. Not recursive. This is
     * basically a brute-force implementation. Works for regular files and also
     * JARs.
     * 
     * @author Greg Briggs
     * @param clazz
     *            Any java class that lives in the same place as the resources
     *            you want.
     * @param path
     *            Should end with "/", but not start with one.
     * @return Just the name of each member item, not the full paths.
     * @throws URISyntaxException
     * @throws IOException
     */
    private static String[] getResourceListing(final Class<?> clazz, final String path) throws URISyntaxException, IOException {
        // http://www.uofr.net/~greg/java/get-resource-listing.html

        URL dirURL = clazz.getClassLoader().getResource(path);

        if(dirURL != null && dirURL.getProtocol().equals("file")) {
            // ordinary file
            return new File(dirURL.toURI()).list();
        }

        if(dirURL == null) {
            final String me = clazz.getName().replace(".", "/") + ".class";
            dirURL = clazz.getClassLoader().getResource(me);
        }

        if(dirURL.getProtocol().equals("jar")) {
            // get resources from jar-file
            final String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
            final JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            final Enumeration<JarEntry> entries = jar.entries();
            final Set<String> result = new HashSet<String>();
            while(entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if(name.startsWith(path)) { // filter according to the path
                    String entry = name.substring(path.length());
                    final int checkSubdir = entry.indexOf("/");
                    if(checkSubdir >= 0) {
                        // if it is a subdirectory, we just return the directory
                        // name
                        entry = entry.substring(0, checkSubdir);
                    }
                    result.add(entry);
                }
            }
            return result.toArray(new String[result.size()]);
        }

        throw new UnsupportedOperationException("Cannot list files for URL " + dirURL);
    }

    public static Image getUserImage(final String id) {
        Image image = USER_IMAGES.get(id);

        if(image == null) {
            LOG.warn("no image for user " + id);

            image = USER_IMAGES.get("default");

            if(image == null) {
                LOG.warn("no default user image");
            }
        }

        return image;
    }

    public static boolean hasImageIcon(final String key) {
        return ICONS.containsKey(key);
    }

}
