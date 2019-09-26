/**
 * 
 */
package org.sercho.masp.models;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <code>EcoreUtility</code> provides some convenience methods for working with
 * Ecore models and metamodels.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public final class EcoreUtility {

    /**
     * <code>getAllEPackages</code> returns all {@link EPackage}s on which model
     * elements of a given {@link EPackage} depend.
     * 
     * @param ePackage
     *            {@link EPackage} to analyze
     * @return Set&lt;EPackage&gt; - all {@link EPackage}s on which
     *         <code>ePackage</code> model elements depend (
     *         <code>ePackage</code> included)
     */
    public static Set<EPackage> getAllEPackages(final EPackage ePackage) {
        return getAllEPackages(ePackage, new HashSet<EPackage>());
    }

    private static Set<EPackage> getAllEPackages(final EPackage ePackage, final Set<EPackage> ePackages) {
        if(ePackage == null) {
            return ePackages;
        }
        if(ePackages.contains(ePackage)) {
            return ePackages;
        }
        ePackages.add(ePackage);
        for(final EPackage subPackage : ePackage.getESubpackages()) {
            getAllEPackages(subPackage, ePackages);
        }
        getAllEPackages(ePackage.getESuperPackage(), ePackages);

        for(final EClassifier eClassifier : ePackage.getEClassifiers()) {
            // check packages of types referenced by classes
            if(eClassifier instanceof EClass) {
                for(final EStructuralFeature eStructuralFeature : ((EClass)eClassifier).getEAllStructuralFeatures()) {
                    getAllEPackages(eStructuralFeature.getEType().getEPackage(), ePackages);
                }
            }
        }

        return ePackages;
    }

    private static final transient String FIELD_EINSTANCE = "eINSTANCE";

    /**
     * <code>load</code> loads an {@link EPackage} instance from an
     * {@link EPackage} class name.
     * 
     * @param ePackageClassName
     *            name of {@link EPackage} class to load
     * @return EPackage - loaded {@link EPackage}, never <code>null</code>
     * @throws RuntimeException
     *             if anything goes wrong
     */
    public static EPackage load(final String ePackageClassName) {
        if(ePackageClassName == null) {
            throw new IllegalArgumentException("ePackageClassName is null");
        }
        final Class<?> ePackageClass;
        try {
            ePackageClass = Class.forName(ePackageClassName);
        }
        catch(final ClassNotFoundException e) {
            throw new IllegalArgumentException("EPackage class " + ePackageClassName + " not found", e);
        }
        if(!EPackage.class.isAssignableFrom(ePackageClass)) {
            throw new IllegalArgumentException(ePackageClassName + " class is not an EPackage");
        }

        final Field f;
        try {
            f = ePackageClass.getField(FIELD_EINSTANCE);
        }
        catch(final SecurityException e) {
            throw new IllegalStateException("Cannot access field " + FIELD_EINSTANCE + " of EPackage class " + ePackageClassName, e);
        }
        catch(final NoSuchFieldException e) {
            throw new IllegalStateException("EPackage class " + ePackageClassName + " does not declare field " + FIELD_EINSTANCE, e);
        }
        try {
            final Object ePackage = f.get(null);
            if(ePackage instanceof EPackage) {
                return (EPackage)ePackage;
            }
            throw new IllegalArgumentException(FIELD_EINSTANCE + " field of " + ePackageClassName + " does not hold an EPackage but " + ePackage);
        }
        catch(final IllegalAccessException e) {
            throw new IllegalStateException("Cannot access field " + FIELD_EINSTANCE + " of EPackage class " + ePackageClassName, e);
        }
    }

    /**
     * <code>singleton</code> returns a modifiable {@link EList} containing one
     * element.
     * 
     * @param <E>
     *            type of elements to put in the list
     * @param element
     *            element to put in the list
     * @return EList&lt;E&gt; - a modifiable {@link EList} with one element
     */
    public static <E> EList<E> singleton(final E element) {
        final EList<E> eList = new BasicEList<E>(1);
        eList.add(element);
        return eList;
    }

    /**
     * <code>list</code> creates an {@link EList}.
     * 
     * @param <E>
     *            type of elements to put in the list
     * @param elements
     *            elements to put in the list
     * @return EList&lt;E&gt; - a modifiable {@link EList} containing
     *         <code>elements</code> or <code>null</code> if
     *         <code>elements</code> is <code>null</code>
     * @since 1.1
     */
    public static <E> EList<E> list(E... elements) {
        if(elements == null) {
            return null;
        }
        final EList<E> eList = new BasicEList<E>(elements.length);
        for(int i = 0; i < elements.length; i++) {
            eList.add(elements[i]);
        }
        return eList;
    }

    private EcoreUtility() {
        // hiding constructor of utility class
    }
}
