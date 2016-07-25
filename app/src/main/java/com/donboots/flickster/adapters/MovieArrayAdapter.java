package com.donboots.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.donboots.flickster.R;
import com.donboots.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public int getItemViewType(int position) {
        return (getItem(position).getVoteAverage() > 6) ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        int type = getItemViewType(position);
        ViewHolder viewHolder;
        int orientation;
        String movieImage;

        if (convertView == null) {
            convertView = getInflatedLayoutForType(type);
            viewHolder = new ViewHolder();

            if (type == 1) {
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivBackdropImage);
            } else {
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        if (type == 1) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                    .placeholder(android.R.drawable.star_on)
                    .error(android.R.drawable.alert_light_frame)
                    .into(viewHolder.ivImage);
        } else {
            viewHolder.tvTitle.setText(movie.getOriginalTitle());
            viewHolder.tvOverview.setText(movie.getOverview());

            orientation = getContext().getResources().getConfiguration().orientation;
            movieImage = (orientation == Configuration.ORIENTATION_PORTRAIT) ? movie.getBackdropPath() : movie.getPosterPath();

            Picasso.with(getContext()).load(movieImage).fit().centerCrop()
                    .placeholder(android.R.drawable.star_on)
                    .error(android.R.drawable.alert_light_frame)
                    .into(viewHolder.ivImage);
        }

        return convertView;
    }

    private View getInflatedLayoutForType(int type) {
        if (type == 1) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie_popular, null);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        }
    }

    public static class ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }
}
