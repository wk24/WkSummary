package com.wuk.wk.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.wuk.wk.R;

/**
 * @author wuk
 * @date 2021/9/7
 */
public class MyRecyclerView extends RecyclerView {

    private Bitmap background;
    Context context;

    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        background = null;
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int count = getChildCount();
        View childAt = getChildAt(0);
        int top = count > 0 ? childAt.getTop() : 0;
        int width = getWidth();
        int height = getHeight();

        int height1 = childAt.getHeight();
        Log.e("TAG", "dispatchDraw: "+height1);
        int totalCount = this.getChildCount();       //如果总数为0自然没必要计算高度并画图
        if (totalCount > 0) {						//获取列数
            int numColumns=3;			// 计算行高，通过行数和控件高度计算
            int rowCount = totalCount / numColumns;
            if (totalCount % numColumns != 0) {
                rowCount++;
            }
//            int rowHeight = height / rowCount;
//            int rowHeight = height1/2;
            int widthR = width - 200;
            int rowHeight = widthR/20;
            if (background == null) {
                background = BitmapFactory.decodeResource(getResources(),
                        R.drawable.bookshelf_layer_center);
                background = Bitmap.createScaledBitmap(background, widthR,
                        rowHeight, true);
            }
            int backgroundWidth = background.getWidth()-200;
            int backgroundHeight = background.getHeight()*2;
            for (int y = top; y < height; y += height1) {
//                for (int x = 0; x < width; x += backgroundWidth) {
//                    canvas.drawBitmap(background, x, y+backgroundHeight/2, null);
//                }
//                for (int x = 0; x < width; x += backgroundWidth) {
                canvas.drawBitmap(background, 100, y+(height1-rowHeight), null);
//                }
            }
        }
        super.dispatchDraw(canvas);
    }
}
