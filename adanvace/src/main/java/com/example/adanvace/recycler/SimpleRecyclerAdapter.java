package com.example.adanvace.recycler;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adanvace.R;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/10.
 *         Function :
 *         Issue :
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder> {


    private LayoutInflater layoutInflater;
    private List<String> mData;

    public SimpleRecyclerAdapter(Context context, List<String> data){
        layoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_text,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public AppCompatTextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (AppCompatTextView) itemView.findViewById(R.id.tv_item_recycler);

        }
    }
}
