package com.wuk.wk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    private RecyclerView rvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        rvView = findViewById(R.id.rv_view);


        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvView.setLayoutManager(layoutManager);
        ArrayList<String> bookList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            bookList.add("test"+i);
        }
        MyBookAdapter myBookAdapter = new MyBookAdapter( this,bookList);
        rvView.setAdapter(myBookAdapter);
    }

    static class MyBookAdapter extends RecyclerView.Adapter<MyHolder>{

        private MainActivity4 mainActivity4;
        private ArrayList<String> bookList;

        public MyBookAdapter(MainActivity4 mainActivity4, ArrayList<String> bookList) {

            this.mainActivity4 = mainActivity4;
            this.bookList = bookList;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tv.setText(bookList.get(position));
            if (position % 2 == 0){
                holder.tv.setBackgroundColor(mainActivity4.getResources().getColor(R.color.white));
            }else{
                holder.tv.setBackgroundColor(mainActivity4.getResources().getColor(R.color.black));
            }
        }

        @Override
        public int getItemCount() {
            return bookList.size();
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        private  TextView tv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.text);
        }


    }
}