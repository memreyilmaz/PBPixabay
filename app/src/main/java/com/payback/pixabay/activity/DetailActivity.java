package com.payback.pixabay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.payback.pixabay.R;
import com.payback.pixabay.model.Hit;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.payback.pixabay.Config.SELECTED_IMAGE;

public class DetailActivity extends AppCompatActivity {
    Hit hit;
    Toolbar toolbar;
    ImageView bigImage;
    TextView imageTags;
    TextView imageLikesCount;
    TextView imageFavouritesCount;
    TextView imageCommentsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.detail_activity_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent detailIntent = getIntent();
        hit = detailIntent.getParcelableExtra(SELECTED_IMAGE);

        setTitle(hit.getUser());

        setUi();
    }

    private void setUi(){
        bigImage = findViewById(R.id.detail_imageview);
        imageTags = findViewById(R.id.image_tags_textView);
        imageLikesCount= findViewById(R.id.image_likes_textView);
        imageFavouritesCount = findViewById(R.id.image_favourites_textView);
        imageCommentsCount = findViewById(R.id.image_comments_textView);

        Picasso.with(this)
                .load(hit.getLargeImageURL())
                .placeholder(R.drawable.pixabay)
                .error(R.drawable.pixabay)
                .into(bigImage);

        imageTags.setText(hit.getTags().replace(","," \u2022"));
        imageLikesCount.setText(String.valueOf(hit.getLikes()));
        imageFavouritesCount.setText(String.valueOf(hit.getFavorites()));
        imageCommentsCount.setText(String.valueOf(hit.getComments()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_detail_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_share_icon:
                  shareCurrentImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareCurrentImage() {
        StringBuilder shareStringBuilder = new StringBuilder();
        shareStringBuilder.append(getResources().getString(R.string.share_image_headline))
                .append("\n")
                .append(hit.getPageURL());
        String imageToShare = shareStringBuilder.toString();

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageToShare);
        shareIntent.setType("text/plain");

        startActivity(Intent.createChooser(shareIntent,getResources().getText(R.string.share_with)));
    }
}