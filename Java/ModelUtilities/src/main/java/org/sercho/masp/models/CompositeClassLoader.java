/**
 * 
 */
package org.sercho.masp.models;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>CompositeClassLoader</code> is composed of several class loaders, which
 * it uses to load classes. The class loaders can be added freely during
 * runtime.
 * 
 * @author Grzegorz Lehmann
 * @since 1.1
 */
public final class CompositeClassLoader extends ClassLoader {

    /**
     * <code>LOG</code> for logging.
     * 
     * @generated NOT
     */
    static final transient Log LOG = LogFactory.getLog(CompositeClassLoader.class);

    /**
     * <code>classLoaders</code> holds all class loaders used by this class
     * loaders.
     */
    private volatile Map<String, ClassLoader> classLoaders = new HashMap<String, ClassLoader>();

    /**
     * <code>classLoaderCache</code> maps class names to class loaders that have
     * already successfully loaded a class specified by the name in the past.
     */
    private transient final Map<String, ClassLoader> classLoaderCache = new HashMap<String, ClassLoader>();

    /**
     * <code>CompositeClassLoader</code> constructor.
     */
    public CompositeClassLoader() {
        this(CompositeClassLoader.class.getClassLoader());
    }

    /**
     * <code>CompositeClassLoader</code> constructor.
     * 
     * @param parent
     *            parent class loader
     */
    public CompositeClassLoader(final ClassLoader parent) {
        super(parent);
    }

    /**
     * <code>addClassLoader</code> adds a new class loader to use when loading
     * classes.
     * 
     * @param id
     *            identifier for the new class loader
     * @param newClassLoader
     *            new class loader to add
     */
    public void addClassLoader(final String id, final ClassLoader newClassLoader) {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        if(newClassLoader == null) {
            throw new IllegalArgumentException("newClassLoader is null");
        }
        if(this.classLoaders.containsKey(id)) {
            // already got the class loader
            return;
        }
        final Map<String, ClassLoader> newClassLoaders = new HashMap<String, ClassLoader>(this.classLoaders);
        newClassLoaders.put(id, newClassLoader);
        this.classLoaders = newClassLoaders;
        LOG.debug("Got new ClassLoader: " + newClassLoader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
        final ClassLoader cachedLoader = this.classLoaderCache.get(name);
        if(cachedLoader != null) {
            // already successfully loaded the class in the past
            return cachedLoader.loadClass(name);
        }
        Class<?> c;
        for(final ClassLoader classLoader : this.classLoaders.values()) {
            try {
                // attempt to load the class
                c = classLoader.loadClass(name);
                // cache to successful loader
                this.classLoaderCache.put(name, classLoader);
                // return loaded class
                return c;
            }
            catch(final ClassNotFoundException e) {
                // try another one
            }
        }
        // last chance
        return super.loadClass(name, resolve);
    }
}