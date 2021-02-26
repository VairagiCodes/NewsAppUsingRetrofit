package com.vairagi.codes.newsappusingretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopHeadlines {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Articles> articles = null;


    public String getStatus() {
        return status;
    }



    public Integer getTotalResults() {
        return totalResults;
    }



    public List<Articles> getArticles() {
        return articles;
    }

}
