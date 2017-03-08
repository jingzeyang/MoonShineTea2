package com.moonshinetea.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;
import com.moonshinetea.R;
import com.moonshinetea.bean.ProductGBean;
import com.moonshinetea.utils.HackyViewPager;


import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 荆泽阳 on 2017/2/16.
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

public class PhotoviewA extends AppCompatActivity {
    List<String> listsStr = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoview);
        ViewPager mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        setContentView(mViewPager);
        //进行接值
        Intent it=getIntent();
        ProductGBean.DataBean.ProductBean list = (ProductGBean.DataBean.ProductBean) it.getSerializableExtra("list");

        listsStr.add(list.getApp_long_image1());
        listsStr.add(list.getApp_long_image2());
        listsStr.add(list.getApp_long_image3());
        listsStr.add(list.getApp_long_image4());
        listsStr.add(list.getApp_long_image5());
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(2);
    }

    class SamplePagerAdapter extends PagerAdapter {

		/*private  final int[] sDrawables = { R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper,
				R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper };*/

        @Override
        public int getCount() {
            //return sDrawables.length;
            return listsStr.size();
        }
        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            BitmapUtils bitmapUtils = new BitmapUtils(PhotoviewA.this);
            bitmapUtils.display(photoView,listsStr.get(position));
            //photoView.setImageResource(sDrawables[position]);
            container.addView(photoView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

}
