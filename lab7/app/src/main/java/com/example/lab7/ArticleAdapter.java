package com.example.lab7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private final ArrayList<Article> articles;
    private final LayoutInflater inflater;

    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        this.inflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    public Article getArticle(int position) {
        return articles.get(position);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.tvTitle.setText(article.getTitle());
        holder.tvContent.setText(article.getContent());
        holder.tvView.setText(holder.itemView.getContext()
                .getString(R.string.views_format, article.getView()));
        holder.imgCover.setImageResource(article.getImgCover());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
