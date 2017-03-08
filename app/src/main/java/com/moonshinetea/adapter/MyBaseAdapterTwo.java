package com.moonshinetea.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lidroid.xutils.BitmapUtils;
import com.moonshinetea.R;
import com.moonshinetea.bean.SiftBean;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/2/14.
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

public class MyBaseAdapterTwo extends BaseAdapter {
    private Context context;
    private List<SiftBean.DataBean.ProductsBean> list;

    public MyBaseAdapterTwo(Context context, List<SiftBean.DataBean.ProductsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder v = null;
        if (view == null) {
            view = View.inflate(context, R.layout.listviewitem, null);
            v = new ViewHolder();
            v.imager = (ImageView) view.findViewById(R.id.Listview_imager_one);
            v.textView_one = (TextView) view.findViewById(R.id.Listview_text_one);
            v.textView_two = (TextView) view.findViewById(R.id.Listview_text_two);
            v.textView_thress = (TextView) view.findViewById(R.id.Listview_text_thress);
            v.textView_four = (TextView) view.findViewById(R.id.Listview_text_four);
            v.textView_five = (TextView) view.findViewById(R.id.Listview_textview_five);
            view.setTag(v);
        } else {
            v = (ViewHolder) view.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(v.imager, list.get(i).getImage_small());
        v.textView_one.setText(list.get(i).getName());
        String s = list.get(i).getShort_description();
        Log.i("aa",s);
        if("".equals(s))
        {
            v.textView_five.setVisibility(View.INVISIBLE);
        }else
        {
            v.textView_five.setText(s);
        }
        String price = list.get(i).getPrice();
        String featured_price = list.get(i).getFeatured_price();
        if (featured_price == null) {
            v.textView_four.setVisibility(View.INVISIBLE);
            v.textView_thress.setVisibility(View.INVISIBLE);
            v.textView_two.setText("￥" + list.get(i).getPrice());
        } else {
            v.textView_four.setText("￥" + list.get(i).getPrice());
            v.textView_four.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            v.textView_two.setText("￥" + list.get(i).getFeatured_price());
            Double a = Double.parseDouble(price);
            Double ii = Double.parseDouble(featured_price);
            v.textView_thress.setText("立减￥" + (a - ii));
        }
        return view;
    }
    class ViewHolder {
        ImageView imager;
        TextView textView_one, textView_two, textView_thress, textView_four, textView_five;
    }
}
