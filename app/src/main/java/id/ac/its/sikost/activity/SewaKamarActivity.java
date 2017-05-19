package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
import id.ac.its.sikost.adapter.PenghuniPindahAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

public class SewaKamarActivity extends AppCompatActivity implements EditHapusInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.spinner_kamar)
    Spinner spinnerKamar;
    @BindView(R.id.tv_kapasitas_kamar)
    TextView tvKapasitasKamar;
    @BindView(R.id.rv_penghuni)
    RecyclerView rvPenghuni;
    @BindView(R.id.spinner_penghuni)
    Spinner spinnerPenghuni;
    @BindView(R.id.tambah)
    Button tambah;
    @BindView(R.id.viewtambah)
    LinearLayout viewtambah;
    List<Kamar> kamars;
    List<Penghuni> penghunis, penghuniset, penghuniunset;
    PenghuniPindahAdapter adapter;
    Kamar kamartemp;
    Penghuni penghunitemp;
    HashMap<String, Integer> kamarMap, penghuniMap;
    ArrayAdapter<String> adapter_kamar_spinner, adapter_penghuni_spinner;
    List<String> opsikamar, opsipenghuni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_kamar);
        ButterKnife.bind(this);
        setTitle("Sewa Kamar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        kamars = KamarSingleton.getInstance().getKamars();
        penghunis = PenghuniSingleton.getInstance().getPenghunis();

        Drawable img;
        img = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_users)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        tvKapasitasKamar.setCompoundDrawables(img, null, null, null);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPenghuni.setLayoutManager(llm);
        rvPenghuni.setHasFixedSize(true);
        opsikamar = new ArrayList<>();
        kamarMap = new HashMap<>();
        for (Kamar k : kamars) {
            opsikamar.add(k.getNama());
            kamarMap.put(k.getNama(), kamars.indexOf(k));
        }
        adapter_kamar_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opsikamar);
        adapter_kamar_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKamar.setAdapter(adapter_kamar_spinner);
        spinnerKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                kamartemp = kamars.get(kamarMap.get(spinnerKamar.getSelectedItem().toString()));
                showkapasitas();
                showpenghuni();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void showtambah() {
        opsipenghuni = new ArrayList<>();
        penghuniMap = new HashMap<>();
        for (Penghuni p : penghuniunset) {
            opsipenghuni.add(p.getNama());
            penghuniMap.put(p.getNama(), penghunis.indexOf(p));
        }
        adapter_penghuni_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opsipenghuni);
        adapter_penghuni_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPenghuni.setAdapter(adapter_penghuni_spinner);
        spinnerPenghuni.setVisibility(View.VISIBLE);
        tambah.setVisibility(View.VISIBLE);
    }

    private void showkapasitas() {
        String kapasitas = String.format(getString(R.string.kapasitas), kamartemp.getTerisi(), kamartemp.getKapasitas());
        tvKapasitasKamar.setText(kapasitas);
        tvKapasitasKamar.setVisibility(View.VISIBLE);
    }

    private void showpenghuni() {
        int counter = kamartemp.getTerisi();
        penghuniset = new ArrayList<>();
        penghuniunset = new ArrayList<>();
        for (Penghuni p : penghunis) {
            if (counter <= 0) penghuniunset.add(p);
            else penghuniset.add(p);
            counter--;
        }
        adapter = new PenghuniPindahAdapter(this, penghuniset, this);
        rvPenghuni.setAdapter(adapter);
        rvPenghuni.setVisibility(View.VISIBLE);
        if (kamartemp.getKapasitas() - kamartemp.getTerisi() > 0) {
            showtambah();
        } else {
            spinnerPenghuni.setVisibility(View.GONE);
            tambah.setVisibility(View.GONE);
        }
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

    @OnClick(R.id.tambah)
    public void onViewClicked() {
        penghunitemp = penghunis.get(penghuniMap.get(spinnerPenghuni.getSelectedItem().toString()));
        penghuniset.add(penghunitemp);
        adapter.notifyDataSetChanged();
        penghuniunset.remove(penghuniunset.indexOf(penghunitemp));
        opsipenghuni.remove(spinnerPenghuni.getSelectedItem());
        adapter_penghuni_spinner.notifyDataSetChanged();
    }

    @Override
    public void edit(int index) {

    }

    @Override
    public void hapus(final int index) {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin menghapus?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                penghuniset.remove(index);
                adapter.notifyDataSetChanged();
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
