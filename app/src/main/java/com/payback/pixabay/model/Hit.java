
package com.payback.pixabay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Parcelable {

    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("pageURL")
    @Expose
    private String pageURL;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("favorites")
    @Expose
    private int favorites;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;
    public final static Parcelable.Creator<Hit> CREATOR = new Creator<Hit>() {

        @SuppressWarnings({
            "unchecked"
        })
        public Hit createFromParcel(Parcel in) {
            return new Hit(in);
        }

        public Hit[] newArray(int size) {
            return (new Hit[size]);
        }

    };

    protected Hit(Parcel in) {
        this.largeImageURL = ((String) in.readValue((String.class.getClassLoader())));
        this.likes = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.comments = ((int) in.readValue((int.class.getClassLoader())));
        this.pageURL = ((String) in.readValue((String.class.getClassLoader())));
        this.tags = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((String) in.readValue((String.class.getClassLoader())));
        this.favorites = ((int) in.readValue((int.class.getClassLoader())));
        this.previewURL = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Hit() {
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(largeImageURL);
        dest.writeValue(likes);
        dest.writeValue(id);
        dest.writeValue(comments);
        dest.writeValue(pageURL);
        dest.writeValue(tags);
        dest.writeValue(user);
        dest.writeValue(favorites);
        dest.writeValue(previewURL);
    }

    public int describeContents() {
        return  0;
    }
}