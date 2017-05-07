package id.ac.its.sikost.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.activity.KamarInfoActivity;
import id.ac.its.sikost.model.Kamar;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.ViewHolder> {

    Context context;
    List<Kamar> kamars;

    public KamarAdapter(Context context, List<Kamar> kamars) {
        this.kamars = kamars;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamar, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kamar kamar = kamars.get(position);
        holder.tvNamaKamar.setText(kamar.getNama());
        String kapasitas = String.format(context.getString(R.string.kapasitas), kamar.getTerisi(), kamar.getKapasitas());
        holder.tvKapasitasKamar.setText(kapasitas);
        String biaya = String.format(context.getString(R.string.biaya), kamar.getBiaya(), kamar.getBiayaSatuan());
        holder.tvBiayaKamar.setText(biaya);
    }

    @Override
    public int getItemCount() {
        return kamars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_nama_kamar)
        TextView tvNamaKamar;
        @BindView(R.id.tv_kapasitas_kamar)
        TextView tvKapasitasKamar;
        @BindView(R.id.tv_biaya_kamar)
        TextView tvBiayaKamar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, KamarInfoActivity.class);
            Kamar kamar = kamars.get(getAdapterPosition());
            intent.putExtra("KAMAR", kamar);
            context.startActivity(intent);
        }
    }
}
