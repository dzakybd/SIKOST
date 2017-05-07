package id.ac.its.sikost.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.EditHapusInterface;
import id.ac.its.sikost.R;
import id.ac.its.sikost.model.Penghuni;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PenghuniAdapter extends RecyclerView.Adapter<PenghuniAdapter.ViewHolder> {

    Context context;
    List<Penghuni> penghunis;
    EditHapusInterface listener;

    public PenghuniAdapter(Context context, List<Penghuni> penghunis, EditHapusInterface listener) {
        this.penghunis = penghunis;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penghuni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Penghuni penghuni = penghunis.get(position);
        String nama = String.format(context.getString(R.string.nama), penghuni.getNama());
        String ktp = String.format(context.getString(R.string.ktp), penghuni.getKtp());
        String ttl = String.format(context.getString(R.string.ttl), penghuni.getTtl());
        String kamar = String.format(context.getString(R.string.kamar), penghuni.getKamar());
        holder.tvNamaPenghuni.setText(nama);
        holder.tvKtpPenghuni.setText(ktp);
        holder.tvTtlPenghuni.setText(ttl);
        holder.tvKamarPenghuni.setText(kamar);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.edit(position);
            }
        });
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.hapus(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return penghunis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_penghuni)
        TextView tvNamaPenghuni;
        @BindView(R.id.tv_ktp_penghuni)
        TextView tvKtpPenghuni;
        @BindView(R.id.tv_ttl_penghuni)
        TextView tvTtlPenghuni;
        @BindView(R.id.tv_kamar_penghuni)
        TextView tvKamarPenghuni;
        @BindView(R.id.btn_edit_penghuni)
        TextView btnEdit;
        @BindView(R.id.btn_hapus_penghuni)
        TextView btnHapus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
