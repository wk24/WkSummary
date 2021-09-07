package com.wuk.wk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ImageView imageView = findViewById(R.id.iv_view);
        TextView tv = findViewById(R.id.tv);
        RelativeLayout rl = findViewById(R.id.rl);

        tv.setText("adsfdsagasgdsagasdgagagdadsfdsagasgdsagasdgagagdadsfdsagasgdsagasdgagagdadsfdsagasgdsagasdgagagdadsfdsagasgdsagasdgagagd");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: "+111111);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        tv.post(new Runnable() {
            @Override
            public void run() {
                tv.getHeight();
            }
        });

        //        rl.requestLayout();
//        rl.invalidate();

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//
//        Bitmap bitmap1 = drawTextAtBitmap("100分");
//        imageView.setImageBitmap(bitmap1);
    }




    /**
     * 图片上画文字
     * @param text  文字内容
     * @return Bitmap
     */
    private Bitmap drawTextAtBitmap(String text) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        int x = bitmap.getWidth();
        int y = bitmap.getHeight();

        // 创建一个和原图同样大小的位图
        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newbit);

        Paint mPaint = new Paint();

        // 在原始位置0，0插入原图
        canvas.drawBitmap(bitmap, 0, 0, mPaint);

        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        mPaint.setTextSize(80);
        mPaint.setColor(getResources().getColor(R.color.black));
        //将文字用一个矩形包裹，进而算出文字的长和宽
        Rect bounds = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        //计算长宽
        int x1 = x / 2 - bounds.width() / 2;
        int y1 = (y - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text, x1, y1, mPaint);
        canvas.save();
        // 存储
        canvas.restore();
        return newbit;
    }
}