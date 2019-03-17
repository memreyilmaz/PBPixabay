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
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.payback.pixabay.Config.SELECTED_IMAGE;

public class DetailActivity extends AppCompatActivity {
    Hit hit;
    @BindView(R.id.detail_activity_toolbar) Toolbar toolbar;
    @BindView(R.id.detail_imageView) ImageView bigImage;
    @BindView(R.id.image_tags_textView) TextView imageTags;
    @BindView(R.id.image_likes_textView) TextView imageLikesCount;
    @BindView(R.id.image_favourites_textView) TextView imageFavouritesCount;
    @BindView(R.id.image_comments_textView) TextView imageCommentsCount;
    @BindString(R.string.share_image_headline) String shareString;
    @BindString(R.string.share_with) String shareWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

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
        Picasso.with(this)
                .load(hit.getLargeImageURL())
                .placeholder(R.drawable.pixabay)
                .error(R.drawable.pixabay)
                .into(bigImage);

        imageTags.setText(hit.getTags().replace(","," /"));
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
        shareStringBuilder.append(shareString)
                .append("\n")
                .append(hit.getPageURL());
        String imageToShare = shareStringBuilder.toString();

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageToShare);
        shareIntent.setType("text/plain");

        startActivity(Intent.createChooser(shareIntent, shareWith));
    }
}