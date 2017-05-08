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
import id.ac.its.sikost.model.Kamar;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class KamarAdapter extends RecyclerView.Adapter<KamarAdapter.ViewHolder> {

    Context context;
    List<Kamar> kamars;
    EditHapusInterface listener;

    public KamarAdapter(Context context, List<Kamar> kamars, EditHapusInterface listener) {
        this.kamars = kamars;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamar_penghuni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kamar kamar = kamars.get(position);
        final int index = position;
        holder.tvNamaKamar.setText(kamar.getNama());
        String kapasitas = String.format(context.getString(R.string.kapasitas), kamar.getTerisi(), kamar.getKapasitas());
        holder.tvKapasitasKamar.setText(kapasitas);
        String biaya = String.format(context.getString(R.string.biaya), kamar.getBiaya(), kamar.getBiayaSatuan());
        holder.tvBiayaKamar.setText(biaya);
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
        img=new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_users)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvKapasitasKamar.setCompoundDrawables(img, null, null, null);
        img=new IconicsDrawable(context)
                .icon(FontAwesome.Icon.faw_money)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        holder.tvBiayaKamar.setCompoundDrawables(img, null, null, null);
    }

    @Override
    public int getItemCount() {
        return kamars.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_nama_kamar)
        TextView tvNamaKamar;
        @BindView(R.id.tv_kapasitas_kamar)
        TextView tvKapasitasKamar;
        @BindView(R.id.tv_biaya_kamar)
        TextView tvBiayaKamar;
        @BindView(R.id.btn_edit)
        TextView btnEdit;
        @BindView(R.id.btn_hapus)
        TextView btnHapus;
        @BindView(R.id.cv_kamar)
        CardView cvKamar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
