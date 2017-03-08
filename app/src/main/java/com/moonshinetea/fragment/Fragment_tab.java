package com.moonshinetea.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;
import com.moonshinetea.R;
import com.moonshinetea.adapter.RecycleviewAdapter;
import com.moonshinetea.adapter.RecycleviewAdapterTwo;
import com.moonshinetea.bean.Bean;
import com.moonshinetea.utils.MyApplication;
import com.moonshinetea.utils.MyokHttpClient;

import java.util.List;

/**
 * Created by 荆泽阳 on 2017/2/9.
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

public class Fragment_tab extends Fragment {
    //    TextView mTvText;
//
//    private View mViewContent; // 缓存视图内容
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return getContentView(inflater,container);
//    }
//
//    private View getContentView(LayoutInflater inflater, @Nullable ViewGroup container){
//        if (mViewContent == null) {//判断一下视图是否被创建，无则创建一下
//            mViewContent = inflater.inflate(R.layout.fragment, container, false);
//        }
//
//        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
//        ViewGroup parent = (ViewGroup) mViewContent.getParent();
//        if (parent != null) {
//            parent.removeView(mViewContent);
//        }
//
//        if (mTvText==null){
//            mTvText=(TextView)mViewContent.findViewById(R.id.tab_tv_text);
//        }
//
//        return mViewContent;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // 显示Fragment的Tag信息
//        mTvText.setText(String.valueOf("Page: " + getTag()));
//    }
//
//    @Override public void onDestroyView() {
//        super.onDestroyView();
//    }
    private RecyclerView recyclerViewone, recyclerViewtwo;
//    private List<String> mDatas;
    private RecycleviewAdapter recycleviewAdapter;
    private Dataa dataa;
    private Bean bean;
//    private List<Bean.DataBean.CategoriesBean.ProductsBean> productlista;
//    private List<String> imagerlist;
    private RecycleviewAdapterTwo recycleviewAdapterTwo;
    private String path = "http://eleteamapi.ygcr8.com/v1/category/list-with-product";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String string = (String) msg.obj;
            Gson gson = new Gson();
            bean = gson.fromJson(string, Bean.class);
            List<Bean.DataBean.CategoriesBean> categories = bean.getData().getCategories();

//            productlista=new ArrayList<>();
//            imagerlist=new ArrayList<>();
//            for (Bean.DataBean.CategoriesBean aa : categories) {
//                    productlista.addAll(aa.getProducts());
//                for(Bean.DataBean.CategoriesBean.ProductsBean a:productlista)
//                {
//
//                    imagerlist.add(a.getApp_long_image1());
//                    imagerlist.add(a.getApp_long_image2());
//                    imagerlist.add(a.getApp_long_image3());
//                    imagerlist.add(a.getApp_long_image4());
//                    imagerlist.add(a.getApp_long_image5());
//
//                }
//            }

            //banner设置方法全部调用完毕时最后调用
            recycleviewAdapter = new RecycleviewAdapter(getActivity(), categories);
            recycleviewAdapterTwo = new RecycleviewAdapterTwo(getActivity());
            recyclerViewone.setAdapter(recycleviewAdapter);
            recyclerViewtwo.setAdapter(recycleviewAdapterTwo);
            recycleviewAdapterTwo.setDate(categories);
            recycleviewAdapter.setClickListener(new RecycleviewAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int pos) {
                    MyApplication.changeint = pos;
                    recycleviewAdapter.notifyDataSetChanged();
                    recycleviewAdapterTwo.notifyDataSetChanged();
                }
            });
            recycleviewAdapterTwo.setClickListener(new RecycleviewAdapterTwo.OnItemClickListener() {
                @Override
                public void onItemClickListener(int pos) {
                    Intent it=new Intent(getActivity(),Particularsview.class);
                    it.putExtra("id",bean.getData().getCategories().get(MyApplication.changeint).getProducts().get(pos).getId());
                    startActivity(it);

                }
            });

        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment, null);
        recyclerViewone = (RecyclerView) view.findViewById(R.id.recyclerone);
        recyclerViewone.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewtwo = (RecyclerView) view.findViewById(R.id.recyclertwo);
        recyclerViewtwo.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();
        return view;
    }

    private void initData() {
        dataa = new Dataa();
        dataa.getJson(path);
    }
    class Dataa extends MyokHttpClient {
        @Override
        protected void getString(String str) {
            Message msg = Message.obtain();
            msg.obj = str;
            handler.sendMessage(msg);

        }
    }
}
