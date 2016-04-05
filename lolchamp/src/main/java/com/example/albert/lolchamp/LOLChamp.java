package com.example.albert.lolchamp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class LOLChamp extends LinearLayout {

    private ImageView imgChamp;

    public LOLChamp(Context context) {
        super(context);
        init(null, 0);
    }

    public LOLChamp(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LOLChamp(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lolchamp,this);

        imgChamp = (ImageView)findViewById(R.id.champSelect);

        //Load attributes
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LOLChamp, defStyle, 0);

        Drawable s = a.getDrawable(R.styleable.LOLChamp_imgChamp);
        if (s != null) { setImg(s);}

        a.recycle();

    }

    public void setImg(Drawable champ){
        imgChamp.setImageDrawable(champ);
        invalidate();
        requestLayout();
    }


}
