package com.vairagi.codes.newsappusingretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NewsApi {
    private static final String API_KEY="01932a8f1b6d49eb8c231a9926ba7790";
    private static final String URL = "https://newsapi.org/v2/top-headlines/";
    private static final String COUNTRY_CODE = "in";

    public static ArticleService  articleService = null;

    public static ArticleService getArticleService() {
        if(articleService==null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            articleService = retrofit.create(ArticleService.class);
        }
        return articleService;
    }



    public interface ArticleService {
        @GET("?country=" + COUNTRY_CODE + "&apiKey=" + API_KEY)
        Call<TopHeadlines> getTopHeadLines();

    }
}
