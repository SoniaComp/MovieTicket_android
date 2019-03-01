package com.example.sonia.movie_ticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

//뷰 페이저는 support 라이브러리에 들어가 있으므로 패키지 이름까지 넣어주어야 함
//어댑터는 SDK에서 제공하는 FragmentStatePagerAdapter을 상속해서 만든다.
public class MoviePagerAdapter extends FragmentStatePagerAdapter {
    //프래그먼트 객체를 담을 ArrayList를 선언
    int mNumOfTabs;
    ArrayList<Fragment> items = new ArrayList<Fragment>();

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addItem(Fragment item) {
        items.add(item);
    }

    @Override
    public Fragment getItem(int i) {
        return items.get(i);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
        //return super.getPageWidth(position);
    }
}