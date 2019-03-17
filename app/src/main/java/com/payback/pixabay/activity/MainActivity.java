package com.payback.pixabay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.payback.pixabay.ConnectionController;
import com.payback.pixabay.model.ImageViewModel;
import com.payback.pixabay.R;
import com.payback.pixabay.adapter.ImageAdapter;
import com.payback.pixabay.model.Hit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static com.payback.pixabay.Config.SEARCH_QUERY;
import static com.payback.pixabay.Config.SELECTED_IMAGE;

public class MainActivity extends AppCompatActivity {
    RecyclerView imageRecyclerView;
    private ImageAdapter mAdapter;
    AlertDialog.Builder builder;
    String searchQuery;
    TextView emptyView;
    ImageViewModel imageViewModel;
    Button retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUi();
        setRecyclerView();
        checkConnection();
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        if (savedInstanceState != null) {
            searchQuery = savedInstanceState.getString(SEARCH_QUERY);
        } else {
            searchQuery = getResources().getString(R.string.fruits);
            imageViewModel.loadImages(searchQuery);
        }

        imageViewModel.getImages().observe(this, hits -> {
            if (hits.size() != 0){
                mAdapter.setImageData(hits);
                mAdapter.notifyDataSetChanged();
                if (emptyView.isShown()){
                    emptyView.setVisibility(View.GONE);
                    imageRecyclerView.setVisibility(View.VISIBLE);
                }
            } else {
                String noResults = getResources().getString(R.string.no_results, searchQuery);
                emptyView.setText(noResults);
                emptyView.setVisibility(View.VISIBLE);
                imageRecyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void checkConnection(){
        if (!ConnectionController.isInternetAvailable(this)){
            imageRecyclerView.setVisibility(View.GONE);
            emptyView.setText(R.string.no_connection);
            emptyView.setVisibility(View.VISIBLE);
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    private void setUi(){
        emptyView = findViewById(R.id.error_empty_view);
        retryButton = findViewById(R.id.retry_connection_check_button);
        Toolbar toolbar = findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(toolbar);
        builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        retryButton.setOnClickListener(v -> {
            if (ConnectionController.isInternetAvailable(MainActivity.this)){
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    private void setRecyclerView(){
        imageRecyclerView = findViewById(R.id.image);
        imageRecyclerView.setHasFixedSize(true);
        mAdapter = new ImageAdapter();
        imageRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((v, position) -> showDetailActivity(position));

    }
    private void showDetailActivity(int position){
        builder.setMessage(R.string.dialog_message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_yes, (dialog, id) -> {
                    Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                    Hit selectedImage = mAdapter.getHitAtPosition(position);
                    detailIntent.putExtra(SELECTED_IMAGE,selectedImage);
                    startActivity(detailIntent);
                })
                .setNegativeButton(R.string.dialog_no, (dialog, id) -> dialog.cancel());

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
                searchQuery = query;
                imageViewModel.loadImages(searchQuery);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_QUERY, searchQuery);
    }
}