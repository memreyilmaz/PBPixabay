
package com.payback.pixabay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Parcelable
{

    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;
    @SerializedName("webformatHeight")
    @Expose
    private int webformatHeight;
    @SerializedName("webformatWidth")
    @Expose
    private int webformatWidth;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("imageWidth")
    @Expose
    private int imageWidth;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("comments")
    @Expose
    private int comments;
    @SerializedName("pageURL")
    @Expose
    private String pageURL;
    @SerializedName("imageHeight")
    @Expose
    private int imageHeight;
    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("previewHeight")
    @Expose
    private int previewHeight;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("downloads")
    @Expose
    private int downloads;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("favorites")
    @Expose
    private int favorites;
    @SerializedName("imageSize")
    @Expose
    private int imageSize;
    @SerializedName("previewWidth")
    @Expose
    private int previewWidth;
    @SerializedName("userImageURL")
    @Expose
    private String userImageURL;
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

    }
    ;

    protected Hit(Parcel in) {
        this.largeImageURL = ((String) in.readValue((String.class.getClassLoader())));
        this.webformatHeight = ((int) in.readValue((int.class.getClassLoader())));
        this.webformatWidth = ((int) in.readValue((int.class.getClassLoader())));
        this.likes = ((int) in.readValue((int.class.getClassLoader())));
        this.imageWidth = ((int) in.readValue((int.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.userId = ((int) in.readValue((int.class.getClassLoader())));
        this.views = ((int) in.readValue((int.class.getClassLoader())));
        this.comments = ((int) in.readValue((int.class.getClassLoader())));
        this.pageURL = ((String) in.readValue((String.class.getClassLoader())));
        this.imageHeight = ((int) in.readValue((int.class.getClassLoader())));
        this.webformatURL = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.previewHeight = ((int) in.readValue((int.class.getClassLoader())));
        this.tags = ((String) in.readValue((String.class.getClassLoader())));
        this.downloads = ((int) in.readValue((int.class.getClassLoader())));
        this.user = ((String) in.readValue((String.class.getClassLoader())));
        this.favorites = ((int) in.readValue((int.class.getClassLoader())));
        this.imageSize = ((int) in.readValue((int.class.getClassLoader())));
        this.previewWidth = ((int) in.readValue((int.class.getClassLoader())));
        this.userImageURL = ((String) in.readValue((String.class.getClassLoader())));
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

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(int webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(int webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
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

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(largeImageURL);
        dest.writeValue(webformatHeight);
        dest.writeValue(webformatWidth);
        dest.writeValue(likes);
        dest.writeValue(imageWidth);
        dest.writeValue(id);
        dest.writeValue(userId);
        dest.writeValue(views);
        dest.writeValue(comments);
        dest.writeValue(pageURL);
        dest.writeValue(imageHeight);
        dest.writeValue(webformatURL);
        dest.writeValue(type);
        dest.writeValue(previewHeight);
        dest.writeValue(tags);
        dest.writeValue(downloads);
        dest.writeValue(user);
        dest.writeValue(favorites);
        dest.writeValue(imageSize);
        dest.writeValue(previewWidth);
        dest.writeValue(userImageURL);
        dest.writeValue(previewURL);
    }

    public int describeContents() {
        return  0;
    }

}
