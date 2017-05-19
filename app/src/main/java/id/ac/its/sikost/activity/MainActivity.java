package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.pixplicity.easyprefs.library.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.datakamar)
    ImageView datakamar;
    @BindView(R.id.datapenghuni)
    ImageView datapenghuni;
    @BindView(R.id.pindahkamar)
    ImageView pindahkamar;
    @BindView(R.id.datapembayaran)
    ImageView datapembayaran;
    @BindView(R.id.keluar)
    Button keluar;
    @BindView(R.id.cardkamar)
    CardView cardkamar;
    @BindView(R.id.cardpenghuni)
    CardView cardpenghuni;
    @BindView(R.id.cardpindah)
    CardView cardpindah;
    @BindView(R.id.cardpembayaran)
    CardView cardpembayaran;
    @BindView(R.id.datapengeluaran)
    ImageView datapengeluaran;
    @BindView(R.id.cardpengeluaran)
    CardView cardpengeluaran;
    @BindView(R.id.dataadmin)
    ImageView dataadmin;
    @BindView(R.id.cardadmin)
    CardView cardadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        datakamar.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_bed)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar());
        datapenghuni.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_users)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar());
        pindahkamar.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_exchange)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar());
        datapembayaran.setImageDrawable(new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_money)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar());
    }

    @OnClick({R.id.keluar, R.id.cardkamar, R.id.cardpenghuni, R.id.cardpindah, R.id.cardpembayaran, R.id.cardadmin, R.id.cardpengeluaran})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardkamar:
                startActivity(new Intent(MainActivity.this, DataKamarActivity.class));
                break;
            case R.id.cardpenghuni:
                startActivity(new Intent(MainActivity.this, DataPenghuniActivity.class));
                break;
            case R.id.cardpindah:
                startActivity(new Intent(MainActivity.this, PindahKamarActivity.class));
                break;
            case R.id.cardpembayaran:
                startActivity(new Intent(MainActivity.this, DataPembayaranActivity.class));
                break;
            case R.id.cardpengeluaran:
                startActivity(new Intent(MainActivity.this, DataPembayaranActivity.class));
                break;
            case R.id.cardadmin:
                startActivity(new Intent(MainActivity.this, DataAdminActivity.class));
                break;
            case R.id.keluar:
                out();
                break;
        }
    }

    private void out() {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin keluar?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Prefs.clear();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        pilihan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alert = pilihan.create();
        alert.show();
    }

}
