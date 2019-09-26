/**
 * 
 */
package org.sercho.masp.models;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EFactory;

/**
 * <code>EMFTestCase</code> TODO comment
 * @todo add missing JavaDoc
 *
 * @param <E>
 * 				type of {@link EFactory} for this test
 * @author Grzegorz Lehmann
 */
public abstract class EMFTestCase<E extends EFactory> extends TestCase {

	/**
	 * <code>factory</code> for this test
	 */
	protected final E factory;
	
	/**
	 * <code>EMFTestCase</code> constructor.
	 *
	 * @param testFactory
	 * 				factory to use in this test
	 */
	protected EMFTestCase(E testFactory) {
		if(testFactory == null) {
			throw new IllegalArgumentException("testFactory is null");
		}
		this.factory = testFactory;
	}
}