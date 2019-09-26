/**
 * 
 */
package org.sercho.masp.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <code>MASPDirectory</code> provides easy access to a MASP directory.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public final class MASPDirectory {

    private static final transient Log LOG = LogFactory.getLog(MASPClassLoaderUtility.class);

    /**
     * <code>USER_DIR_ENVIRONMENT_PROPERTY</code> denotes the name of the
     * environment property holding the path to user directory.
     */
    public static final String USER_DIR_ENVIRONMENT_PROPERTY = "user.home";

    /**
     * <code>DEFAULT_MASP_DIRECTORY</code> denotes the name of the default MASP
     * directory (inside of user's home directory). This directory is used by
     * the MASP for storing different configuration files, JARs, models, etc.
     */
    public static final String DEFAULT_MASP_DIRECTORY = "/.masp/";

    private static volatile String MASP_DIRECTORY_PATH;

    /**
     * <code>getMASPDirectoryPath</code> returns the path to the MASP directory.
     * 
     * @return String - absolute path to the MASP directory
     */
    public static String getMASPDirectoryPath() {
        if(MASP_DIRECTORY_PATH == null) {
            String userDir = null;
            try {
                userDir = System.getProperty(USER_DIR_ENVIRONMENT_PROPERTY);
            }
            catch(final SecurityException e) {
                // this really should not happen
                LOG.warn("Access denied to the environment variable " + USER_DIR_ENVIRONMENT_PROPERTY, e);
            }
            if(userDir == null) {
                MASP_DIRECTORY_PATH = new File(DEFAULT_MASP_DIRECTORY).getAbsolutePath();
            } else {
                MASP_DIRECTORY_PATH = userDir + DEFAULT_MASP_DIRECTORY;
            }
        }
        return MASP_DIRECTORY_PATH;
    }

    /**
     * <code>getMASPDirectory</code> returns a {@link File} representing the
     * MASP directory. This method creates the directory if it does not yet
     * exist.
     * 
     * @return File - file representing the MASP directory
     */
    public static File getMASPDirectory() {
        final File maspDirectory = new File(getMASPDirectoryPath());
        if(!maspDirectory.exists()) {
            maspDirectory.mkdir();
        }
        return maspDirectory;
    }

    /**
     * <code>loadModelFromXMI</code> loads a model from an XMI file in the MASP
     * directory.
     * 
     * @param fileName
     *            name of the XMI file containing the model
     * @param ePackages
     *            {@link EPackage}s of the model
     * @return EObject - the loaded model, never <code>null</code>
     * @throws IllegalArgumentException
     *             if <code>fileName</code> is <code>null</code>
     * @throws IOException
     *             if anything goes wrong
     */
    public static final EObject loadModelFromXMI(final String fileName, final EPackage... ePackages) throws IOException {
        if(fileName == null) {
            throw new IllegalArgumentException("fileName argument must not be null in method loadModelFromXMI");
        }
        final String maspDirectoryPath = getMASPDirectoryPath();
        final String filePath = maspDirectoryPath + "/" + fileName;
        final File file = new File(filePath);
        if(!file.exists()) {
            throw new FileNotFoundException(fileName + " does not exist in MASP directory " + maspDirectoryPath);
        }
        if(!file.canRead()) {
            throw new IOException("Missing permission to read " + fileName + " from MASP directory " + maspDirectoryPath);
        }
        return XMIUtility.convert(new FileInputStream(file), ePackages).get(0);
    }

    private static Map<String, EObject> SINGLETON_MODELS = new HashMap<String, EObject>();

    /**
     * <code>loadModelFromXMI</code> loads a model from an XMI file in the MASP
     * directory.
     * 
     * @param fileName
     *            name of the XMI file containing the model
     * @param ePackages
     *            {@link EPackage}s of the model
     * @return EObject - the loaded model, never <code>null</code>
     * @throws IllegalArgumentException
     *             if <code>fileName</code> is <code>null</code>
     * @throws IOException
     *             if anything goes wrong
     */
    public static final EObject loadSingletonModelFromXMI(final String fileName, final EPackage... ePackages) throws IOException {
        synchronized(SINGLETON_MODELS) {
            EObject model = SINGLETON_MODELS.get(fileName);
            if(model == null) {
                model = loadModelFromXMI(fileName, ePackages);
                SINGLETON_MODELS.put(fileName, model);
            }
            return model;
        }
    }

    /**
     * <code>saveModelToXMI</code> saves a model in XMI format into a file in
     * the MASP directory. If the file already exists, it will be overwritten.
     * 
     * @param fileName
     *            name for the XMI file to which the model should be saved
     * @param model
     *            model to save
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     * @throws IOException
     *             if anything goes wrong
     */
    public static final void saveModelToXMI(final String fileName, final EObject model) throws IOException {
        if(fileName == null) {
            throw new IllegalArgumentException("fileName argument must not be null in method saveModelToXMI");
        }
        if(model == null) {
            throw new IllegalArgumentException("model argument must not be null in method saveModelToXMI");
        }
        XMIUtility.saveAsXMI(model, new File(getMASPDirectoryPath() + "/" + fileName));
    }

    /**
     * <code>MASPDirectory</code> constructor.
     */
    private MASPDirectory() {
        // hiding constructor of utility class
    }
}
