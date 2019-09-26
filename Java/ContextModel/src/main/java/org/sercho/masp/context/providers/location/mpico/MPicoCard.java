package org.sercho.masp.context.providers.location.mpico;

/**
 * Simple datatype to store changes.
 * 
 * @author Sebastian Garn
 * 
 */
public class MPicoCard {

    // card (marker-tag)
    boolean cardFirstTimeDetected = true;

    String lastActiveTime, currentActiveUser;

    // panic
    boolean panicFirstTimeDetected = true;

    String lastPanic = "";

    int panic = 0;

    // each time user pushes button we send true or false in a rotary way
    boolean interactionRequestedNextTime = false;

    // fingerprint
    int enrolment;

    int identification = 0;

    String lastIdentified = "UNKNOWN USER";

    /**
     * Called by PollThread if there is a <marker .../> tag containing the id of
     * this card
     * 
     * @param lastActiveTimeIn
     *            Value of parsed attribute lastActive
     * @param nameIn
     *            Value of parsed attribute name
     */
    void updateMarker(final String lastActiveTimeIn, final String nameIn) {
        boolean hasChanged = false;

        if(this.cardFirstTimeDetected) {
            this.lastActiveTime = lastActiveTimeIn;
            this.currentActiveUser = nameIn;
            this.cardFirstTimeDetected = false;
        }

        if(lastActiveTimeIn.compareTo(this.lastActiveTime) != 0) {
            this.lastActiveTime = lastActiveTimeIn;
            hasChanged = true;
        }

        if(nameIn.compareTo(this.currentActiveUser) != 0) {
            this.currentActiveUser = nameIn;
            hasChanged = true;
        }

        if(hasChanged) {
            // nothing to do
        }
    }

    /**
     * Called by PollThread if there is a <panic .../> tag containing the id of
     * this card
     * 
     * @param lastPanicIn
     *            Value of parsed attribute lastPanic
     * @param panicIn
     *            Value of parsed attribute panic
     */
    boolean updatePanic(final String lastPanicIn, final int panicIn) {
        boolean hasChanged = false;

        if(this.panicFirstTimeDetected) {
            this.panic = panicIn;
            this.lastPanic = lastPanicIn;
            this.panicFirstTimeDetected = false;
        }

        if(lastPanicIn.compareTo(this.lastPanic) != 0) {
            this.lastPanic = lastPanicIn;
            hasChanged = true;
        }

        if(panicIn != this.panic) {
            this.panic = panicIn;
            hasChanged = true;
        }

        return hasChanged;
    }

    /**
     * Called by PollThread if there is a <fingerprint .../> tag containing the
     * id of this card
     * 
     * @param enrolmentIn
     * @param identificationIn
     */
    boolean updateFingerprint(final int enrolmentIn, final int identificationIn) {

        // if it has changed
        if(identificationIn != this.identification) {
            if((this.identification == 0 || (this.identification == 2)) && identificationIn == 1) {
                this.identification = identificationIn;
                this.lastIdentified = this.currentActiveUser;
                return true;
            } else {
                this.identification = identificationIn;
                return false;
            }
        }
        // if identification value did not change but the user (so the was no
        // logout between two events)
        else if(identificationIn == this.identification && this.currentActiveUser.compareTo(this.lastIdentified) != 0 && this.currentActiveUser.compareTo("UNKNOWN USER") != 0) {
            this.identification = identificationIn;
            this.lastIdentified = this.currentActiveUser;
            return true;
        }

        return false;
    }

    boolean getAndSwitchRequestType() {
        this.interactionRequestedNextTime = !this.interactionRequestedNextTime;
        return this.interactionRequestedNextTime;
    }
}
