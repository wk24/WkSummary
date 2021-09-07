package com.wuk.wk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.PipedOutputStream;
import java.util.List;

/**
 * @author wuk
 * @date 2021/5/14
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<String> list;
    private ItemClickListtener itemClickListtener;

    public RecyclerViewAdapter(List<String> list) {


        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));


        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListtener !=null){
                    itemClickListtener.onItemClick(position);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv;
        private final View view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            view = itemView.findViewById(R.id.view);
        }


    }

    public interface ItemClickListtener{

        void onItemClick(int position);
    }

    public void setItemClickListtener(ItemClickListtener itemClickListtener){

        this.itemClickListtener = itemClickListtener;
    }

}
