/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sercho.masp.models.Context.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.SingletonAdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sercho.masp.models.Disposeable;
import org.sercho.masp.models.DisposeableEContentAdapter;
import org.sercho.masp.models.DisposeableProxy;
import org.sercho.masp.models.ListAdapterUtility;
import org.sercho.masp.models.ListAdapterUtility.ListObserver;
import org.sercho.masp.models.LookAndFeelUtility;
import org.sercho.masp.models.TwoDimensionalUtility;
import org.sercho.masp.models.Context.ContextPackage;
import org.sercho.masp.models.Context.GraphicalOutputChannel;
import org.sercho.masp.models.UI.Button;
import org.sercho.masp.models.UI.GraphicalOutput;
import org.sercho.masp.models.UI.Image;
import org.sercho.masp.models.UI.Interactor;
import org.sercho.masp.models.UI.InteractorState;
import org.sercho.masp.models.UI.LookAndFeel;
import org.sercho.masp.models.UI.TextLabel;
import org.sercho.masp.models.UI.TwoDimensional;
import org.sercho.masp.models.UI.UIModel;
import org.sercho.masp.models.UI.UIPackage;
import org.sercho.masp.models.channel.api.GraphicalOutputChannelAPI;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Graphical Output Channel</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl#getHeight
 * <em>Height</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl#getX
 * <em>X</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl#getY
 * <em>Y</em>}</li>
 * <li>
 * {@link org.sercho.masp.models.Context.impl.GraphicalOutputChannelImpl#getZ
 * <em>Z</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GraphicalOutputChannelImpl extends
        ChannelImpl<GraphicalOutput, GraphicalOutputChannelAPI> implements
        GraphicalOutputChannel {

    /**
     * <code>GraphicalOutputAdapter</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * 
     * @author Grzegorz Lehmann
     * @since 5.0.6
     * @generated NOT
     */
    final class GraphicalOutputAdapter extends SingletonAdapterImpl {

        private final transient Log LOG = LogFactory.getLog(GraphicalOutputAdapter.class);

        final GraphicalOutputChannelAPI channelAPI;

        private final TwoDimensional channel;

        /**
         * <code>GraphicalOutputAdapter</code> constructor.
         * 
         * @param twoDimensional
         *            2D output channel for which this adapter is created
         * @param api
         *            channel API to use
         */
        public GraphicalOutputAdapter(final TwoDimensional twoDimensional,
                final GraphicalOutputChannelAPI api) {
            if(twoDimensional == null) {
                throw new IllegalArgumentException("twoDimensional is null");
            }
            if(api == null) {
                throw new IllegalArgumentException("api is null");
            }
            this.channel = twoDimensional;
            this.channelAPI = api;
        }

        void newInteractorState(final GraphicalOutput interactor, final Object oldState, final Object newState) {
            // get Look&Feel
            LookAndFeel lookAndFeel = interactor.getLookAndFeel();
            if(lookAndFeel == null) {
                lookAndFeel = LookAndFeelUtility.loadDefaultLookAndFeel();
            }
            // activated?
            if(oldState == null || oldState == InteractorState.INACTIVE) {
                if(newState != InteractorState.INACTIVE) {
                    // activated, add to API
                    set(lookAndFeel);
                    if(interactor instanceof Button) {
                        getApi().addButton(interactor.getId(), getLookAndFeelId(lookAndFeel, interactor.getState()), interactor.getX() - getX(), interactor.getY() - getY(), interactor.getZ(), interactor.getWidth(), interactor.getHeight(), ((Button)interactor).getText());
                    } else if(interactor instanceof TextLabel) {
                        getApi().addTextLabel(interactor.getId(), getLookAndFeelId(lookAndFeel, interactor.getState()), interactor.getX() - getX(), interactor.getY() - getY(), interactor.getZ(), interactor.getWidth(), interactor.getHeight(), ((TextLabel)interactor).getText());
                    } else if(interactor instanceof Image) {
                        getApi().addImage(interactor.getId(), getLookAndFeelId(lookAndFeel, interactor.getState()), ((Image)interactor).getUrl(), interactor.getX() - getX(), interactor.getY() - getY(), interactor.getZ(), interactor.getWidth(), interactor.getHeight());
                    } else {
                        LogFactory.getLog(getClass()).error("Unknown element: " + interactor);
                        return;
                    }
                }
            } else {
                if(newState == InteractorState.INACTIVE) {
                    // deactivated
                    getApi().remove(interactor.getId());
                    return;
                }
            }
            final String lookAndFeelID = getLookAndFeelId(lookAndFeel, interactor.getState());
            if(lookAndFeelID == null) {
                this.LOG.warn("No LookAndFeel set in element " + interactor);
                return;
            }
            this.channelAPI.setLookAndFeelOfElement(interactor.getId(), lookAndFeelID);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(final Notification msg) {
            final Object feature = msg.getFeature();
            if(feature == UIPackage.eINSTANCE.getInteractor_State()) {
                newInteractorState((GraphicalOutput)msg.getNotifier(), msg.getOldValue(), msg.getNewValue());
            } else if(feature == UIPackage.eINSTANCE.getUIModel_PointingX()) {
                this.channelAPI.newPointingX(msg.getNewIntValue() - this.channel.getX());
            } else if(feature == UIPackage.eINSTANCE.getUIModel_PointingY()) {
                this.channelAPI.newPointingY(msg.getNewIntValue() - this.channel.getY());
            } else if(feature == UIPackage.eINSTANCE.getTwoDimensional_X()) {
                this.channelAPI.newPositionX(getElementID(msg), msg.getNewIntValue() - this.channel.getX());
            } else if(feature == UIPackage.eINSTANCE.getTwoDimensional_Y()) {
                this.channelAPI.newPositionY(getElementID(msg), msg.getNewIntValue() - this.channel.getY());
            } else if(feature == UIPackage.eINSTANCE.getTwoDimensional_Z()) {
                this.channelAPI.newPositionZ(getElementID(msg), msg.getNewIntValue());
            } else if(feature == UIPackage.eINSTANCE.getTwoDimensional_Width()) {
                this.channelAPI.newWidth(getElementID(msg), msg.getNewIntValue());
            } else if(feature == UIPackage.eINSTANCE.getTwoDimensional_Height()) {
                this.channelAPI.newHeight(getElementID(msg), msg.getNewIntValue());
            } else if(feature == UIPackage.Literals.IMAGE__URL) {
                this.channelAPI.newURL(getElementID(msg), msg.getNewStringValue());
            } else if(feature == UIPackage.eINSTANCE.getButton_Text()) {
                this.channelAPI.newText(getElementID(msg), msg.getNewStringValue());
            } else if(feature == UIPackage.eINSTANCE.getTextLabel_Text()) {
                this.channelAPI.newText(getElementID(msg), msg.getNewStringValue());
            } else if(feature == UIPackage.eINSTANCE.getGraphicalOutput_LookAndFeel()) {
                LookAndFeel newLookAndFeel = (LookAndFeel)msg.getNewValue();
                if(newLookAndFeel == null) {
                    newLookAndFeel = LookAndFeelUtility.loadDefaultLookAndFeel();
                }
                final GraphicalOutput output = (GraphicalOutput)msg.getNotifier();
                final InteractorState state = output.getState();
                if(state == InteractorState.INACTIVE) {
                    return;
                }
                this.channelAPI.setLookAndFeelOfElement(output.getId(), getLookAndFeelId(newLookAndFeel, state));
            }
        }

        private String getElementID(final Notification msg) {
            return ((Interactor)msg.getNotifier()).getId();
        }
    }

    /**
     * <code>POSTFIX_LF_ACTIVE</code>
     * 
     * @generated NOT
     */
    private static final String POSTFIX_LF_ACTIVE = "_ACTIVE";

    /**
     * <code>POSTFIX_LF_FOCUSED</code>
     * 
     * @generated NOT
     */
    private static final String POSTFIX_LF_FOCUSED = "_FOCUSED";

    /**
     * <code>POSTFIX_LF_SELECTED</code>
     * 
     * @generated NOT
     */
    private static final String POSTFIX_LF_SELECTED = "_SELECTED";

    /**
     * <code>LookAndFeelAdapter</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * 
     * @author Grzegorz Lehmann
     * @since TODO
     * @generated NOT
     */
    private final class LookAndFeelAdapter extends DisposeableEContentAdapter {

        private final LookAndFeel lf;

        /**
         * <code>LookAndFeelAdapter</code> constructor.
         * 
         * @param lookAndFeel
         *            observed L&F
         */
        public LookAndFeelAdapter(final LookAndFeel lookAndFeel) {
            if(lookAndFeel == null) {
                throw new IllegalArgumentException("lookAndFeel is null");
            }
            this.lf = lookAndFeel;
            this.lf.eAdapters().add(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyChanged(final Notification notification) {
            // avoid attaching to children, which is handled in lfObserver
            if(notification.getFeature() != UIPackage.Literals.LOOK_AND_FEEL__CHILDREN) {
                super.notifyChanged(notification);
                // update this look and feel
                set(this.lf);
            }
        }
    }

    /**
     * <code>lfObserver</code>
     * 
     * @generated NOT
     */
    private final ListObserver<LookAndFeel> lfObserver = new ListObserver<LookAndFeel>() {

        private final Map<LookAndFeel, Disposeable> disposeables = new Hashtable<LookAndFeel, Disposeable>();

        /**
         * {@inheritDoc}
         */
        @Override
        public void added(final LookAndFeel lf) {
            final DisposeableProxy disposeable = new DisposeableProxy(new LookAndFeelAdapter(lf), ListAdapterUtility.observe(lf, UIPackage.Literals.LOOK_AND_FEEL__CHILDREN, LookAndFeel.class, this));
            this.disposeables.put(lf, disposeable);
            set(lf);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void disposed() {
            // nothing to do here
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void removed(final LookAndFeel lf) {
            final Disposeable disposeable = this.disposeables.remove(lf);
            if(disposeable != null) {
                disposeable.dispose();
            }
        }
    };

    /**
     * <code>set</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * @param lf
     * @generated NOT
     */
    void set(final LookAndFeel lf) {
        // add look and feel for each 3 states
        getApi().setLookAndFeel(getLookAndFeelId(lf, InteractorState.ACTIVE), LookAndFeelUtility.getBackgroundColor(lf, InteractorState.ACTIVE).getRGBHex(), LookAndFeelUtility.getFontColor(lf, InteractorState.ACTIVE).getRGBHex(), LookAndFeelUtility.getFontName(lf, InteractorState.ACTIVE), LookAndFeelUtility.getFontSize(lf, InteractorState.ACTIVE), LookAndFeelUtility.getFontStyle(lf, InteractorState.ACTIVE).getLiteral(), (LookAndFeelUtility.hasBorder(lf, InteractorState.ACTIVE) ? LookAndFeelUtility.getBorderColor(lf, InteractorState.ACTIVE).getRGBHex()
                : null), LookAndFeelUtility.getBorderWidth(lf, InteractorState.ACTIVE));
        getApi().setLookAndFeel(getLookAndFeelId(lf, InteractorState.FOCUSED), LookAndFeelUtility.getBackgroundColor(lf, InteractorState.FOCUSED).getRGBHex(), LookAndFeelUtility.getFontColor(lf, InteractorState.FOCUSED).getRGBHex(), LookAndFeelUtility.getFontName(lf, InteractorState.FOCUSED), LookAndFeelUtility.getFontSize(lf, InteractorState.FOCUSED), LookAndFeelUtility.getFontStyle(lf, InteractorState.FOCUSED).getLiteral(), (LookAndFeelUtility.hasBorder(lf, InteractorState.FOCUSED) ? LookAndFeelUtility.getBorderColor(lf, InteractorState.FOCUSED).getRGBHex()
                : null), LookAndFeelUtility.getBorderWidth(lf, InteractorState.FOCUSED));
        getApi().setLookAndFeel(getLookAndFeelId(lf, InteractorState.SELECTED), LookAndFeelUtility.getBackgroundColor(lf, InteractorState.SELECTED).getRGBHex(), LookAndFeelUtility.getFontColor(lf, InteractorState.SELECTED).getRGBHex(), LookAndFeelUtility.getFontName(lf, InteractorState.SELECTED), LookAndFeelUtility.getFontSize(lf, InteractorState.SELECTED), LookAndFeelUtility.getFontStyle(lf, InteractorState.SELECTED).getLiteral(), (LookAndFeelUtility.hasBorder(lf, InteractorState.SELECTED) ? LookAndFeelUtility.getBorderColor(lf, InteractorState.SELECTED).getRGBHex()
                : null), LookAndFeelUtility.getBorderWidth(lf, InteractorState.SELECTED));
    }

    /**
     * <code>remove</code> TODO comment
     * 
     * @todo add missing JavaDoc
     * @param lf
     * @generated NOT
     */
    void remove(final LookAndFeel lf) {
        // remove all 3 L&Fs
        getApi().removeLookAndFeel(lf + POSTFIX_LF_ACTIVE);
        getApi().removeLookAndFeel(lf + POSTFIX_LF_FOCUSED);
        getApi().removeLookAndFeel(lf + POSTFIX_LF_SELECTED);
    }

    /**
     * <code>getLookAndFeelId</code> TODO comment
     * 
     * @param lookAndFeel
     * 
     * @todo add missing JavaDoc
     * @param interactorState
     * @return
     * @generated NOT
     */
    static String getLookAndFeelId(final LookAndFeel lookAndFeel, final InteractorState interactorState) {
        switch(interactorState) {
            case ACTIVE:
                return lookAndFeel.getName() + POSTFIX_LF_ACTIVE;
            case FOCUSED:
                return lookAndFeel.getName() + POSTFIX_LF_FOCUSED;
            case SELECTED:
                return lookAndFeel.getName() + POSTFIX_LF_SELECTED;
            default:
                return null;
        }
    }

    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getX()
     * @generated
     * @ordered
     */
    protected int x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getY()
     * @generated
     * @ordered
     */
    protected int y = Y_EDEFAULT;

    /**
     * The default value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @generated
     * @ordered
     */
    protected static final int Z_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getZ() <em>Z</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getZ()
     * @generated
     * @ordered
     */
    protected int z = Z_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GraphicalOutputChannelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContextPackage.Literals.GRAPHICAL_OUTPUT_CHANNEL;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void setPosition(final int newX, final int newY) {
        setX(newX);
        setY(newY);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    public void setPosition(final int newX, final int newY, final int newZ) {
        setX(newX);
        setY(newY);
        setZ(newZ);
    }

    /**
     * <code>adapter</code> handling the state of this channel's elements.
     * 
     * @generated NOT
     */
    private GraphicalOutputAdapter adapter;

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void setApi(final GraphicalOutputChannelAPI channelAPI) {
        this.api = channelAPI;
        if(this.api != null) {
            setAdapter(new GraphicalOutputAdapter(this, channelAPI));
            channelAPI.setCallback(this);
        }
    }

    /**
     * <code>setAdapter</code> sets a new adapter for all elements currently on
     * channel. If there was another adapter previously, it is removed.
     * 
     * @param newAdapter
     *            new state adapter to set
     * @generated NOT
     */
    private synchronized void setAdapter(final GraphicalOutputAdapter newAdapter) {
        UIModel model = null;
        if(this.adapter != null) {
            for(final GraphicalOutput element : getElements()) {
                element.eAdapters().remove(this.adapter);
                if(model == null) {
                    try {
                        model = (UIModel)EcoreUtil.getRootContainer(element);
                    }
                    catch(final ClassCastException e) {
                        System.out.println("GraphicalOutputChannelImpl.setAdapter(): " + e.getMessage());
                    }
                }
            }
            if(model != null) {
                model.eAdapters().remove(this.adapter);
            }
        }
        this.adapter = newAdapter;
        for(final GraphicalOutput element : getElements()) {
            element.eAdapters().add(this.adapter);
            if(model == null) {
                try {
                    model = (UIModel)EcoreUtil.getRootContainer(element);
                }
                catch(final ClassCastException e) {
                    System.out.println("GraphicalOutputChannelImpl.setAdapter(): " + e.getMessage());
                }
            }
        }
        if(model != null) {
            model.eAdapters().add(this.adapter);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void addHook(final GraphicalOutput element) {
        if(this.adapter != null) {
            final EObject uiModel = EcoreUtil.getRootContainer(element);
            if(uiModel instanceof UIModel && !uiModel.eAdapters().contains(this.adapter)) {
                uiModel.eAdapters().add(this.adapter);
                ListAdapterUtility.observe(uiModel, UIPackage.Literals.UI_MODEL__LOOK_AND_FEELS, LookAndFeel.class, this.lfObserver);
            }
            element.eAdapters().add(this.adapter);
            this.adapter.newInteractorState(element, null, element.getState());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    protected final void removeHook(final GraphicalOutput element) {
        getApi().remove(element.getId());
        if(this.adapter != null) {
            element.eAdapters().remove(this.adapter);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public void setSize(final int newWidth, final int newHeight) {
        setWidth(newWidth);
        setHeight(newHeight);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch(featureID) {
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH:
                return getWidth();
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT:
                return getHeight();
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X:
                return getX();
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y:
                return getY();
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z:
                return getZ();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch(featureID) {
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH:
                setWidth((Integer)newValue);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT:
                setHeight((Integer)newValue);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X:
                setX((Integer)newValue);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y:
                setY((Integer)newValue);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z:
                setZ((Integer)newValue);
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
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X:
                setX(X_EDEFAULT);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y:
                setY(Y_EDEFAULT);
                return;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z:
                setZ(Z_EDEFAULT);
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
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH:
                return width != WIDTH_EDEFAULT;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X:
                return x != X_EDEFAULT;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y:
                return y != Y_EDEFAULT;
            case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z:
                return z != Z_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if(baseClass == TwoDimensional.class) {
            switch(derivedFeatureID) {
                case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH:
                    return UIPackage.TWO_DIMENSIONAL__WIDTH;
                case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT:
                    return UIPackage.TWO_DIMENSIONAL__HEIGHT;
                case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X:
                    return UIPackage.TWO_DIMENSIONAL__X;
                case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y:
                    return UIPackage.TWO_DIMENSIONAL__Y;
                case ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z:
                    return UIPackage.TWO_DIMENSIONAL__Z;
                default:
                    return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if(baseClass == TwoDimensional.class) {
            switch(baseFeatureID) {
                case UIPackage.TWO_DIMENSIONAL__WIDTH:
                    return ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH;
                case UIPackage.TWO_DIMENSIONAL__HEIGHT:
                    return ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT;
                case UIPackage.TWO_DIMENSIONAL__X:
                    return ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X;
                case UIPackage.TWO_DIMENSIONAL__Y:
                    return ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y;
                case UIPackage.TWO_DIMENSIONAL__Z:
                    return ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z;
                default:
                    return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(", x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", z: ");
        result.append(z);
        result.append(')');
        return result.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void newPosition(final int relativeX, final int relativeY) {
        // set new channel position
        setPosition(relativeX, relativeY);

        // positions of elements are relative to channel, so when channel moves
        // they need to be moved back, so they correspond to global positions
        final List<GraphicalOutput> outputs = getElements();
        GraphicalOutput e;
        for(int i = 0; i < outputs.size(); i++) {
            e = outputs.get(i);
            getApi().newPositionX(e.getId(), e.getX() - getX());
            getApi().newPositionY(e.getId(), e.getY() - getY());
        }
        // TODO set relative to interaction resource
        // System.err.println("GraphicalOutputChannelImpl.newPosition() implement relative to interaction resource!");
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void newSize(final int newWidth, final int newHeight) {
        setSize(newWidth, newHeight);
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setHeight(final int newHeight) {
        // new height
        if(newHeight != this.height) {
            final int old = this.height;
            this.height = newHeight;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__HEIGHT, old, newHeight));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setWidth(final int newWidth) {
        // new width
        if(newWidth != this.width) {
            final int old = this.width;
            this.width = newWidth;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__WIDTH, old, newWidth));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setX(final int value) {
        // X coordinate
        if(value != this.x) {
            final int old = this.x;
            this.x = value;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__X, old, value));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final void setY(final int value) {
        // Y coordinate
        if(value != this.y) {
            final int old = this.y;
            this.y = value;
            if(eNotificationRequired()) {
                eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Y, old, value));
            }
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getZ() {
        return z;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setZ(int newZ) {
        int oldZ = z;
        z = newZ;
        if(eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ContextPackage.GRAPHICAL_OUTPUT_CHANNEL__Z, oldZ, z));
    }

    /**
     * {@inheritDoc}
     * 
     * @generated NOT
     */
    @Override
    public final boolean contains(final int xCoordinate, final int yCoordinate) {
        return TwoDimensionalUtility.contains(this, xCoordinate, yCoordinate);
    }
} // GraphicalOutputChannelImpl
