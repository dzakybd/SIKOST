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
import id.ac.its.sikost.model.Penghuni;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PenghuniAdapter extends RecyclerView.Adapter<PenghuniAdapter.ViewHolder> {

    Context context;
    List<Penghuni> penghunis;

    public PenghuniAdapter(Context context, List<Penghuni> penghunis) {
        this.penghunis = penghunis;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghuni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Penghuni penghuni = penghunis.get(position);
        String nama = String.format(context.getString(R.string.nama), penghuni.getNama());
        String ktp = String.format(context.getString(R.string.ktp), penghuni.getKtp());
        String ttl = String.format(context.getString(R.string.ttl), penghuni.getTtl());
        String kamar = String.format(context.getString(R.string.kamar), penghuni.getKamar());
        holder.tvNamaPenghuni.setText(nama);
        holder.tvKtpPenghuni.setText(ktp);
        holder.tvTtlPenghuni.setText(ttl);
        holder.tvKamarPenghuni.setText(kamar);
    }

    @Override
    public int getItemCount() {
        return penghunis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_nama_penghuni)
        TextView tvNamaPenghuni;
        @BindView(R.id.tv_ktp_penghuni)
        TextView tvKtpPenghuni;
        @BindView(R.id.tv_ttl_penghuni)
        TextView tvTtlPenghuni;
        @BindView(R.id.tv_kamar_penghuni)
        TextView tvKamarPenghuni;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(context, PenghuniInfoActivity.class);
//            Penghuni penghuni = penghunis.get(getAdapterPosition());
//            intent.putExtra("KAMAR", penghuni);
//            context.startActivity(intent);
        }
    }
}
