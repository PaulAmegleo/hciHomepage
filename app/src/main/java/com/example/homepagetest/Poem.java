package com.example.homepagetest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Poem implements Parcelable {
    private String title;
    private String author;
    private List<String> lines;

    public Poem(String title, String author, List<String> lines) {
        this.title = title;
        this.author = author;
        this.lines = lines;
    }

    protected Poem(Parcel in) {
        title = in.readString();
        author = in.readString();
        lines = in.createStringArrayList();
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

    public List<String> getLines() {
        return lines;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeStringList(lines);
    }
}
