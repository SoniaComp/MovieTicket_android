package com.example.sonia.movie_ticket;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //-----------------------menu 클릭시 옆으로 로그인 페이지(뷰 슬라이딩)-----------------------//
    boolean isPageOpen=false;
    Animation goToLeft;
    Animation goToRight;
    LinearLayout page;
    ImageView menu;

    //-----------------------뷰 페이저-----------------------//
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-----------------------menu 클릭시 옆으로 로그인 페이지(뷰 슬라이딩)-----------------------//
        page = (LinearLayout) findViewById(R.id.login);
        goToLeft = AnimationUtils.loadAnimation(this, R.anim.go_to_left);
        goToRight = AnimationUtils.loadAnimation(this, R.anim.go_to_right);

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToggleMenu(v);
            }
        });

        //-----------------------뷰 페이저-----------------------//
        pager = (ViewPager) findViewById(R.id.container);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);
        adapter.addItem(fragment4);
        adapter.addItem(fragment5);
        pager.setAdapter(adapter);

        // 화면에서 보이는 페이지 제한
        pager.setOffscreenPageLimit(3);
        // Disable clip to padding
        pager.setClipToPadding(false);
        // set padding manually, the more you set the padding, the more you see of prev&next padding
        pager.setPadding(90,0,0,0);
        //sets a margin individual pages to ensure that there is a gap b/w them
        //pager.setPageMargin(-20);
        pager.setPageMargin(getResources().getDisplayMetrics().widthPixels / -9);
    }
    //-----------------------menu 클릭시 옆으로 로그인 페이지(뷰 슬라이딩)-----------------------//
    public boolean ToggleMenu(View v){
        if (isPageOpen) {
            page.startAnimation(goToLeft);
        } else {
            page.setVisibility(View.VISIBLE);
            page.startAnimation(goToRight);
        }
        SlidingPageAnimationListener pageListener = new SlidingPageAnimationListener();
        goToLeft.setAnimationListener(pageListener);
        goToRight.setAnimationListener(pageListener);
        return true;
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) { }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                page.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) { }
    }
}

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_provider:
                if (isPageOpen) {
                    page.startAnimation(goToLeft);
                } else {
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(goToRight);
                }
                SlidingPageAnimationListener pageListener = new SlidingPageAnimationListener();
                goToLeft.setAnimationListener(pageListener);
                goToRight.setAnimationListener(pageListener);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    */