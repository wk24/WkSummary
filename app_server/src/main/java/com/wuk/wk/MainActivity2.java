package com.wuk.wk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Recyclerview 的条目上添加遮盖层
 */

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    List<String> list = new ArrayList<>();
    private int currentPosition = 0;
    private RecyclerView.ItemDecoration itemDecoration;

    private boolean isNotify = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        addData();
        /*
        将itemDecoration添加到RecyclerView。
        请注意这里是add的,在底层源码里面可以看到ItemDecoration是可以被添加多个的.这里是一个RecyclerView持有ItemDecoration集合。
        能添加当然就可以移除,所以对应移除的方法
        mRecyclerView.removeItemDecoration();//根据目标移除
        mRecyclerView.removeItemDecorationAt();//根据索引index移除
         */
        itemDecoration = getItemDecoration();
        recyclerView.addItemDecoration(itemDecoration);


        mRecyclerViewAdapter.setItemClickListtener(new RecyclerViewAdapter.ItemClickListtener() {
            @Override
            public void onItemClick(int position) {

                String[] array = {"1","2"};
                String s = array[3];

                Log.e("TAG", "onItemClick: "+ s );

                if (currentPosition != position){
                    currentPosition = position;
                    isNotify = true;
//                    mRecyclerViewAdapter.notifyItemChanged(currentPosition);
                    mRecyclerViewAdapter.notifyDataSetChanged();
//                mRecyclerViewAdapter.notifyItemRangeChanged(currentPosition,list.size());
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isNotify = true;
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isNotify = true;
            }
        });



    }


    private RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view111, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                if (parent.getChildAdapterPosition(view) == 0){ //给第一位的item设置50上边距
//                    outRect.top = 50;
//                    return;
//                }
//                if (parent.getChildAdapterPosition(view) == state.getItemCount() -1){ //给最后一位的item设置50下边距
//                    outRect.bottom = 50;
//                    return;
//                }

            }



            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                if (!isNotify){
                    return;
                }
                isNotify = false;

                //TODO  为什么第一个item的高度有问题?

                int count = parent.getChildCount();     //获得当前RecyclerView数量
                Log.e("TAG", "onDraw: "+count );
                for (int i = 0; i < count; i++) {       //遍历全部item View

                    View view = parent.getChildAt(i);
                    View viewById = view.findViewById(R.id.view);
                    TextView tv = view.findViewById(R.id.tv);
                    Log.e("TAG", "onDraw: "+ i+ "----- " +tv.getHeight() +tv.getText().toString() );

                    int index = i + ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    if (currentPosition == index){
//                        Log.e("TAG", "onDraw: "+currentPosition );
                        viewById.setVisibility(View.GONE);
                        Log.e("TAG", "onDraw: 设置不可以见"+ i);
                    }else{
                        Log.e("TAG", "onDraw: 设置可以见"+ i);
                        viewById.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(tv.getWidth(),tv.getHeight());
                        viewById.setLayoutParams(layoutParams);
                    }
                }
            }
        };
    }

    private void addData() {
        list.clear();
        list.add("00000000000000000000000000000000000");
        list.add("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        list.add("222222222222");
        list.add("33333333333333333333333333333333333333333333333333");
        list.add("4444444444444444444444444444444444444444444444444444444444444444444444444444");
        list.add("55555555555555555555555555555555555555555555");
        list.add("666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
        list.add("777777777777777777777777");
        list.add("88888888888888888888888888888888888");
        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}