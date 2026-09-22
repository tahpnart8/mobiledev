package com.example.lab7;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ArticleAdapter adapter;
    public TextView tvTitle;
    public TextView tvContent;
    public TextView tvView;
    public ImageView imgCover;

    public ArticleViewHolder(View item, ArticleAdapter adapter) {
        super(item);
        this.adapter = adapter;
        tvTitle = item.findViewById(R.id.tvTitle);
        tvContent = item.findViewById(R.id.tvContent);
        tvView = item.findViewById(R.id.tvView);
        imgCover = item.findViewById(R.id.imgCover);
        item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getBindingAdapterPosition();
        if (position == RecyclerView.NO_POSITION) {
            return;
        }

        // tang view tren chinh object trong danh sach roi cap nhat lai dong do
        Article article = adapter.getArticle(position);
        article.setView(article.getView() + 1);
        adapter.notifyItemChanged(position);

        Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
        intent.putExtra(ArticleDetailActivity.EXTRA_ARTICLE, article);
        v.getContext().startActivity(intent);
    }
}
