package com.moonshinetea.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lidroid.xutils.BitmapUtils;
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

public class RecycleviewAdapterTwo extends RecyclerView.Adapter<RecycleviewAdapterTwo.MyViewHolder> {

    private Context context;
    List<Bean.DataBean.CategoriesBean>  list;
    public OnItemClickListener clickListener;

    public RecycleviewAdapterTwo(Context context) {
        this.context = context;

    }
    public void setDate(List<Bean.DataBean.CategoriesBean>  list){
        this.list = list;
        this.notifyDataSetChanged();
    }

        public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recyclerviewitemtwo, parent,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.text_one.setText(list.get(MyApplication.changeint).getProducts().get(position).getName());

        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(holder.imager, list.get(MyApplication.changeint).getProducts().get(position).getImage_small());
        String price = list.get(MyApplication.changeint).getProducts().get(position).getPrice();
        String featured_price = list.get(MyApplication.changeint).getProducts().get(position).getFeatured_price();
        if(featured_price==null)
        {
            holder.text_four.setVisibility(View.INVISIBLE);
            holder.text_thress.setVisibility(View.INVISIBLE);
            holder.text_two.setText("￥"+list.get(MyApplication.changeint).getProducts().get(position).getPrice());


        }else
        {
            holder.text_four.setText("￥"+list.get(MyApplication.changeint).getProducts().get(position).getPrice());
            holder.text_four.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.text_two.setText("￥"+list.get(MyApplication.changeint).getProducts().get(position).getFeatured_price());
            Double i = Double.parseDouble(price);
            Double ii = Double.parseDouble(featured_price);
            holder.text_thress.setText("立减￥" + (i-ii));

        }

//        if (MyApplication.changeint == position) {
//            holder.linearLayout.setBackgroundColor(Color.RED);
//        } else {
//            holder.linearLayout.setBackgroundColor(Color.BLUE);
//        }
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
        return list.get(MyApplication.changeint).getProducts().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_one, text_two, text_thress, text_four;
        ImageView imager;


        public MyViewHolder(View view) {
            super(view);
            text_one = (TextView) view.findViewById(R.id.text_one);
            text_two = (TextView) view.findViewById(R.id.text_two);
            text_thress = (TextView) view.findViewById(R.id.text_thress);
            text_four = (TextView) view.findViewById(R.id.text_four);
            imager = (ImageView) view.findViewById(R.id.imager_one);

        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos);
    }


}
