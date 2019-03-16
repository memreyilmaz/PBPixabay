
package com.payback.pixabay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse implements Parcelable
{

    @SerializedName("totalHits")
    @Expose
    private int totalHits;
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    @SerializedName("total")
    @Expose
    private int total;
    public final static Parcelable.Creator<ImageResponse> CREATOR = new Creator<ImageResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ImageResponse createFromParcel(Parcel in) {
            return new ImageResponse(in);
        }

        public ImageResponse[] newArray(int size) {
            return (new ImageResponse[size]);
        }

    }
    ;

    protected ImageResponse(Parcel in) {
        this.totalHits = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.hits, (Hit.class.getClassLoader()));
        this.total = ((int) in.readValue((int.class.getClassLoader())));
    }

    public ImageResponse() {
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalHits);
        dest.writeList(hits);
        dest.writeValue(total);
    }

    public int describeContents() {
        return  0;
    }

}
