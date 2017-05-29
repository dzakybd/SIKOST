package id.ac.its.sikost.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Pembayaran;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.ViewHolder> {

    Context context;
    List<Pembayaran> pembayarans;
    EditHapusInterface listener;


    public PembayaranAdapter(Context context, List<Pembayaran> pembayarans, EditHapusInterface listener) {
        this.pembayarans = pembayarans;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembayaran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pembayaran pembayaran = pembayarans.get(position);
        final int index = position;
        holder.tvKamarPembayaran.setText(pembayaran.kamar);
        holder.tvNominalPembayaran.setText(String.valueOf(pembayaran.nominal));
        holder.tvPetugasPembayaran.setText(pembayaran.petugas);
        holder.tvTanggalPembayaran.setText(pembayaran.tanggal);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.edit(index);
            }
        });
        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.hapus(index);
            }
        });

        Drawable img;
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_calendar)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvTanggalPembayaran.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_money)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvNominalPembayaran.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_user_secret)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvPetugasPembayaran.setCompoundDrawables(img, null, null, null);
    }

    @Override
    public int getItemCount() {
        return pembayarans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_kamar_pembayaran)
        TextView tvKamarPembayaran;
        @BindView(R.id.tv_tanggal_pembayaran)
        TextView tvTanggalPembayaran;
        @BindView(R.id.tv_nominal_pembayaran)
        TextView tvNominalPembayaran;
        @BindView(R.id.tv_petugas_pembayaran)
        TextView tvPetugasPembayaran;
        @BindView(R.id.btn_edit)
        TextView btnEdit;
        @BindView(R.id.btn_hapus)
        TextView btnHapus;
        @BindView(R.id.cv_pembayaran)
        CardView cvPembayaran;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
