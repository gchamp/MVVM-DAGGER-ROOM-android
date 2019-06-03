package com.liveservey.dynamicview.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class CustomTextView : TextView{

    constructor (context: Context) : super(context) {
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typeFaceData
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet){
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typeFaceData
    }

    constructor(context: Context,attributeSet: AttributeSet,defStyle: Int) : super(context,attributeSet,defStyle){
        val typeFaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typeFaceData
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}