package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.EditHapusInterface;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.PenghuniAdapter;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

public class DataPenghuniActivity extends AppCompatActivity implements EditHapusInterface {

    PenghuniAdapter adapter;
    @BindView(R.id.rv_penghuni)
    RecyclerView rvPenghuni;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;

    List<Penghuni> penghunis;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    EditText et_nama,et_ktp,et_ttl;
    Spinner spn_kamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penghuni);

        ButterKnife.bind(this);

        penghunis = PenghuniSingleton.getInstance().getPenghunis();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPenghuni.setLayoutManager(llm);
        rvPenghuni.setHasFixedSize(true);

        adapter = new PenghuniAdapter(this, penghunis,this);
        rvPenghuni.setAdapter(adapter);

        fabAddPenghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });
        setTitle("Data Penghuni");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }
    private void tambah(){
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_add_penghuni,null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_ktp = (EditText) alertview.findViewById(R.id.et_ktp);
        et_ttl = (EditText) alertview.findViewById(R.id.et_ttl);
        spn_kamar = (Spinner) alertview.findViewById(R.id.spn_kamar);
        List<String> categories = new ArrayList<>();
        List<Kamar> kamars = KamarSingleton.getInstance().getKamars();
        for (Kamar kamar : kamars) {
            categories.add(kamar.getNama());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_kamar.setAdapter(dataAdapter);
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                String ktp = et_ktp.getText().toString();
                String ttl = et_ttl.getText().toString();
                String kamar = spn_kamar.getSelectedItem().toString();
                Penghuni penghuni = new Penghuni(nama, ktp, ttl, kamar);
                PenghuniSingleton.getInstance().addPenghuni(penghuni);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog=result.create();
        dialog.show();
    }

    @Override
    public void edit(final int index){
        Penghuni temp = penghunis.get(index);
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_add_penghuni,null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_ktp = (EditText) alertview.findViewById(R.id.et_ktp);
        et_ttl = (EditText) alertview.findViewById(R.id.et_ttl);
        spn_kamar = (Spinner) alertview.findViewById(R.id.spn_kamar);
        et_nama.setText(temp.getNama());
        et_ktp.setText(temp.getKtp());
        et_ttl.setText(temp.getTtl());
        List<String> categories = new ArrayList<>();
        List<Kamar> kamars = KamarSingleton.getInstance().getKamars();
        for (Kamar kamar : kamars) {
            categories.add(kamar.getNama());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_kamar.setAdapter(dataAdapter);
        spn_kamar.setSelection(index);
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                String ktp = et_ktp.getText().toString();
                String ttl = et_ttl.getText().toString();
                String kamar = spn_kamar.getSelectedItem().toString();
                penghunis.get(index).setNama(nama);
                penghunis.get(index).setKtp(ktp);
                penghunis.get(index).setTtl(ttl);
                penghunis.get(index).setKamar(kamar);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog=result.create();
        dialog.show();
    }

    @Override
    public void hapus(final int index){
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin menghapus?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                penghunis.remove(index);
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
}
