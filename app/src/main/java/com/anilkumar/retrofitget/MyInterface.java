package com.anilkumar.retrofitget;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyInterface {
//    https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<List<PostModal>>getModal();
}
