package com.liveservey.dynamicview.abstractsclass;

import android.support.annotation.NonNull;


public abstract class AbstractClassForTypeText extends BaseAbstractClass {

    @NonNull
    public abstract String getHintText();

    @NonNull
    public abstract String getAlertText();

    @NonNull
    public abstract String getMaxLength();
}
