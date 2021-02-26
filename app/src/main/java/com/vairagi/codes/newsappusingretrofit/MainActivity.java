package com.vairagi.codes.newsappusingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    ArrayList<Articles> articles;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articles = new ArrayList<>();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.articlesRecyclerView);
        recyclerView.setLayoutManager(layoutManager);
        progressBar = findViewById(R.id.spinner_loading);


        getTopHeadLines();

        newsAdapter = new NewsAdapter(this,articles);
        recyclerView.setAdapter(newsAdapter);
    }

    private void getTopHeadLines() {
        Call<TopHeadlines> topHeadlinesCall = NewsApi.getArticleService().getTopHeadLines();

        topHeadlinesCall.enqueue(new Callback<TopHeadlines>() {
            @Override
            public void onResponse(Call<TopHeadlines> call, Response<TopHeadlines> response) {
                if(response.isSuccessful()) {

                    TopHeadlines topHeadlines = response.body();
                    articles.addAll(topHeadlines.getArticles());
                    newsAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }

                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<TopHeadlines> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}