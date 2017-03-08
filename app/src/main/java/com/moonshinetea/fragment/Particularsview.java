package com.moonshinetea.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.google.gson.Gson;
import com.moonshinetea.R;
import com.moonshinetea.bean.ProductGBean;
import com.moonshinetea.utils.GlideImageLoader;
import com.moonshinetea.utils.MyokHttpClient;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
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

public class Particularsview extends AppCompatActivity {
    private Banner banner;
    private Dataaa dataaa;
    private List<String> imagerlista;
    private ProductGBean.DataBean.ProductBean product;
    private Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String a= (String) msg.obj;
            Gson gson=new Gson();
            ProductGBean productGBean = gson.fromJson(a, ProductGBean.class);
            product = productGBean.getData().getProduct();
            String image1 = product.getApp_long_image1();
            String image2 = product.getApp_long_image2();
            String image3 = product.getApp_long_image3();
            String image4 = product.getApp_long_image4();
            String image5 = product.getApp_long_image5();
            imagerlista=new ArrayList<>();
            imagerlista.add(image1);
            imagerlista.add(image2);
            imagerlista.add(image3);
            imagerlista.add(image4);
            imagerlista.add(image5);
            Log.i("Tag",imagerlista.size()+"...............");

            banner.setImages(imagerlista).setImageLoader(new GlideImageLoader()).start();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particularsview);
        banner = (Banner) findViewById(R.id.banner);
        Intent it = getIntent();
        int cc = it.getIntExtra("id",0);
        String patha = "http://eleteamapi.ygcr8.com/v1/product/view?id="+cc;
        initData(patha);

        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent it=new Intent(Particularsview.this,PhotoviewA.class);
                it.putExtra("list",product);
                Log.i("bbb","......................................................");
                startActivity(it);
            }
        });
    }

    private void initData(String path) {
        dataaa = new Dataaa();
        dataaa.getJson(path);
    }

    class Dataaa extends MyokHttpClient {

        @Override
        protected void getString(String str) {
            Log.i("TAG",str);
            Message msg=Message.obtain();
            msg.obj=str;
            handler.sendMessage(msg);

        }
    }

}
