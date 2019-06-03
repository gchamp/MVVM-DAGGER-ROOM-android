package com.liveservey.dynamicview.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.CheckBox

class CustomCheckBoxView : CheckBox{

    constructor(context: Context) : super(context){
      val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    constructor(context: Context,attributeSet: AttributeSet) : super(context,attributeSet){
      val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    constructor(context: Context,attributeSet: AttributeSet,defineStype: Int) : super(context,attributeSet,defineStype){
      val typefaceData = Typeface.createFromAsset(context.assets,"PlayfairDisplay-Regular.otf")
        this.typeface = typefaceData
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }


}