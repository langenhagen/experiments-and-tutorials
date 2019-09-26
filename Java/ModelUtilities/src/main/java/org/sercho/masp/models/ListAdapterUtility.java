/**
 * 
 */
package org.sercho.masp.models;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * <code>ListAdapterUtility</code> allows to easily observe model elements with
 * lists.
 * 
 * @author Grzegorz Lehmann
 * @since 1.0.5
 */
public final class ListAdapterUtility {

    /**
     * <code>observe</code> allows to easily observe model elements with lists.
     * If elements are added to or removed from observed list the
     * <code>observer</code> will become notified.
     * 
     * @param <E>
     *            type of elements in the list
     * @param eObject
     *            object containing the list
     * @param eReference
     *            reference pointing to the list
     * @param type
     *            class of elements in the list
     * @param observer
     *            observer to call when the list changes
     * @return Disposeable - object to dispose when the list should no longer be
     *         observed
     * @throws IllegalArgumentException
     *             if any argument is <code>null</code> or inconsistent
     */
    public static <E> Disposeable observe(final EObject eObject, final EReference eReference, final Class<E> type, final ListObserver<E> observer) {
        if(eObject == null) {
            throw new IllegalArgumentException("subject is null");
        }
        if(eReference == null) {
            throw new IllegalArgumentException("eReference is null");
        }
        if(type == null) {
            throw new IllegalArgumentException("type is null");
        }
        if(observer == null) {
            throw new IllegalArgumentException("observer is null");
        }
        if(!eObject.eClass().getEAllReferences().contains(eReference)) {
            throw new IllegalArgumentException("EObject of EClass " + eObject.eClass() + " does not feature the EReference: " + eReference);
        }
        return new DisposeableAdapter(ListAdapter.create(eObject, eReference, type, observer));
    }

    /**
     * <code>ListAdapter</code> handles the events about the list.
     * 
     * @param <E>
     *            type of elements in the list
     * @author Grzegorz Lehmann
     */
    private static class ListAdapter<E> extends SingletonAdapterImpl {

        private final ListObserver<E> observer;

        private final Class<E> type;

        private final Object feature;

        static <E> ListAdapter<E> create(final EObject eObject, final EReference eReference, final Class<E> observedType, final ListObserver<E> listObserver) {
            final ListAdapter<E> adapter = new ListAdapter<E>(eReference, observedType, listObserver);
            final Object o = eObject.eGet(eReference);
            if(o != null) {
                adapter.addedMany((Collection<?>)o);
            }
            eObject.eAdapters().add(adapter);
            return adapter;
        }

        /**
         * <code>ListAdapter</code> constructor.
         * 
         * @param eObject
         *            object containing the list
         * @param eReference
         *            list reference to observe
         * @param observedType
         *            class of observed elements
         * @param listObserver
         *            callback
         */
        private ListAdapter(final EReference eReference, final Class<E> observedType,
                final ListObserver<E> listObserver) {
            if(!observedType.isAssignableFrom(eReference.getEReferenceType().getInstanceClass())) {
                throw new IllegalArgumentException("EReference " + eReference + " does not point to elements assignable to class " + observedType.getName() + " but " + eReference.getEReferenceType().getInstanceClass().getName());
            }
            if(!eReference.isMany()) {
                throw new IllegalArgumentException("ERefernce does not point to a list: " + eReference);
            }
            this.type = observedType;
            this.observer = listObserver;
            this.feature = eReference;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(final Notification msg) {
            if(msg.getFeature() == this.feature) {
                if(msg.getEventType() == Notification.ADD) {
                    this.observer.added(this.type.cast(msg.getNewValue()));
                } else if(msg.getEventType() == Notification.ADD_MANY) {
                    addedMany((Collection<?>)msg.getNewValue());
                } else if(msg.getEventType() == Notification.REMOVE) {
                    this.observer.removed(this.type.cast(msg.getOldValue()));
                } else if(msg.getEventType() == Notification.REMOVE_MANY) {
                    removedMany((Collection<?>)msg.getOldValue());
                } else if(msg.getEventType() == Notification.SET) {
                    // remove all from old list
                    Collection<?> elements = (Collection<?>)msg.getOldValue();
                    if(elements != null) {
                        removedMany(elements);
                    }
                    // add all from new list
                    elements = (Collection<?>)msg.getNewValue();
                    if(elements != null) {
                        addedMany(elements);
                    }
                }
            }
        }

        private void addedMany(final Collection<?> elements) {
            for(final Object element : new ArrayList<Object>(elements)) {
                this.observer.added(this.type.cast(element));
            }
        }

        private void removedMany(final Collection<?> elements) {
            for(final Object element : new ArrayList<Object>(elements)) {
                this.observer.removed(this.type.cast(element));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void dispose() {
            super.dispose();
            this.observer.disposed();
        }
    }

    /**
     * <code>ListObserver</code> allows observing lists.
     * 
     * @param <E>
     *            type of elements in the observed list
     * @author Grzegorz Lehmann
     * @since 5.0.3
     */
    public static interface ListObserver<E> {

        /**
         * <code>added</code> informs this observer about a new element added to
         * the list.
         * 
         * @param element
         *            new element, <code>null</code> disallowed
         * @throws IllegalArgumentException
         *             if <code>element</code> is <code>null</code>
         */
        void added(E element);

        /**
         * <code>removed</code> informs this observer about an element that has
         * been removed from the list.
         * 
         * @param element
         *            removed element, <code>null</code> disallowed
         * @throws IllegalArgumentException
         *             if <code>element</code> is <code>null</code>
         */
        void removed(E element);

        /**
         * <code>disposed</code> informs this observer, that it will receive no
         * more notifications.
         */
        void disposed();
    }

    private static final class DisposeableAdapter implements Disposeable {

        private final SingletonAdapterImpl adapter;

        /**
         * <code>DisposeableAdapter</code> constructor.
         * 
         * @param disposeableAdapter
         *            adapter that can be disposed
         */
        DisposeableAdapter(final SingletonAdapterImpl disposeableAdapter) {
            this.adapter = disposeableAdapter;
        }

        private boolean disposed = false;

        /**
         * {@inheritDoc}
         */
        public synchronized void dispose() {
            if(this.disposed) {
                return;
            }
            this.adapter.dispose();
            this.disposed = true;
        }
    }

    private ListAdapterUtility() {
        // hiding constructor of utility class
    }
}
