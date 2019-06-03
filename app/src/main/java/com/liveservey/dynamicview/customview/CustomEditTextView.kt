package com.liveservey.dynamicview.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText

class CustomEditTextView : EditText{

    constructor(context: Context) : super(context){
        val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet){
        val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    constructor(context: Context,attributeSet: AttributeSet,defineStyle: Int) : super(context,attributeSet,defineStyle){
        val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}