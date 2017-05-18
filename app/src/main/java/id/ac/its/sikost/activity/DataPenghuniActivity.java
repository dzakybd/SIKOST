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
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.PenghuniAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

public class DataPenghuniActivity extends AppCompatActivity implements EditHapusInterface {


    @BindView(R.id.rv_penghuni)
    RecyclerView rvPenghuni;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;

    EditText et_nama;
    EditText et_ktp;
    EditText et_ttl;

    List<Penghuni> penghunis;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    PenghuniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penghuni);

        ButterKnife.bind(this);

        penghunis = PenghuniSingleton.getInstance().getPenghunis();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPenghuni.setLayoutManager(llm);
        rvPenghuni.setHasFixedSize(true);

        adapter = new PenghuniAdapter(this, penghunis, this);
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

    @Override
    public void edit(final int index) {
        Penghuni temp = penghunis.get(index);
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_penghuni, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_ktp = (EditText) alertview.findViewById(R.id.et_ktp);
        et_ttl = (EditText) alertview.findViewById(R.id.et_ttl);
        et_nama.setText(temp.getNama());
        et_ktp.setText(temp.getKtp());
        et_ttl.setText(temp.getTtl());
        result.setTitle("Edit Penghuni");
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                String ktp = et_ktp.getText().toString();
                String ttl = et_ttl.getText().toString();
                penghunis.get(index).setNama(nama);
                penghunis.get(index).setKtp(ktp);
                penghunis.get(index).setTtl(ttl);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
    }

    @Override
    public void hapus(final int index) {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin menghapus?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                penghunis.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        pilihan.setNegativeButton("Tidak", null);
        AlertDialog alert = pilihan.create();
        alert.show();
    }

    private void tambah() {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertView = getLayoutInflater().inflate(R.layout.dialog_data_penghuni, null);
        et_nama = (EditText) alertView.findViewById(R.id.et_nama);
        et_ktp = (EditText) alertView.findViewById(R.id.et_ktp);
        et_ttl = (EditText) alertView.findViewById(R.id.et_ttl);
        result.setTitle("Tambah Penghuni");
        result.setView(alertView)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama = et_nama.getText().toString();
                        String ktp = et_ktp.getText().toString();
                        String ttl = et_ttl.getText().toString();
                        Penghuni penghuni = new Penghuni(nama, ktp, ttl);
                        PenghuniSingleton.getInstance().addPenghuni(penghuni);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
    }
}
