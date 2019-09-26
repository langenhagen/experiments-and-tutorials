/**
 * File     MASPClassLoaderUtilityTest.java
 * Package  org.sercho.masp.models
 * Project  ModelUtilities
 * Date     Mar 11, 2008
 * Web      www.dai-labor.de
 * 
 * @author  Grzegorz Lehmann
 */
package org.sercho.masp.models;

import java.util.Collections;

import junit.framework.TestCase;

/**
 * <code>MASPClassLoaderUtilityTest</code> tests {@link MASPClassLoaderUtility}.
 * 
 * @author Grzegorz Lehmann
 */
public class MASPClassLoaderUtilityTest extends TestCase {

    /**
     * <code>CLASSPATH_JAR</code>
     */
    private static final String CLASSPATH_JAR = "src/test/";
    
    /**
     * <code>CLASSPATH_JAR_TEST_CLASS</code>
     */
    private static final String CLASSPATH_JAR_TEST_CLASS = "org.sercho.masp.models.ServiceModel.ClasspathTestClass";
    
    /**
     * Test method for {@link MASPClassLoaderUtility#getClassLoader(java.util.List)}.
     */
    public final void testLoadClass() {
        ClassLoader loader = MASPClassLoaderUtility.getClassLoader(Collections.<String>emptyList());
        try {
            fail("MASPClassLoaderUtility loaded a class although it is not on classpath " + Class.forName(CLASSPATH_JAR_TEST_CLASS, false, loader));
        }
        catch(ClassNotFoundException e) {
            // okay, expected
        }
        loader = MASPClassLoaderUtility.getClassLoader(Collections.singletonList(CLASSPATH_JAR));
        try {
            Class.forName(CLASSPATH_JAR_TEST_CLASS, false, loader);
            // okay
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            fail(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
