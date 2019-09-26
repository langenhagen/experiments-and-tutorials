/**
 * File     MASPClassLoaderUtility.java
 * Package  org.sercho.masp.models
 * Project  ModelUtilities
 * Date     Mar 11, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>MASPClassLoaderUtility</code> TODO comment
 * 
 * @author Grzegorz Lehmann
 */
public final class MASPClassLoaderUtility {

    /**
     * <code>MASP_CLASSPATH_ENVIRONMENT_PROPERTY</code> defines the name of the
     * environment variable denoting the directory for JARs of MASP applications
     */
    public static final String MASP_CLASSPATH_ENVIRONMENT_PROPERTY = "MASP_CLASSPATH";

    private static final transient Log LOG = LogFactory.getLog(MASPClassLoaderUtility.class);

    /**
     * <code>JAR_FILTER</code> filters JAR files
     */
    private static transient final FileFilter JAR_FILTER = new FileFilter() {

        /**
         * {@inheritDoc}
         */
        public boolean accept(final File pathname) {
            return pathname.isFile() && pathname.getName().toLowerCase().endsWith(".jar");
        }
    };

    /**
     * <code>MASPClassLoaderUtility</code> constructor
     */
    private MASPClassLoaderUtility() {
        // hiding constructor of utility class
    }

    /**
     * <code>getClassLoader</code> returns a class loader for a given set of
     * paths.
     * 
     * @param paths
     *            paths to resources (directories or JAR files) with classes
     * @return ClassLoader - class loader for classes in <code>paths</code>
     */
    public static ClassLoader getClassLoader(final List<String> paths) {
        return getClassLoader(paths, MASPClassLoaderUtility.class.getClassLoader());
    }

    /**
     * <code>getClassLoader</code> returns a class loader for a given set of
     * paths.
     * 
     * @param paths
     *            paths to resources (directories or JAR files) with classes
     * @param parent
     *            parent class loader to use
     * @return ClassLoader - class loader for classes in <code>paths</code>
     */
    public static ClassLoader getClassLoader(final List<String> paths, final ClassLoader parent) {
        if(paths == null) {
            LOG.error("paths is null");
            throw new IllegalArgumentException("paths is null");
        }
        final List<URL> urlList = new LinkedList<URL>();
        for(final String jar : paths) {
            if(jar == null) {
                LOG.warn("MASPClassLoaderUtility.getClassLoader() encountered null path in paths: " + paths);
                continue;
            }
            getURLs(jar, urlList);
        }
        final URL[] urls = new URL[urlList.size()];
        urlList.toArray(urls);
        LOG.debug("Returning ClassLoader for URLs: " + urlList);
        return URLClassLoader.newInstance(urls, parent);
    }

    /**
     * <code>getURL</code> attempts to create a URL for a string. The string may
     * be a valid URL, a absolute file path or a file path relative to current
     * working directory.
     * 
     * @param path
     *            path to a file, a URL, a classpath resource, absolute or
     *            relative to {@value #MASP_CLASSPATH_ENVIRONMENT_PROPERTY} path
     *            or {@value #DEFAULT_MASP_DIRECTORY} directory inside user's
     *            home directory
     */
    private static void getURLs(final String path, final List<URL> urlList) {
        try {
            urlList.add(new URL(path));
            LOG.debug("Path is a valid URL, loading directly from URL: " + path);
        }
        catch(final MalformedURLException e) {
            LOG.debug("Not a valid URL, trying other load options for path: " + path);
            final URL url = MASPClassLoaderUtility.class.getClassLoader().getResource(path);
            if(url != null) {
                urlList.add(url);
                LOG.debug("Path points to a classpath resource: " + path);
                return;
            }
            LOG.debug("Path does not point to a classpath resource, checking if it denotes a file: " + path);
            // absolute path?
            File file = new File(path);
            if(file.exists()) {
                LOG.debug("Path denotes an existing file: " + path);
                addURLs(file, urlList);
                return;
            }
            // in MASP classpath?
            String env = getEnv(MASP_CLASSPATH_ENVIRONMENT_PROPERTY);
            if(env == null) {
                LOG.warn(MASP_CLASSPATH_ENVIRONMENT_PROPERTY + " environment property not set");
            } else {
                LOG.debug("Checking if " + path + " lies inside MASP classpath directory: " + env);
                file = new File(env + "/" + path);
                if(file.exists()) {
                    LOG.debug("MASP classpath contains resource: " + path);
                    addURLs(file, urlList);
                    return;
                }
                LOG.debug("MASP classpath does not contain resource: " + path);
            }
            LOG.debug("Checking if resource exists in the " + MASPDirectory.DEFAULT_MASP_DIRECTORY + " under user's home directory");
            // in user's home directory?
            env = getEnv(MASPDirectory.USER_DIR_ENVIRONMENT_PROPERTY);
            if(env == null) {
                LOG.warn(MASPDirectory.USER_DIR_ENVIRONMENT_PROPERTY + " environment property not set");
            } else {
                file = new File(MASPDirectory.getMASPDirectoryPath() + path);
                if(file.exists()) {
                    LOG.debug(MASPDirectory.getMASPDirectoryPath() + " MASP directory contains the resource: " + path);
                    addURLs(file, urlList);
                    return;
                }
                LOG.debug("MASP classpath does not contain resource: " + path);
            }
            LOG.warn("Failed to locate resource: " + path);
        }
    }

    private static String getEnv(final String name) {
        try {
            return System.getenv(name);
        }
        catch(final SecurityException e2) {
            LOG.warn("Access denied to the environment variable " + name);
            return null;
        }
    }

    /**
     * <code>addURLs</code> filters JAR files and adds their URLs to a given
     * list. Please note, that sub-directories are also filtered.
     * 
     * @param file
     *            file or directory with JAR files
     * @param urlList
     *            list for additional URLs
     */
    private static void addURLs(final File file, final List<URL> urlList) {
        try {
            if(file.isDirectory()) {
                // another directory, add recursive
                for(final File f : file.listFiles(JAR_FILTER)) {
                    addURLs(f, urlList);
                }
            } else {
                // file, add URL
                urlList.add(file.toURI().toURL());
            }
        }
        catch(final MalformedURLException e) {
            LOG.warn("Failed to create URL for file " + file.getAbsolutePath() + ", error: " + e.getMessage());
        }
    }
}