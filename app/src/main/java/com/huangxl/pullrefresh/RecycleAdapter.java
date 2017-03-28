package com.huangxl.pullrefresh;

import android.content.Context;
import android.icu.util.ValueIterator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huangxl on 2017/3/28.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.VH> {

    LayoutInflater inflater;
    Context context;

    public RecycleAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item,parent,false);
        return new VH(view);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.textView.setText("item "+position);

    }

    class VH extends RecyclerView.ViewHolder{
        TextView textView;
        public VH(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.textview);

        }
    }
}
