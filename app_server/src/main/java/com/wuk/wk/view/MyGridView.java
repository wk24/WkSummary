package com.wuk.wk.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;

import com.wuk.wk.R;
import com.wuk.wk.utils.ScreenUtils;

/**
 * @author wuk
 * @date 2021/9/7
 */

public class MyGridView extends GridView {
    private Bitmap background;

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        background = BitmapFactory.decodeResource(getResources(),
//                R.drawable.bookshelf_layer_center);
//        Bitmap bitmap = Bitmap.createBitmap();
        int width = ScreenUtils.getScreenWidth(context) / 2;
        Log.e("TAG", "MyGridView: "+width);
        background = Bitmap.createBitmap(width,100,Bitmap.Config.ARGB_8888);
        background.eraseColor(Color.parseColor("#ffff55"));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = getChildCount();
        int top = count > 0 ? getChildAt(0).getTop() : 0;
        int backgroundWidth = background.getWidth();
        int backgroundHeight = (background.getHeight()+2)*2;
        int width = getWidth();
        int height = getHeight();
        for (int y = top; y < height; y += backgroundHeight) {
            for (int x = 0; x < width; x += backgroundWidth) {
                canvas.drawBitmap(background, 100, 100, null);
            }
        }
        super.dispatchDraw(canvas);
    }
}

