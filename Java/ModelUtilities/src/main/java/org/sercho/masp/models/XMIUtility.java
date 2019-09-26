/**
 * File     XMIUtility.java
 * Package  org.sercho.masp.models
 * Project  ContextModel
 * Date     Oct 10, 2007
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * <code>XMIUtility</code> helps converting EMF model objects ({@link EObject}s)
 * into {@link String}s of XMI data and loading them back.
 * 
 * @author Grzegorz Lehmann
 */
public final class XMIUtility {

    /**
     * <code>RESOURCE_SET</code> reused to create {@link Resource}s.
     */
    private static final ResourceSet RESOURCE_SET = new ResourceSetImpl();
    static {
        RESOURCE_SET.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
    }

    /**
     * <code>STREAM</code> reused for String creation
     */
    private static final ByteArrayOutputStream STREAM = new ByteArrayOutputStream();

    /**
     * <code>XMIUtility</code> constructor
     */
    private XMIUtility() {
        // hiding constructor of utility class
    }

    /**
     * <code>convert</code> converts some EObjects into an XMI string.
     * 
     * @param objects
     *            model elements to store as XMI
     * @return String - XMI string
     */
    public static synchronized String convert(final EObject... objects) {
        final Resource resource = RESOURCE_SET.createResource(URI.createURI("http:///My.library"));
        for(final EObject e : objects) {
            resource.getContents().add(e);
        }
        try {
            resource.save(STREAM, null);
        }
        catch(final IOException e) {
            e.printStackTrace();
        }
        final String xml = STREAM.toString();
        STREAM.reset();
        return xml;
    }

    /**
     * <code>convert</code> creates an EObject out of XMI data from an input
     * stream.
     * 
     * @param in
     *            input stream to read from
     * @return EList&lt;EObject&gt; - list of loaded resources
     * @throws IOException
     *             if <code>in</code> could not be read
     */
    public static synchronized EList<EObject> convert(final InputStream in) throws IOException {
        if(in == null) {
            throw new IllegalArgumentException("in is null");
        }
        final Resource resource = RESOURCE_SET.createResource(URI.createURI("http:///My.library"));
        resource.load(in, null);
        return resource.getContents();
    }

    /**
     * <code>convert</code> loads {@link EObject}s from an input stream with XMI
     * data.
     * 
     * @param in
     *            input stream with XMI resource data
     * @param packages
     *            packages needed for the XMI resource
     * @return EList&lt;EObject&gt; - contents of the XMI resource
     * @throws IOException
     *             if resource cannot be loaded
     */
    public static EList<EObject> convert(final InputStream in, final EPackage... packages) throws IOException {
        if(in == null) {
            throw new IllegalArgumentException("in is null");
        }
        if(packages == null) {
            throw new IllegalArgumentException("packages is null");
        }
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
        for(final EPackage p : packages) {
            // register package
            resourceSet.getPackageRegistry().put(p.getNsURI(), p);
        }
        final Resource resource = resourceSet.createResource(URI.createURI("http:///My.library"));
        resource.load(in, null);
        return resource.getContents();
    }

    /**
     * <code>convert</code> loads {@link EObject}s from a {@link String}
     * containing XMI data.
     * 
     * @param <E>
     *            type of model element expected
     * @param xmiString
     *            XMI string
     * @param packages
     *            packages needed for the XMI resource
     * @return E - model from the XMI resource
     * @since 1.1
     */
    @SuppressWarnings("unchecked")
    public static <E extends EObject> E convert(final String xmiString, final EPackage... packages) {
        if(xmiString == null) {
            throw new IllegalArgumentException("xmiString is null");
        }
        final InputStream in = new ByteArrayInputStream(xmiString.getBytes());
        try {
            return (E)convert(in, packages).get(0);
        }
        catch(IOException e) {
            // should never happen!
            throw new IllegalStateException("Unexpected IOException occured", e);
        }
        finally {
            try {
                in.close();
            }
            catch(IOException e) {
                // should never happen, ignore!
            }
        }
    }

    /**
     * <code>saveAsXMI</code> saves a model into <code>file</code> in XMI
     * format. If <code>file</code> does not exist it will be created. If
     * <code>file</code> exists it will be overwritten.
     * 
     * @param model
     *            model to store in XMI format
     * @param file
     *            file in which <code>model</code> should be stored
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code>
     * @throws IOException
     *             if any I/O error occurs
     */
    public static void saveAsXMI(EObject model, File file) throws IOException {
        if(model == null) {
            throw new IllegalArgumentException("model is null");
        }
        if(file == null) {
            throw new IllegalArgumentException("file is null");
        }
        if(file.exists()) {
            if(file.isDirectory()) {
                throw new IOException("Destination file " + file.getAbsolutePath() + " is a directory");
            }
            if(!file.canWrite()) {
                throw new IOException("No write permission for destination file " + file.getAbsolutePath());
            }
        } else {
            file.createNewFile();
        }
        final String xmi = XMIUtility.convert(model);
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)), xmi.length());
        try {
            out.write(xmi);
            out.flush();
        }
        finally {
            try {
                out.close();
            }
            catch(IOException e) {
                // nothing to do here
            }
        }
    }
}