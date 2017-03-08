package com.moonshinetea.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moonshinetea.R;
import com.moonshinetea.bean.Bean;
import com.moonshinetea.utils.MyApplication;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/2/10.
 * .::::.
 * .::::::::.
 * :::::::::::  Goddess bless, never bug
 * ..:::::::::::'
 * '::::::::::::'
 * .::::::::::
 * '::::::::::::::..
 * ..::::::::::::.
 * ``::::::::::::::::
 * ::::``:::::::::'        .:::.
 * ::::'   ':::::'       .::::::::.
 * .::::'      ::::     .:::::::'::::.
 * .:::'       :::::  .:::::::::' ':::::.
 * .::'        :::::.:::::::::'      ':::::.
 * .::'         ::::::::::::::'         ``::::.
 * ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 * '.:::::'                    ':'````..
 */

public class RecycleviewAdapter extends RecyclerView.Adapter<RecycleviewAdapter.MyViewHolder> {

    private Context context;
    private List<Bean.DataBean.CategoriesBean> list;
    public OnItemClickListener clickListener;


    public RecycleviewAdapter(Context context, List<Bean.DataBean.CategoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recyclerviewitem, parent,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getName());
        if (MyApplication.changeint == position) {
            holder.linearLayout.setBackgroundColor(Color.WHITE);
        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener!=null) {
                        int layoutPosition = holder.getLayoutPosition();
                        clickListener.onItemClickListener(layoutPosition);
                    }
                }
            });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.recycletext);
            linearLayout = (LinearLayout) view.findViewById(R.id.lll);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos);
    }


}
