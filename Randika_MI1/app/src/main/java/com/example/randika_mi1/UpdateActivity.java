package com.example.randika_mi1;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    private ApiEndpoint api;
    private EditText etName;
    private EditText etNumber;
    private MaterialButton mbUpdate;
    private ReadModel.Data contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        api = new ApiRetrofit().getEndpoint();
        contact = (ReadModel.Data) getIntent().getSerializableExtra("contact");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupView();
        setupListener();
    }

    private void setupView() {
        etName = findViewById(R.id.EditTextName);
        etNumber = findViewById(R.id.EditTextNumber);
        mbUpdate = findViewById(R.id.mbUpdate);

        if (contact != null) {
            etName.setText(contact.getName());
            etNumber.setText(contact.getNumber());
        }
    }

    private void setupListener() {
        mbUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String number = etNumber.getText().toString();

            if (!name.isEmpty() && !number.isEmpty()) {
                Log.i("PPM", "name: " + name);
                Log.i("PPM", "number: " + number);

                api.update(contact.getId(), name, number).enqueue(new Callback<SubmitModel>() {
                    @Override
                    public void onResponse(Call<SubmitModel> call, Response<SubmitModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<SubmitModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Name or Number cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
