package com.example.sonia.movie_ticket;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CommentItemView extends LinearLayout {
    TextView userName;
    TextView review;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    //초기화 하는 함수
   private void init(Context context) {
        // 시스템 서비스: 단말이 시작됐을 때 단말에서 기본적으로 실행시켜 놓는 서비스
        // 그 중에 LayoutInflater을 사용하겠다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 직접 인플레이션 해주어 하므로, layout Inflater를 참조한다.
        inflater.inflate(R.layout.comment_item_view, this, true); // 레이아웃이 this(최상위 레이아웃)에 붙는다.

       userName = (TextView) findViewById(R.id.userName);
       review = (TextView) findViewById(R.id.review);
    }

    public void setName(String name){
        userName.setText(name);
    }

    public void setReview(String text){
        review.setText(text);
    }
}
