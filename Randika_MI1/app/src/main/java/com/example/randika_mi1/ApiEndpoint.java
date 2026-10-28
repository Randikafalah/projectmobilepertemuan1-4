package com.example.randika_mi1;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoint {
    @GET("tampilSemua.php")
    Call<ReadModel> data();

    @FormUrlEncoded
    @POST("tambah.php")
    Call<SubmitModel> create(
            @Field("name") String name,
            @Field("number") String number
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<SubmitModel> update(
            @Field("id") String id,
            @Field("name") String name,
            @Field("number") String number
    );

    @FormUrlEncoded
    @POST("hapus.php")
    Call<SubmitModel> delete(
            @Field("id") String id
    );
}
