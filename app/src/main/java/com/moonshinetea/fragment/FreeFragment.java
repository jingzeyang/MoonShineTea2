package com.moonshinetea.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.moonshinetea.R;
import com.moonshinetea.adapter.MypagerAdapter;

import java.util.ArrayList;
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

public class FreeFragment extends Fragment {

    private TabLayout tablayout;
    private ViewPager viewPager;
    private String[] mTitles = new String[]{"天天优惠", "为你精选", "亲的最爱"};
    private List<Fragment> mFragments;
    private MypagerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.freefragment, null);
        tablayout = (TabLayout) view.findViewById(R.id.id_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        initData();
        initView();
        return view;
    }

    private void initView() {
        mAdapter = new MypagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles);
        //最多可以缓存5个界面
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(mAdapter);
        //与viewpager关联起来
        tablayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        mFragments = new ArrayList<>();
        FreeFragmentone freeFragmentone = new FreeFragmentone();
        FreeFragmentwto freeFragmenttwo = new FreeFragmentwto();
        FreeFragmentthress freeFragmentthress = new FreeFragmentthress();
        mFragments.add(freeFragmentone);
        mFragments.add(freeFragmenttwo);
        mFragments.add(freeFragmentthress);

    }
}
