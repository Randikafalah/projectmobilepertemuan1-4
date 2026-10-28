package com.example.uas_mi_10924008;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private Context context;
    private List<MahasiswaModel> list;

    public MahasiswaAdapter(Context context, List<MahasiswaModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MahasiswaModel data = list.get(position);
        holder.tvNama.setText(data.getNamaLengkap());
        holder.tvEmail.setText(data.getEmail());
        holder.tvJurusan.setText(data.getJurusan());

        // Klik item untuk Mengubah / Menghapus Data
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditorActivity.class);
            intent.putExtra("id", data.getId());
            intent.putExtra("nama", data.getNamaLengkap());
            intent.putExtra("email", data.getEmail());
            intent.putExtra("nohp", data.getNohp());
            intent.putExtra("jurusan", data.getJurusan());
            intent.putExtra("alamat", data.getAlamat());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvEmail, tvJurusan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvJurusan = itemView.findViewById(R.id.tvJurusan);
        }
    }
}
