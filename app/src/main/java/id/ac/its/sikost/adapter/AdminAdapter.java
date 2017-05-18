package id.ac.its.sikost.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Admin;

/**
 * Created by Ilham Aulia Majid on 18-May-17.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    Context context;
    List<Admin> admins;
    EditHapusInterface listener;
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

    public AdminAdapter(Context context, List<Admin> admins, EditHapusInterface listener) {
        this.context = context;
        this.admins = admins;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kamar_penghuni, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Admin admin = admins.get(position);
        final int index = position;

        holder.tvNomorAdmin.setText(String.valueOf(index + 1));
        holder.tvNamaAdmin.setText(admin.getNama());
        holder.tvUsernameAdmin.setText(admin.getUsername());
    }

    @Override
    public int getItemCount() {
        return admins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_kamar)
        TextView tvNomorAdmin;
        @BindView(R.id.tv_kapasitas_kamar)
        TextView tvNamaAdmin;
        @BindView(R.id.tv_biaya_kamar)
        TextView tvUsernameAdmin;
        @BindView(R.id.btn_edit)
        TextView btnEdit;
        @BindView(R.id.btn_hapus)
        TextView btnHapus;
        @BindView(R.id.cv_kamar)
        CardView cvAdmin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
