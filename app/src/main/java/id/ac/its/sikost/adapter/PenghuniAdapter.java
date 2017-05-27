package id.ac.its.sikost.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
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
        String nama = String.format(penghuni.getNama());
        String ktp = String.format(context.getString(R.string.ktp), penghuni.getKtp());
        String ttl = String.format(context.getString(R.string.ttl), penghuni.getTtl());
        String nohp = penghuni.getNohp();
        holder.tvNamaPenghuni.setText(nama);
        holder.tvKtpPenghuni.setText(ktp);
        holder.tvTtlPenghuni.setText(ttl);
        holder.tvTtlPenghuni.setText(ttl);
        holder.tvNoHp.setText(nohp);
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
        Drawable img;
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_credit_card)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvKtpPenghuni.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_calendar)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvTtlPenghuni.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_phone)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvNoHp.setCompoundDrawables(img, null, null, null);
    }

    @Override
    public int getItemCount() {
        return penghunis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama_kamar)
        TextView tvNamaPenghuni;
        @BindView(R.id.tv_ktp)
        TextView tvKtpPenghuni;
        @BindView(R.id.tv_ttl)
        TextView tvTtlPenghuni;
        @BindView(R.id.tv_no_hp)
        TextView tvNoHp;
        @BindView(R.id.btn_edit)
        TextView btnEdit;
        @BindView(R.id.btn_hapus)
        TextView btnHapus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
