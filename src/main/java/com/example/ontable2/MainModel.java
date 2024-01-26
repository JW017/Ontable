package com.example.ontable2;

public class MainModel {
////////////////////这个attribute有说是要和firebase里面一样的naming////////////////////
    private String memodetails;

    MainModel(){

    }

    public MainModel(String memodetails) {
//        this.id = id;
        this.memodetails = memodetails;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getDetails() {
        return memodetails;
    }

    public void setDetails(String memodetails) {
        this.memodetails = memodetails;
    }

    @Override
    public String toString() {
        return memodetails;
    }
}
