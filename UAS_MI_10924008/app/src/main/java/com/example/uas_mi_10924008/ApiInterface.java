package com.example.uas_mi_10924008;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("tampil.php")
    Call<ResponseModel> getMahasiswa();

    @FormUrlEncoded
    @POST("tambah.php")
    Call<ResponseModel> addMahasiswa(
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("nohp") String nohp,
            @Field("jurusan") String jurusan,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("ubah.php")
    Call<ResponseModel> updateMahasiswa(
            @Field("id") String id,
            @Field("nama_lengkap") String nama,
            @Field("email") String email,
            @Field("nohp") String nohp,
            @Field("jurusan") String jurusan,
            @Field("alamat") String alamat
    );

    @FormUrlEncoded
    @POST("hapus.php")
    Call<ResponseModel> deleteMahasiswa(
            @Field("id") String id
    );
}
