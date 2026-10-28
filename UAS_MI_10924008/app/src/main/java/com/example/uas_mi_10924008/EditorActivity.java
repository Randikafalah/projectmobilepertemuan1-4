package com.example.uas_mi_10924008;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {
    private EditText etNama, etEmail, etNoHp, etJurusan, etAlamat;
    private Button btnSimpan, btnHapus;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNoHp = findViewById(R.id.etNoHp);
        etJurusan = findViewById(R.id.etJurusan);
        etAlamat = findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnHapus = findViewById(R.id.btnHapus);

        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
            etNama.setText(getIntent().getStringExtra("nama"));
            etEmail.setText(getIntent().getStringExtra("email"));
            etNoHp.setText(getIntent().getStringExtra("nohp"));
            etJurusan.setText(getIntent().getStringExtra("jurusan"));
            etAlamat.setText(getIntent().getStringExtra("alamat"));

            btnHapus.setVisibility(View.VISIBLE);
        }

        btnSimpan.setOnClickListener(v -> {
            if (validateInputs()) {
                if (id.isEmpty()) {
                    tambahData();
                } else {
                    ubahData();
                }
            }
        });

        btnHapus.setOnClickListener(v -> hapusData());
    }

    private boolean validateInputs() {
        if (etNama.getText().toString().trim().isEmpty()) {
            etNama.setError("Nama tidak boleh kosong");
            return false;
        }
        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Email tidak boleh kosong");
            return false;
        }
        return true;
    }

    private void tambahData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.addMahasiswa(
                etNama.getText().toString(),
                etEmail.getText().toString(),
                etNoHp.getText().toString(),
                etJurusan.getText().toString(),
                etAlamat.getText().toString()
        ).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(EditorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("EditorActivity", "tambahData onFailure: " + t.getMessage(), t);
                Toast.makeText(EditorActivity.this, "Koneksi Gagal: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ubahData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.updateMahasiswa(
                id,
                etNama.getText().toString(),
                etEmail.getText().toString(),
                etNoHp.getText().toString(),
                etJurusan.getText().toString(),
                etAlamat.getText().toString()
        ).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(EditorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("EditorActivity", "ubahData onFailure: " + t.getMessage(), t);
                Toast.makeText(EditorActivity.this, "Koneksi Gagal: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hapusData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        apiService.deleteMahasiswa(id).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(EditorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("EditorActivity", "hapusData onFailure: " + t.getMessage(), t);
                Toast.makeText(EditorActivity.this, "Koneksi Gagal: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
