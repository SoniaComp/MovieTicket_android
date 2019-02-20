package com.example.sonia.movie_ticket;

public class CommentItem {
    String userName;
    String review;

    public CommentItem(String name, String text) {
        setUserName(name);
        setReview(text);
    }

    //직접 접근 보다는, get, set 함수를 사용한다.
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
}
