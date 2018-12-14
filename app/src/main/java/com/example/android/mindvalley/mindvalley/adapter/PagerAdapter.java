package com.example.android.mindvalley.mindvalley.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.mindvalley.R;
import com.example.android.mindvalley.mindvalley.fragment.Favorite;
import com.example.android.mindvalley.mindvalley.fragment.Pinterest;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static final int ONE = 0;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == ONE){
            return new Pinterest();
        }else {
            return new Favorite();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == ONE){
            return mContext.getString(R.string.list);
        }else {
            return mContext.getString(R.string.favorite);
        }
    }
}
