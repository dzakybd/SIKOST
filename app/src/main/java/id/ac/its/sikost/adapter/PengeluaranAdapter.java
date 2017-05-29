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
import id.ac.its.sikost.model.Pengeluaran;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PengeluaranAdapter extends RecyclerView.Adapter<PengeluaranAdapter.ViewHolder> {

    Context context;
    List<Pengeluaran> pengeluarans;
    EditHapusInterface listener;


    public PengeluaranAdapter(Context context, List<Pengeluaran> pengeluarans, EditHapusInterface listener) {
        this.pengeluarans = pengeluarans;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengeluaran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pengeluaran pengeluaran = pengeluarans.get(position);
        final int index = position;
        holder.tvJudulPengeluaran.setText(pengeluaran.judul);
        holder.tvNominalPengeluaran.setText(String.valueOf(pengeluaran.nominal));
        holder.tvPetugasPengeluaran.setText(pengeluaran.petugas);
        holder.tvTanggalPengeluaran.setText(pengeluaran.tanggal);
        holder.tvJenisPengeluaran.setText(pengeluaran.jenis);
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
        holder.tvTanggalPengeluaran.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_money)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvNominalPengeluaran.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_user_secret)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvPetugasPengeluaran.setCompoundDrawables(img, null, null, null);
        img = new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_list_ul)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvJenisPengeluaran.setCompoundDrawables(img, null, null, null);
    }

    @Override
    public int getItemCount() {
        return pengeluarans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul_pengeluaran)
        TextView tvJudulPengeluaran;
        @BindView(R.id.tv_tanggal_pengeluaran)
        TextView tvTanggalPengeluaran;
        @BindView(R.id.tv_nominal_pengeluaran)
        TextView tvNominalPengeluaran;
        @BindView(R.id.tv_petugas_pengeluaran)
        TextView tvPetugasPengeluaran;
        @BindView(R.id.tv_jenis_pengeluaran)
        TextView tvJenisPengeluaran;
        @BindView(R.id.btn_edit)
        TextView btnEdit;
        @BindView(R.id.btn_hapus)
        TextView btnHapus;
        @BindView(R.id.cv_pengeluaran)
        CardView cvPengeluaran;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
