package id.ac.its.sikost.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;
import id.ac.its.sikost.model.Pembayaran;
import id.ac.its.sikost.model.PembayaranSingleton;

public class DataPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.spinner_kamar)
    Spinner spinnerPembayaran;
    @BindView(R.id.tv_rekap)
    TextView tvRekap;
    @BindView(R.id.rv_bayar)
    RecyclerView rvBayar;
    @BindView(R.id.fab_add_bayar)
    FloatingActionButton fabAddBayar;
    List<Pembayaran> pembayarans;
    List<Kamar> kamars;
    List<String> opsikamar;
    Kamar kamartemp;
    HashMap<String, Integer> kamarMap;
    ArrayAdapter<String> adapter_kamar_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pembayaran);
        ButterKnife.bind(this);
        setTitle("Data Pembayaran");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        kamars = KamarSingleton.getInstance().getKamars();
        pembayarans = PembayaranSingleton.getInstance().getPembayarans();

        Drawable img;
        img = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_users)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        tvRekap.setCompoundDrawables(img, null, null, null);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvBayar.setLayoutManager(llm);
        rvBayar.setHasFixedSize(true);
        opsikamar = new ArrayList<>();
        kamarMap = new HashMap<>();
        for (Kamar k : kamars) {
            opsikamar.add(k.getNama());
            kamarMap.put(k.getNama(), kamars.indexOf(k));
        }
        adapter_kamar_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opsikamar);
        adapter_kamar_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPembayaran.setAdapter(adapter_kamar_spinner);
        spinnerPembayaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                kamartemp = kamars.get(kamarMap.get(spinnerPembayaran.getSelectedItem().toString()));
                showpembayaran();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void showpembayaran() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab_add_bayar)
    public void onViewClicked() {
    }
}
