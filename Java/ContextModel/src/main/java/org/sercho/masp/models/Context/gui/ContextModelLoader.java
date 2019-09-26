package org.sercho.masp.models.Context.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sercho.masp.models.XMIUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.gui.exceptions.LoadingContextModelException;

/**
 * This class has some static methods for loading an environment.
 * 
 * @author Antonio Fernandez Zaragoza
 * 
 */
public class ContextModelLoader {

    public static final transient Log LOG = LogFactory.getLog(ContextModelLoader.class);

    /**
     * Loads and returns an environment from a file.
     * 
     * @param file
     *            the file reference.
     * @return the environment loaded.
     * @throws LoadingContextModelException
     * @throws IOException
     */
    public static Environment loadEnvironmentFromFile(final File file) throws LoadingContextModelException {

        try {
            if(LOG.isDebugEnabled()) {
                LOG.debug("Loading context model from " + file.getCanonicalPath());
            }

            return (Environment)XMIUtility.convert(new FileInputStream(file), ContextPackage.eINSTANCE).get(0);
        }
        catch(final FileNotFoundException e) {
            throw new LoadingContextModelException("File not found", file);
        }
        catch(final IOException e) {
            throw new LoadingContextModelException("Unable to load context model from file", file);
        }
    }

    /**
     * Loads and returns an environment from a file.
     * 
     * @param xmiFilePath
     *            the file path.
     * @return the environment loaded.
     * @throws IOException
     */
    public static Environment loadEnvironmentFromFile(final String xmiFilePath) throws IOException {

        final String fileName = xmiFilePath;
        File file = new File(fileName);
        if(!file.exists()) {
            file = new File(System.getProperty("user.dir") + "/" + fileName);
            if(!file.exists()) {
                LOG.error("File not found: " + fileName);
                return null;
            }
        }
        LOG.debug("Loaded context model from " + file.getCanonicalPath());
        return (Environment)XMIUtility.convert(new FileInputStream(file), ContextPackage.eINSTANCE).get(0);

    }
}