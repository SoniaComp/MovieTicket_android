package com.example.sonia.movie_ticket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class showAll extends AppCompatActivity {
    public static final int REQUEST_CODE_sub = 10;
    ListView listView;
    String review;
    Float score;

    CommentAdapter adapter = new CommentAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        /*                              메인에서 데이터 가져오기                              */
        listView = (ListView) findViewById(R.id.listView);

        ArrayList<CommentItem> items = getIntent().getExtras().getParcelableArrayList("data");
        Toast.makeText(getApplicationContext(), String.valueOf(items.size()), Toast.LENGTH_LONG).show();

        for(int i=0; i<items.size(); i++){
            adapter.addItem(items.get(i));
        }

        listView.setAdapter(adapter);

        /*                              코멘트 쓰기                              */
        Button button = (Button) findViewById(R.id.goToWrite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWriteAcitivity();
            }
        });
    }

    public class CommentAdapter extends BaseAdapter {
        // 데이터 관리
        // 문자열 하나가지고 안되므로, 정보를 담을 수 있는 객체를 만듦
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        public void addItem(CommentItem item) {
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 뷰를 부분화면으로 정의하고, 객체를 만든 다음에, 데이터를 설정하고 리턴해주기
            // 모든 뷰는 context 객체를 받게 되어 있다.
            CommentItemView view = new CommentItemView(getApplicationContext());
            CommentItem item = items.get(position);
            view.setName(item.getUserName());
            view.setReview(item.getReview());
            view.setScore(item.getScore());

            return view;
        }
    }

    /*                              코멘트 쓰기                              */
    private void goToWriteAcitivity() {
        Intent intent = new Intent(getApplicationContext(), writeReviewActivity.class);
        startActivityForResult(intent, REQUEST_CODE_sub);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_sub){
            processIntent(data);
            adapter.addItem(new CommentItem("UserID", review, score));
            listView.setAdapter(adapter);
        }
    }

    //getIntent를 통해 intent 객체에 전달된 걸 받음
    private void processIntent(Intent intent) {
        if (intent != null) {
            score = intent.getFloatExtra("rating", 0.0f);
            review = intent.getStringExtra("text");
        }
    }

    /*                              메인으로 돌아가기                              */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", adapter.items);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        super.onBackPressed(); //순서 중요
    }
}
