 package com.example.sonia.movie_ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    Button thumbUp;
    Button thumbDown;
    TextView upCountView;
    TextView downCountView;

    boolean upState = false;
    boolean downState = false;
    int upCount;
    int downCount;

     ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml 파일을 메모리에 객체화(인플레이션 과정)

        //xml 레이아웃 내 뷰를 찾아서 사용하기
        thumbUp = (Button) findViewById(R.id.thumbUp);
        thumbDown = (Button) findViewById(R.id.thumbDown);
        upCountView = (TextView) findViewById(R.id.upCount);
        downCountView = (TextView) findViewById(R.id.downCount);

        // 리스트뷰는 껍데기 역할만 한다. 데이터를 관리할 수도 없고, 각각의 뷰도 관리할 수 없다. - 어댑터 사용
        listView = (ListView)findViewById(R.id.listView);
        CommentAdapter adapter = new CommentAdapter(); //어댑터와 물어보면서 화면에 표시
        // Adapter에서 데이터도 관리하고 화면도 관리
        adapter.addItem(new CommentItem("Sonia", "Good"));
        adapter.addItem(new CommentItem("Zlata", "хорошо"));
        listView.setAdapter(adapter);

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upCount = Integer.valueOf(upCountView.getText().toString());

                if(downState){
                    thumbDown.performClick();
                }

                if(upState){
                    decrUpCount();
                }else{
                    incrUpCount();
                }
                upState = !upState;
            }
        });

        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downCount = Integer.valueOf(downCountView.getText().toString());

                if(upState){
                    thumbUp.performClick();
                }

                if(downState){
                    decrDownCount();
                }else{
                    incrDownCount();
                }
                downState = !downState;
            }
        });

    }

    class CommentAdapter extends BaseAdapter {
        // 데이터 관리
        // 문자열 하나가지고 안되므로, 정보를 담을 수 있는 객체를 만듦
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        public void addItem(CommentItem item){
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

           return view;
        }
    }

     public void incrUpCount(){
         upCount += 1;
         upCountView.setText(String.valueOf(upCount));
         thumbUp.setBackgroundResource(R.drawable.ic_thumb_up_selected);
     }
     public void decrUpCount(){
         upCount -= 1;
         upCountView.setText(String.valueOf(upCount));
         thumbUp.setBackgroundResource(R.drawable.thumb_up);
     }
     public void incrDownCount(){
         downCount += 1;
         downCountView.setText(String.valueOf(downCount));
         thumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
     }
     public void decrDownCount(){
         downCount -= 1;
         downCountView.setText(String.valueOf(downCount));
         thumbDown.setBackgroundResource(R.drawable.thumb_down);
     }


}
