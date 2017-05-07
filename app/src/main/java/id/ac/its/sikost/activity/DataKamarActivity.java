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
import id.ac.its.sikost.EditHapusInterface;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.KamarAdapter;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;

public class DataKamarActivity extends AppCompatActivity implements EditHapusInterface {

    private KamarAdapter adapter;
    @BindView(R.id.rv_kamar)
    RecyclerView rvKamar;
    @BindView(R.id.fab_add_kamar)
    FloatingActionButton fabAddKamar;
    EditText et_nama, et_kapasitas, et_biaya;
    List<Kamar> kamars;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kamar);
        ButterKnife.bind(this);
        kamars = KamarSingleton.getInstance().getKamars();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvKamar.setLayoutManager(llm);
        rvKamar.setHasFixedSize(true);

        adapter = new KamarAdapter(this, kamars, this);
        rvKamar.setAdapter(adapter);

        fabAddKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });
        setTitle("Data Kamar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }


    private void tambah() {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_add_kamar, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_kapasitas = (EditText) alertview.findViewById(R.id.et_kapasitas);
        et_biaya = (EditText) alertview.findViewById(R.id.et_biaya);
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                int kapasitas = Integer.valueOf(et_kapasitas.getText().toString());
                int biaya = Integer.valueOf(et_biaya.getText().toString());
                Kamar kamar = new Kamar(nama, kapasitas, 0, biaya, "bulan");
                KamarSingleton.getInstance().addKamar(kamar);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
    }

    @Override
    public void edit(final int index) {
        Kamar temp = kamars.get(index);
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_add_kamar, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_kapasitas = (EditText) alertview.findViewById(R.id.et_kapasitas);
        et_biaya = (EditText) alertview.findViewById(R.id.et_biaya);
        et_nama.setText(temp.getNama());
        et_kapasitas.setText(String.valueOf(temp.getKapasitas()));
        et_biaya.setText(String.valueOf(temp.getBiaya()));
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                int kapasitas = Integer.valueOf(et_kapasitas.getText().toString());
                int biaya = Integer.valueOf(et_biaya.getText().toString());
                kamars.get(index).setBiaya(biaya);
                kamars.get(index).setKapasitas(kapasitas);
                kamars.get(index).setNama(nama);
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
                kamars.remove(index);
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
