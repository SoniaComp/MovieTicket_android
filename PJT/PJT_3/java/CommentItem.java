package com.example.sonia.movie_ticket;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    String userName;
    String review;
    float score;

    // Parcelable을 생성하기 위한 생성자 //임의 생성
    public CommentItem(String name, String text, float score) {
        setUserName(name);
        setReview(text);
        setScore(score);
    }
    // Parcelable을 생성하기 위한 생성자 Parcel을 매개변수로 넘겨줌
    public CommentItem(Parcel src){
        setUserName(src.readString());
        setReview(src.readString());
        setScore(src.readFloat());
    }
    //각 값을 넘겨주기 위한 getter and setter
    //직접 접근 보다는, get, set 함수를 사용한다.
    public float getScore() {
        return score;
    }
    public void setScore(float score) {
        this.score = score;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }

    //Parcelable 객체로 구현하기 위한 Parcelable Method ArrayList 구현...
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Object createFromParcel(Parcel source) {
            return new CommentItem(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    //Parcel하려는 Object의 종류를 정의
    @Override
    public int describeContents() {
        return 0;
    }

    //Parcelable write를 구현하기 위한 Method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(review);
        dest.writeFloat(score);
    }
}
