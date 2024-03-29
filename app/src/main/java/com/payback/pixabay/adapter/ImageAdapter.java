package com.payback.pixabay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.payback.pixabay.R;
import com.payback.pixabay.model.Hit;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageAdapterViewHolder> {

    private List<Hit> mImages;
    private Context mContext;
    private static ClickListener clickListener;

    public ImageAdapter() {
    }

    public class ImageAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_imageView) ImageView posterImageView;
        @BindView(R.id.user_name_textView) TextView userNameTextView;
        @BindView(R.id.tag_textView) TextView tagsTextView;

        public ImageAdapterViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

        view.setOnClickListener(v -> clickListener.onItemClick(v, getAdapterPosition()));
        }
    }

    @Override
    public ImageAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_item, parent, false);

        return new ImageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapterViewHolder holder, int position) {

        Hit hit = mImages.get(position);
        holder.userNameTextView.setText(hit.getUser());
        holder.tagsTextView.setText(hit.getTags().replace(","," /"));
        Picasso.with(mContext)
                .load(hit.getPreviewURL())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mImages) return 0;
        return mImages.size();
    }

    public List<Hit> getImages () {
        return mImages;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setImageData(List<Hit> images) {
        mImages = images;
        notifyDataSetChanged();
    }

    public Hit getHitAtPosition(int position) {
        return mImages.get(position);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ImageAdapter.clickListener = clickListener;
    }
    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}