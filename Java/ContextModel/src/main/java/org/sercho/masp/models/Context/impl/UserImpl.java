/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sercho.masp.models.Context.Assistant;
import org.sercho.masp.models.Context.ContextFactory;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.Environment;
import org.sercho.masp.models.Context.InteractionResource;
import org.sercho.masp.models.Context.User;
import org.sercho.masp.models.Context.Vector;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>User</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getBirthDate <em>
 * Birth Date</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getSurname <em>
 * Surname</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#isLeftHanded <em>Left
 * Handed</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getResources <em>
 * Resources</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getLineOfVision <em>
 * Line Of Vision</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getIrExperience <em>
 * Ir Experience</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#isFollowMe <em>Follow
 * Me</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getEnvironment <em>
 * Environment</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getCurrentAssistants
 * <em>Current Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getPastAssistants
 * <em>Past Assistants</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.UserImpl#getInstalledAssistants
 * <em>Installed Assistants</em>}</li>
 * <li>{@link org.sercho.masp.models.Context.impl.UserImpl#getPasswordHash <em>
 * Password Hash</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UserImpl extends ElementWithPositionImpl implements User {

    /**
     * <code>LOG</code> is the logger for this class.
     * 
     * @generated NOT
     */
    public static final transient Log LOG = LogFactory.getLog(UserImpl.class);

    /**
     * The default value of the '{@link #getBirthDate() <em>Birth Date</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBirthDate()
     * @generated
     * @ordered
     */
    protected static final String BIRTH_DATE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBirthDate() <em>Birth Date</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBirthDate()
     * @generated
     * @ordered
     */
    protected String birthDate = BIRTH_DATE_EDEFAULT;

    /**
     * The default value of the '{@link #getSurname() <em>Surname</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSurname()
     * @generated
     * @ordered
     */
    protected static final String SURNAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSurname() <em>Surname</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSurname()
     * @generated
     * @ordered
     */
    protected String surname = SURNAME_EDEFAULT;

    /**
     * The default value of the '{@link #isLeftHanded() <em>Left Handed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isLeftHanded()
     * @generated
     * @ordered
     */
    protected static final boolean LEFT_HANDED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isLeftHanded() <em>Left Handed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isLeftHanded()
     * @generated
     * @ordered
     */
    protected boolean leftHanded = LEFT_HANDED_EDEFAULT;

    /**
     * The cached value of the '{@link #getResources() <em>Resources</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResources()
     * @generated
     * @ordered
     */
    protected EList<InteractionResource> resources;

    /**
     * The cached value of the '{@link #getLineOfVision()
     * <em>Line Of Vision</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getLineOfVision()
     * @generated
     * @ordered
     */
    protected Vector lineOfVision;

    /**
     * The cached value of the '{@link #getIrExperience()
     * <em>Ir Experience</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getIrExperience()
     * @generated
     * @ordered
     */
    protected Map<Class<? extends InteractionResource>, String> irExperience;

    /**
     * The default value of the '{@link #isFollowMe() <em>Follow Me</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isFollowMe()
     * @generated
     * @ordered
     */
    protected static final boolean FOLLOW_ME_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFollowMe() <em>Follow Me</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isFollowMe()
     * @generated
     * @ordered
     */
    protected boolean followMe = FOLLOW_ME_EDEFAULT;

    /**
     * The cached value of the '{@link #getCurrentAssistants()
     * <em>Current Assistants</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getCurrentAssistants()
     * @generated
     * @ordered
     */
    protected EList<Assistant> currentAssistants;

    /**
     * The cached value of the '{@link #getPastAssistants()
     * <em>Past Assistants</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPastAssistants()
     * @generated
     * @ordered
     */
    protected EList<Assistant> pastAssistants;

    /**
     * The cached value of the '{@link #getInstalledAssistants()
     * <em>Installed Assistants</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInstalledAssistants()
     * @generated
     * @ordered
     */
    protected EList<Assistant> installedAssistants;

    /**
     * The default value of the '{@link #getPasswordHash()
     * <em>Password Hash</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPasswordHash()
     * @generated
     * @ordered
     */
    protected static final String PASSWORD_HASH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPasswordHash()
     * <em>Password Hash</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPasswordHash()
     * @generated
     * @ordered
     */
    protected String passwordHash = PASSWORD_HASH_EDEFAULT;

    /**
     * <code>birthDateObject</code> holds a {@link Date} object representing the
     * {@link #birthDate}.
     * 
     * @generated NOT
     */
    private transient volatile Date birthDateObject;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    protected UserImpl() {
        super();
        this.lineOfVision = ContextFactory.eINSTANCE.createVector();
        this.position = ContextFactory.eINSTANCE.createVector();
        this.position.setCoordinates(-1, -1, -1);
        this.irExperience = new HashMap<Class<? extends InteractionResource>, String>();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.USER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<InteractionResource> getResources() {
        if(resources == null) {
            resources = new EObjectWithInverseResolvingEList<InteractionResource>(InteractionResource.class, this, ContextPackage.USER__RESOURCES, ContextPackage.INTERACTION_RESOURCE__USER);
        }
        return resources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Vector getLineOfVision() {
        return lineOfVision;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetLineOfVision(Vector newLineOfVision, NotificationChain msgs) {
        Vector oldLineOfVision = lineOfVision;
        lineOfVision = newLineOfVision;
        if(eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ContextPackage.USER__LINE_OF_VISION, oldLineOfVision, newLineOfVision);
            if(msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLineOfVision(Vector newLineOfVision) {
        if(newLineOfVision != lineOfVision) {
            NotificationChain msgs = null;
            if(lineOfVision != null)
                msgs = ((InternalEObject)lineOfVision).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ContextPackage.USER__LINE_OF_VISION, null, msgs);
            if(newLineOfVision != null)
                msgs = ((InternalEObject)newLineOfVision).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ContextPackage.USER__LINE_OF_VISION, null, msgs);
            msgs = basicSetLineOfVision(newLineOfVision, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__LINE_OF_VISION, newLineOfVision, newLineOfVision));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Map<Class<? extends InteractionResource>, String> getIrExperience() {
        return irExperience;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIrExperience(Map<Class<? extends InteractionResource>, String> newIrExperience) {
        Map<Class<? extends InteractionResource>, String> oldIrExperience = irExperience;
        irExperience = newIrExperience;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__IR_EXPERIENCE, oldIrExperience, irExperience));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isFollowMe() {
        return followMe;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated
     */
    @Override
    public void setFollowMe(boolean newFollowMe) {
        boolean oldFollowMe = followMe;
        followMe = newFollowMe;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__FOLLOW_ME, oldFollowMe, followMe));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Environment getEnvironment() {
        if(eContainerFeatureID() != ContextPackage.USER__ENVIRONMENT)
            return null;
        return (Environment)eContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<InteractionResource> getUsableResources() {
        final EList<InteractionResource> usableResources = new BasicEList<InteractionResource>();
        for(final InteractionResource resource : getPlace().getInteractionResources()) {
            if(resource.getContextRating(this) > InteractionResource.MINIMUM_CONTEXT_RATING) {
                usableResources.add(resource);
            }
        }
        return usableResources;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void addCurrentAssistant(final Assistant assistant) {
        if(assistant == null) {
            throw new IllegalArgumentException("assistant argument must not be null in method addCurrentAssistant");
        }
        synchronized(getCurrentAssistants()) {
            if(getCurrentAssistants().contains(assistant)) {
                // nothing to do
                return;
            }
            // add assistant to current
            getCurrentAssistants().add(assistant);
            // update past list
            synchronized(getPastAssistants()) {
                // remove from past list
                getPastAssistants().remove(assistant);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void removeCurrentAssistant(final Assistant assistant) {
        if(assistant == null) {
            throw new IllegalArgumentException("assistant argument must not be null in method removeCurrentAssistant");
        }
        synchronized(getCurrentAssistants()) {
            if(!getCurrentAssistants().contains(assistant)) {
                // nothing to do
                return;
            }
            // remove from current
            getCurrentAssistants().remove(assistant);
            // update past list
            synchronized(getPastAssistants()) {
                if(!getPastAssistants().contains(assistant)) {
                    // add to past list
                    getPastAssistants().add(0, assistant);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized void setNewBirthDate(final String newBirthDate) {
        if(newBirthDate == null) {
            throw new IllegalArgumentException("newBirthDate argument must not be null in method setNewBirthDate");
        }
        // get formatter for dates
        final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);

        // attempt to parse the date string
        try {

            this.birthDateObject = format.parse(newBirthDate);
        }
        catch(final ParseException e) {
            LOG.warn(newBirthDate + " is not a valid date string", e);
            throw new IllegalArgumentException(newBirthDate + " is not a valid date string", e);
        }
        LOG.debug("Setting new birthdate: " + newBirthDate);
        // store date string
        setBirthDate(newBirthDate);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized Date getBirthDateObject() {
        if(this.birthDateObject == null) {
            final String currentBirthDate = getBirthDate();
            if(currentBirthDate == null) {
                return null;
            }

            // get formatter for dates
            final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);

            // attempt to parse the date string
            try {
                this.birthDateObject = format.parse(currentBirthDate);
            }
            catch(final ParseException e) {
                LOG.warn(currentBirthDate + " is not a valid date string", e);
                return null;
            }
        }
        return this.birthDateObject;

    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final synchronized boolean isAdult() {
        final Date date = getBirthDateObject();
        if(date == null) {
            return false;
        }
        final Calendar birthday = Calendar.getInstance();
        birthday.setTime(date);
        final Calendar today = Calendar.getInstance();
        if(today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) > 18) {
            return true;
        }
        if(today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) < 18) {
            return false;
        }
        if(today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH) > 0) {
            return true;
        }
        if(today.get(Calendar.MONTH) - birthday.get(Calendar.MONTH) < 0) {
            return false;
        }
        if(today.get(Calendar.DAY_OF_MONTH) - birthday.get(Calendar.DAY_OF_MONTH) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setLeftHanded() {
        if(!isLeftHanded()) {
            setLeftHanded(true);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setRightHanded() {
        if(isLeftHanded()) {
            setLeftHanded(false);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void installAssistant(final String assistantId) {
        final Assistant assistant = getAssistant(assistantId);
        synchronized(getInstalledAssistants()) {
            if(!getInstalledAssistants().contains(assistant)) {
                getInstalledAssistants().add(assistant);
                LOG.debug("Assistant " + assistantId + " installed for user " + getId());
            } else {
                LOG.warn("Got install request for an already installed assistant " + assistantId + " for user " + getId());
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void uninstallAssistant(final String assistantId) {
        final Assistant assistant = getAssistant(assistantId);
        synchronized(getInstalledAssistants()) {
            if(getInstalledAssistants().contains(assistant)) {
                getInstalledAssistants().remove(assistant);
                LOG.debug("Assistant " + assistantId + " uninstalled for user " + getId());
            } else {
                LOG.warn("Got uninstall request but assistant " + assistantId + " was not installed for user " + getId());
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public void setNewPasswordHash(final String passwordHash) {
        if(passwordHash == null) {
            throw new IllegalArgumentException("passwordHash argument must not be null in method setNewPassword");
        }

        this.passwordHash = passwordHash;
    }

    /**
     * <code>getAssistant</code> returns an {@link Assistant} for an identifier.
     * 
     * @param assistantId
     * @return Assistant - assistant with <code>assistantId</code>, never
     *         <code>null</code>
     * @throws IllegalArgumentException
     *             if <code>assistantId</code> is <code>null</code> or no
     *             assistant with such id exists
     * @throws IllegalStateException
     *             if {@link #getEnvironment()} returns <code>null</code>
     * @generated NOT
     */
    private final Assistant getAssistant(final String assistantId) {
        if(assistantId == null) {
            throw new IllegalArgumentException("assistantId argument must not be null");
        }
        final Environment environment = getEnvironment();
        if(environment == null) {
            throw new IllegalStateException("Environment is not set for user " + this);
        }
        synchronized(environment.getAssistants()) {
            for(final Assistant assistant : environment.getAssistants()) {
                if(assistantId.equals(assistant.getId())) {
                    return assistant;
                }
            }
        }
        throw new IllegalArgumentException("No assistant exists with id: " + assistantId);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetEnvironment(Environment newEnvironment, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEnvironment, ContextPackage.USER__ENVIRONMENT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEnvironment(Environment newEnvironment) {
        if(newEnvironment != eInternalContainer() || (eContainerFeatureID() != ContextPackage.USER__ENVIRONMENT && newEnvironment != null)) {
            if(EcoreUtil.isAncestor(this, newEnvironment))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if(eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if(newEnvironment != null)
                msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, ContextPackage.ENVIRONMENT__USERS, Environment.class, msgs);
            msgs = basicSetEnvironment(newEnvironment, msgs);
            if(msgs != null)
                msgs.dispatch();
        } else if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__ENVIRONMENT, newEnvironment, newEnvironment));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Assistant> getCurrentAssistants() {
        if(currentAssistants == null) {
            currentAssistants = new EObjectWithInverseResolvingEList.ManyInverse<Assistant>(Assistant.class, this, ContextPackage.USER__CURRENT_ASSISTANTS, ContextPackage.ASSISTANT__CURRENT_USERS);
        }
        return currentAssistants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Assistant> getPastAssistants() {
        if(pastAssistants == null) {
            pastAssistants = new EObjectWithInverseResolvingEList.ManyInverse<Assistant>(Assistant.class, this, ContextPackage.USER__PAST_ASSISTANTS, ContextPackage.ASSISTANT__PAST_USERS);
        }
        return pastAssistants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Assistant> getInstalledAssistants() {
        if(installedAssistants == null) {
            installedAssistants = new EObjectResolvingEList<Assistant>(Assistant.class, this, ContextPackage.USER__INSTALLED_ASSISTANTS);
        }
        return installedAssistants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPasswordHash(String newPasswordHash) {
        String oldPasswordHash = passwordHash;
        passwordHash = newPasswordHash;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__PASSWORD_HASH, oldPasswordHash, passwordHash));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isLeftHanded() {
        return leftHanded;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLeftHanded(boolean newLeftHanded) {
        boolean oldLeftHanded = leftHanded;
        leftHanded = newLeftHanded;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__LEFT_HANDED, oldLeftHanded, leftHanded));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBirthDate(String newBirthDate) {
        String oldBirthDate = birthDate;
        birthDate = newBirthDate;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__BIRTH_DATE, oldBirthDate, birthDate));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSurname() {
        return surname;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSurname(String newSurname) {
        String oldSurname = surname;
        surname = newSurname;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.USER__SURNAME, oldSurname, surname));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.USER__RESOURCES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
            case ContextPackage.USER__ENVIRONMENT:
                if(eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetEnvironment((Environment)otherEnd, msgs);
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getCurrentAssistants()).basicAdd(otherEnd, msgs);
            case ContextPackage.USER__PAST_ASSISTANTS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getPastAssistants()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch(featureID) {
            case ContextPackage.USER__RESOURCES:
                return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
            case ContextPackage.USER__LINE_OF_VISION:
                return basicSetLineOfVision(null, msgs);
            case ContextPackage.USER__ENVIRONMENT:
                return basicSetEnvironment(null, msgs);
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                return ((InternalEList<?>)getCurrentAssistants()).basicRemove(otherEnd, msgs);
            case ContextPackage.USER__PAST_ASSISTANTS:
                return ((InternalEList<?>)getPastAssistants()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch(eContainerFeatureID()) {
            case ContextPackage.USER__ENVIRONMENT:
                return eInternalContainer().eInverseRemove(this, ContextPackage.ENVIRONMENT__USERS, Environment.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.USER__BIRTH_DATE:
                return getBirthDate();
            case ContextPackage.USER__SURNAME:
                return getSurname();
            case ContextPackage.USER__LEFT_HANDED:
                return isLeftHanded();
            case ContextPackage.USER__RESOURCES:
                return getResources();
            case ContextPackage.USER__LINE_OF_VISION:
                return getLineOfVision();
            case ContextPackage.USER__IR_EXPERIENCE:
                return getIrExperience();
            case ContextPackage.USER__FOLLOW_ME:
                return isFollowMe();
            case ContextPackage.USER__ENVIRONMENT:
                return getEnvironment();
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                return getCurrentAssistants();
            case ContextPackage.USER__PAST_ASSISTANTS:
                return getPastAssistants();
            case ContextPackage.USER__INSTALLED_ASSISTANTS:
                return getInstalledAssistants();
            case ContextPackage.USER__PASSWORD_HASH:
                return getPasswordHash();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.USER__BIRTH_DATE:
                setBirthDate((String)newValue);
                return;
            case ContextPackage.USER__SURNAME:
                setSurname((String)newValue);
                return;
            case ContextPackage.USER__LEFT_HANDED:
                setLeftHanded((Boolean)newValue);
                return;
            case ContextPackage.USER__RESOURCES:
                getResources().clear();
                getResources().addAll((Collection<? extends InteractionResource>)newValue);
                return;
            case ContextPackage.USER__LINE_OF_VISION:
                setLineOfVision((Vector)newValue);
                return;
            case ContextPackage.USER__IR_EXPERIENCE:
                setIrExperience((Map<Class<? extends InteractionResource>, String>)newValue);
                return;
            case ContextPackage.USER__FOLLOW_ME:
                setFollowMe((Boolean)newValue);
                return;
            case ContextPackage.USER__ENVIRONMENT:
                setEnvironment((Environment)newValue);
                return;
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                getCurrentAssistants().clear();
                getCurrentAssistants().addAll((Collection<? extends Assistant>)newValue);
                return;
            case ContextPackage.USER__PAST_ASSISTANTS:
                getPastAssistants().clear();
                getPastAssistants().addAll((Collection<? extends Assistant>)newValue);
                return;
            case ContextPackage.USER__INSTALLED_ASSISTANTS:
                getInstalledAssistants().clear();
                getInstalledAssistants().addAll((Collection<? extends Assistant>)newValue);
                return;
            case ContextPackage.USER__PASSWORD_HASH:
                setPasswordHash((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch(featureID) {
            case ContextPackage.USER__BIRTH_DATE:
                setBirthDate(BIRTH_DATE_EDEFAULT);
                return;
            case ContextPackage.USER__SURNAME:
                setSurname(SURNAME_EDEFAULT);
                return;
            case ContextPackage.USER__LEFT_HANDED:
                setLeftHanded(LEFT_HANDED_EDEFAULT);
                return;
            case ContextPackage.USER__RESOURCES:
                getResources().clear();
                return;
            case ContextPackage.USER__LINE_OF_VISION:
                setLineOfVision((Vector)null);
                return;
            case ContextPackage.USER__IR_EXPERIENCE:
                setIrExperience((Map<Class<? extends InteractionResource>, String>)null);
                return;
            case ContextPackage.USER__FOLLOW_ME:
                setFollowMe(FOLLOW_ME_EDEFAULT);
                return;
            case ContextPackage.USER__ENVIRONMENT:
                setEnvironment((Environment)null);
                return;
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                getCurrentAssistants().clear();
                return;
            case ContextPackage.USER__PAST_ASSISTANTS:
                getPastAssistants().clear();
                return;
            case ContextPackage.USER__INSTALLED_ASSISTANTS:
                getInstalledAssistants().clear();
                return;
            case ContextPackage.USER__PASSWORD_HASH:
                setPasswordHash(PASSWORD_HASH_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch(featureID) {
            case ContextPackage.USER__BIRTH_DATE:
                return BIRTH_DATE_EDEFAULT == null ? birthDate != null
                        : !BIRTH_DATE_EDEFAULT.equals(birthDate);
            case ContextPackage.USER__SURNAME:
                return SURNAME_EDEFAULT == null ? surname != null
                        : !SURNAME_EDEFAULT.equals(surname);
            case ContextPackage.USER__LEFT_HANDED:
                return leftHanded != LEFT_HANDED_EDEFAULT;
            case ContextPackage.USER__RESOURCES:
                return resources != null && !resources.isEmpty();
            case ContextPackage.USER__LINE_OF_VISION:
                return lineOfVision != null;
            case ContextPackage.USER__IR_EXPERIENCE:
                return irExperience != null;
            case ContextPackage.USER__FOLLOW_ME:
                return followMe != FOLLOW_ME_EDEFAULT;
            case ContextPackage.USER__ENVIRONMENT:
                return getEnvironment() != null;
            case ContextPackage.USER__CURRENT_ASSISTANTS:
                return currentAssistants != null && !currentAssistants.isEmpty();
            case ContextPackage.USER__PAST_ASSISTANTS:
                return pastAssistants != null && !pastAssistants.isEmpty();
            case ContextPackage.USER__INSTALLED_ASSISTANTS:
                return installedAssistants != null && !installedAssistants.isEmpty();
            case ContextPackage.USER__PASSWORD_HASH:
                return PASSWORD_HASH_EDEFAULT == null ? passwordHash != null
                        : !PASSWORD_HASH_EDEFAULT.equals(passwordHash);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if(eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (birthDate: ");
        result.append(birthDate);
        result.append(", surname: ");
        result.append(surname);
        result.append(", leftHanded: ");
        result.append(leftHanded);
        result.append(", irExperience: ");
        result.append(irExperience);
        result.append(", followMe: ");
        result.append(followMe);
        result.append(", passwordHash: ");
        result.append(passwordHash);
        result.append(')');
        return result.toString();
    }

} // UserImpl
