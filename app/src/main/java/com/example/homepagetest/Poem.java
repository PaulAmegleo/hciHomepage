package com.example.homepagetest;

import android.os.Parcel;
import android.os.Parcelable;

public class Poem implements Parcelable {
    private String title;
    private String author;

    public Poem(String title, String author) {
        this.title = title;
        this.author = author;
    }

    protected Poem(Parcel in) {
        title = in.readString();
        author = in.readString();
    }

    public static final Creator<Poem> CREATOR = new Creator<Poem>() {
        @Override
        public Poem createFromParcel(Parcel in) {
            return new Poem(in);
        }

        @Override
        public Poem[] newArray(int size) {
            return new Poem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
    }
}
