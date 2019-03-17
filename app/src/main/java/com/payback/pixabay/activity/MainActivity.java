package com.payback.pixabay.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.payback.pixabay.ConnectionController;
import com.payback.pixabay.ImageViewModel;
import com.payback.pixabay.R;
import com.payback.pixabay.adapter.ImageAdapter;
import com.payback.pixabay.model.Hit;
import com.payback.pixabay.rest.PixabayApiInterface;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static com.payback.pixabay.Config.SELECTED_IMAGE;

public class MainActivity extends AppCompatActivity {
    RecyclerView imageRecyclerView;
   // StaggeredGridLayoutManager layoutManager;
    PixabayApiInterface apiService;
    private ImageAdapter mAdapter;
    private List<Hit> images;
    AlertDialog.Builder builder;
    String searchquery = "fruits";
    TextView emptyView;
    ImageViewModel imageViewModel;
    String fruits = "fruits";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyView = findViewById(R.id.error_empty_view);
        imageRecyclerView = findViewById(R.id.image);
        if (!ConnectionController.isInternetAvailable(this)){
            imageRecyclerView.setVisibility(View.GONE);
            emptyView.setText(R.string.no_connection);
            emptyView.setVisibility(View.VISIBLE);
        }
        Toolbar toolbar = findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);

       // layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
       // imageRecyclerView.setLayoutManager(layoutManager);
        imageRecyclerView.setHasFixedSize(true);
        mAdapter = new ImageAdapter();
        imageRecyclerView.setAdapter(mAdapter);
        builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);

        mAdapter.setOnItemClickListener(new ImageAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                showDetailActivity(position);
            }
        });

        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        getImages(fruits);

       /* imageViewModel.getImages(searchquery).observe(this, new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {
                mAdapter.setImageData(hits);
                mAdapter.notifyDataSetChanged();
            }
        });*/
    }

    private void getImages(String searchquery){
        //todo clear list
        imageViewModel.getImages(searchquery).observe(this, new Observer<List<Hit>>() {
            @Override
            public void onChanged(List<Hit> hits) {
                mAdapter.setImageData(hits);
                mAdapter.notifyDataSetChanged();
            }
        });
       /* apiService = PixabayApiClient.getClient().create(PixabayApiInterface.class);
        String photo = "photo";

        Call<ImageResponse> call = apiService.getSearched(searchquery, photo);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                Timber.i("Request Url: %s", call.request().url().toString());
                Timber.i("Response code: %s", response.code());
                int statusCode = response.code();

                if (response.body().getHits() != null && response.body().getTotalHits() != 0 ) {
                    images = response.body().getHits();
                    mAdapter.setImageData(images);
                    mAdapter.notifyDataSetChanged();
                } else if (statusCode == 429){
                    emptyView.setText(R.string.to_many_requests);
                    emptyView.setVisibility(View.VISIBLE);
                    imageRecyclerView.setVisibility(View.GONE);
                } else {
                    imageRecyclerView.setVisibility(View.GONE);
                    Resources res = getResources();
                    String text = res.getString(R.string.no_results, searchquery);
                    emptyView.setText(text);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Timber.e(t.toString());
            }
        });*/
    }

    private void showDetailActivity(int position){
        builder.setMessage(R.string.dialog_message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                        Hit selectedHit = images.get(position);
                        detailIntent.putExtra(SELECTED_IMAGE,selectedHit);
                        startActivity(detailIntent);
                    }
                })
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_action, menu);
        MenuItem searchItem = menu.findItem(R.id.action_bar_search_icon);
        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchItem.collapseActionView();
                searchquery = query;
                getImages(searchquery);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
        searchView.setOnQueryTextListener(searchListener);
        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}