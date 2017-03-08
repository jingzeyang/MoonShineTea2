package com.moonshinetea.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.google.gson.Gson;
import com.moonshinetea.R;
import com.moonshinetea.adapter.MyBaseAdapter;
import com.moonshinetea.bean.FavorableBean;
import com.moonshinetea.utils.MyokHttpClient;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/2/13.
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

public class FreeFragmentone extends Fragment {

    private ListView listview;
    private String path="http://eleteamapi.ygcr8.com/v1/product/list-featured-price";
    private Dataa dataa;
    private MyBaseAdapter myBaseAdapter;
    private Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String  string = (String) msg.obj;
            Gson gson=new Gson();
            FavorableBean bean = gson.fromJson(string, FavorableBean.class);
            List<FavorableBean.DataBean.ProductsBean> products = bean.getData().getProducts();
            Log.i("aaa",products.toString());
            myBaseAdapter = new MyBaseAdapter(getActivity(),products);
            listview.setAdapter(myBaseAdapter);

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.freefragmentone,null);
        listview= (ListView) view.findViewById(R.id.DayListview);
        initData();


        return view;
    }

    private void initData() {

        dataa=new Dataa();
        dataa.getJson(path);

    }
    class  Dataa extends MyokHttpClient
    {

        @Override
        protected void getString(String str) {
            Message msg=Message.obtain();
            msg.obj=str;
            Log.i("TAG",str);
            handler.sendMessage(msg);
        }
    }

}
