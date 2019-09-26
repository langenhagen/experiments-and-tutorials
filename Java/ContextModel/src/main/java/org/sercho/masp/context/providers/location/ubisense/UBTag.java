package org.sercho.masp.context.providers.location.ubisense;

import org.sercho.masp.context.providers.location.Vector;

public class UBTag {

    public UBTag(final String _tagID) {

        this.tagID = _tagID;

    }

    public String tagID;

    public Vector position;

    public Vector orientation;

    double confidence;

}
