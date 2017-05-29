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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.Utils;
import id.ac.its.sikost.adapter.PenghuniAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

public class DataPenghuniActivity extends AppCompatActivity implements EditHapusInterface {


    @BindView(R.id.rv_penghuni)
    RecyclerView rvPenghuni;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;

    EditText et_nama, et_ktp, et_ttl, et_nohp;

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
    public void edit(final int index) {
        final Penghuni penghuni = penghunis.get(index);
        final AlertDialog dialog = buildDialog("Edit Penghuni");
        et_nama.setText(penghuni.getNama());
        et_ktp.setText(penghuni.getKtp());
        et_ttl.setText(penghuni.getTtl());
        et_nohp.setText(penghuni.getNohp());
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String nama = et_nama.getText().toString();
                String ktp = et_ktp.getText().toString();
                String ttl = et_ttl.getText().toString();
                String nohp = et_nohp.getText().toString();
                penghuni.setNama(nama);
                penghuni.setKtp(ktp);
                penghuni.setTtl(ttl);
                penghuni.setNohp(nohp);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void tambah() {
        final AlertDialog dialog = buildDialog("Tambah Penghuni");
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String nama = et_nama.getText().toString();
                String ktp = et_ktp.getText().toString();
                String ttl = et_ttl.getText().toString();
                String nohp = et_nohp.getText().toString();
                Penghuni penghuni = new Penghuni(nama, ktp, ttl, nohp);
                PenghuniSingleton.getInstance().addPenghuni(penghuni);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private AlertDialog buildDialog(String title) {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertView = getLayoutInflater().inflate(R.layout.dialog_data_penghuni, null);
        et_nama = (EditText) alertView.findViewById(R.id.et_nama);
        et_ktp = (EditText) alertView.findViewById(R.id.et_ktp);
        et_ttl = (EditText) alertView.findViewById(R.id.et_ttl);
        et_nohp = (EditText) alertView.findViewById(R.id.et_nohp);
        result.setTitle(title)
                .setView(alertView)
                .setPositiveButton("Simpan", null)
                .setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
        return dialog;
    }

    private boolean isEmpty() {
        List<EditText> editTexts = new ArrayList<>();
        editTexts.add(et_nama);
        editTexts.add(et_ktp);
        editTexts.add(et_ttl);
        editTexts.add(et_nohp);
        return Utils.setEmptyErrorMessage(editTexts, "Wajib diisi");
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
