package com.ibrahim.focustimer.util;

public class ToggleSelector<T> {

    private T binaryChoice0;
    private T binaryChoice1;

    private boolean isBinaryChoice0Selected;

    public ToggleSelector(T binaryChoice0, T binaryChoice1) {
        this(binaryChoice0, binaryChoice1, true);
    }

    public ToggleSelector(T binaryChoice0, T binaryChoice1, boolean isBinaryChoice0Selected) {
        this.binaryChoice0 = binaryChoice0;
        this.binaryChoice1 = binaryChoice1;
        this.isBinaryChoice0Selected = isBinaryChoice0Selected;
    }

    public T toggle() {
        if (isBinaryChoice0Selected) {
            isBinaryChoice0Selected = false;
            return binaryChoice0;
        } else {
            isBinaryChoice0Selected = true;
            return binaryChoice1;
        }
    }

    public T getCurrentState() {
        return isBinaryChoice0Selected ? binaryChoice0 : binaryChoice1;
    }

    public T setToBinayChoice0() {
        isBinaryChoice0Selected = true;
        return binaryChoice0;
    }

    public T setToBinayChoice1() {
        isBinaryChoice0Selected = true;
        return binaryChoice1;
    }

}
