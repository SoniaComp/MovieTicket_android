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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    //--------------------------------PJT 2--------------------------------//
    Button thumbUp;
    Button thumbDown;
    TextView upCountView;
    TextView downCountView;

    boolean upState = false;
    boolean downState = false;
    int upCount;
    int downCount;
    //--------------------------------PJT 3--------------------------------//
    public static final int REQUEST_CODE = 5;
    public static final int REQUEST_CODE_sub = 10;

    Button showAll;
    Button write;
    float score;
    String review;

    ListView listView;

    CommentAdapter adapter = new CommentAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // xml 파일을 메모리에 객체화(인플레이션 과정)

        //--------------------------------PJT 2--------------------------------//
        /*                              좋아요 표시                              */
        thumbUp = (Button) findViewById(R.id.thumbUp);
        thumbDown = (Button) findViewById(R.id.thumbDown);
        upCountView = (TextView) findViewById(R.id.upCount);
        downCountView = (TextView) findViewById(R.id.downCount);

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upCount = Integer.valueOf(upCountView.getText().toString());

                if (downState) {
                    thumbDown.performClick();
                }

                if (upState) {
                    decrUpCount();
                } else {
                    incrUpCount();
                }
                upState = !upState;
            }
        });

        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downCount = Integer.valueOf(downCountView.getText().toString());

                if (upState) {
                    thumbUp.performClick();
                }

                if (downState) {
                    decrDownCount();
                } else {
                    incrDownCount();
                }
                downState = !downState;
            }
        });

        /*                              리스트뷰                              */

        // 리스트뷰는 껍데기 역할만 한다. 데이터를 관리할 수도 없고, 각각의 뷰도 관리할 수 없다. - 어댑터 사용
        listView = (ListView) findViewById(R.id.listView);

        // Adapter에서 데이터도 관리하고 화면도 관리
        adapter.addItem(new CommentItem("Sonia", "Good", 4.3f));
        adapter.addItem(new CommentItem("Zlata", "хорошо", 4.8f));

        listView.setAdapter(adapter);
        //--------------------------------PJT 2--------------------------------//

        //--------------------------------PJT 3--------------------------------//
        //xml 레이아웃 내 뷰를 찾아서 사용하기
        write = (Button)findViewById(R.id.goToWrite);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWriteAcitivity();
            }
        });

        showAll = (Button)findViewById(R.id.buttonShowAll);
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShowAll();
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////

    //--------------------------------PJT 2 메소드--------------------------------//
    /*                              좋아요 표시                              */
    public void incrUpCount() {
        upCount += 1;
        upCountView.setText(String.valueOf(upCount));
        thumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
    }

    public void decrUpCount() {
        upCount -= 1;
        upCountView.setText(String.valueOf(upCount));
        thumbUp.setBackgroundResource(R.drawable.thumb_up);
    }

    public void incrDownCount() {
        downCount += 1;
        downCountView.setText(String.valueOf(downCount));
        thumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDownCount() {
        downCount -= 1;
        downCountView.setText(String.valueOf(downCount));
        thumbDown.setBackgroundResource(R.drawable.thumb_down);
    }

    /*                              리스트뷰                              */
    class CommentAdapter extends BaseAdapter {
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

    //--------------------------------PJT 3 메소드--------------------------------//
    /*                              코멘트 쓰기                              */
    private void goToWriteAcitivity() {
        Intent intent = new Intent(getApplicationContext(), writeReviewActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Toast.makeText(getApplicationContext(),"시작", Toast.LENGTH_LONG).show();

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            processIntent(data);
            adapter.addItem(new CommentItem("UserID", review, score));
            listView.setAdapter(adapter);
        }

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE_sub){
            ArrayList<CommentItem> items = data.getExtras().getParcelableArrayList("data");

            Toast.makeText(getApplicationContext(), "오키", Toast.LENGTH_LONG).show();

            for(int i=adapter.items.size(); i<items.size(); i++){
               adapter.addItem(items.get(i));
            }

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

    /*                              모두 보여주기                             */
    private void goToShowAll(){
        Intent intent = new Intent(getApplicationContext(), showAll.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", adapter.items);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE_sub);
    }
}
