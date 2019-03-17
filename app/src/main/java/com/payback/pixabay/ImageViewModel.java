package com.payback.pixabay;

import com.payback.pixabay.model.Hit;
import com.payback.pixabay.model.ImageResponse;
import com.payback.pixabay.rest.PixabayApiClient;
import com.payback.pixabay.rest.PixabayApiInterface;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ImageViewModel extends ViewModel {

    private MutableLiveData<List<Hit>> imageList;

    public LiveData<List<Hit>> getImages(String searchquery) {
        if (imageList == null) {
            imageList = new MutableLiveData<List<Hit>>();
            loadImages(searchquery);
        }
        return imageList;
    }

    private void loadImages(String searchquery) {

        PixabayApiInterface apiService = PixabayApiClient.getClient().create(PixabayApiInterface.class);
        String photo = "photo";

        Call<ImageResponse> call = apiService.getSearched(searchquery, photo);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                Timber.i("Request Url: %s", call.request().url().toString());
                Timber.i("Response code: %s", response.code());

                imageList.setValue(response.body().getHits());
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Timber.e(t.toString());
            }
        });
    }
}
