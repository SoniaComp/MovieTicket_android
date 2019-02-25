package com.example.sonia.movie_ticket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class writeReviewActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView review;
    Button save;
    Button cancel;
    float rating;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wirte_review);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        review = (TextView) findViewById(R.id.review);

        // 저장 버튼
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });

        // 취소 버튼
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void goBackToMain() {
        text = review.getText().toString();
        rating = ratingBar.getRating();

        Intent intent = new Intent(); // 인텐트 객체 생성하고 부가 데이터 넣기
        intent.putExtra("rating", rating);
        intent.putExtra("text", text);
        setResult(RESULT_OK, intent); // 응답 보대기
        finish(); // 현재 액티비티 없애기
    }
}
