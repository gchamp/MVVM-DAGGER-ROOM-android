package com.liveservey.dynamicview.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.RadioButton

class CustomBoldRadioButton : RadioButton{
    constructor(context: Context) : super(context){
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Bold.otf")
        this.typeface = typeFaceData
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet){
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Bold.otf")
        this.typeface = typeFaceData
    }

    constructor(context: Context, attributeSet: AttributeSet, defineStyle : Int) : super(context,attributeSet,defineStyle){
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Bold.otf")
        this.typeface = typeFaceData
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}