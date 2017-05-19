package id.ac.its.sikost.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.KamarAdapter;
import id.ac.its.sikost.model.Pengeluaran;
import id.ac.its.sikost.model.PengeluaranSingleton;

public class DataPengeluaranActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_pengeluaran)
    RecyclerView rvPengeluaran;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;
    List<Pengeluaran> pengeluarans;
    KamarAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengeluaran);
        ButterKnife.bind(this);
        setTitle("Data Pembayaran");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        pengeluarans = PengeluaranSingleton.getInstance().getPengeluarans();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPengeluaran.setLayoutManager(llm);
        rvPengeluaran.setHasFixedSize(true);
//        adapter = new KamarAdapter(this, kamars, this);
//        rvKamar.setAdapter(adapter);

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

//    @OnClick(R.id.fab_add_penghuni)
//    public void onViewClicked() {
//        tambah();
//    }



//    private void tambah() {
//        final AlertDialog dialog = buildDialog("Tambah Kamar");
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isEmpty()) return;
//                String nama = et_nama.getText().toString();
//                int kapasitas = Integer.valueOf(et_kapasitas.getText().toString());
//                int biaya = Integer.valueOf(et_biaya.getText().toString());
//                Kamar kamar = new Kamar(nama, kapasitas, 0, biaya, "bulan");
//                KamarSingleton.getInstance().addKamar(kamar);
//                adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });
//    }
//
//    @Override
//    public void edit(final int index) {
//        final Kamar kamar = kamars.get(index);
//        final AlertDialog dialog = buildDialog("Edit Kamar");
//        et_nama.setText(kamar.getNama());
//        et_kapasitas.setText(String.valueOf(kamar.getKapasitas()));
//        et_biaya.setText(String.valueOf(kamar.getBiaya()));
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isEmpty()) return;
//                String nama = et_nama.getText().toString();
//                int kapasitas = Integer.valueOf(et_kapasitas.getText().toString());
//                int biaya = Integer.valueOf(et_biaya.getText().toString());
//                kamar.setNama(nama);
//                kamar.setKapasitas(kapasitas);
//                kamar.setBiaya(biaya);
//                adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });
//    }
//
//    private AlertDialog buildDialog(String title) {
//        AlertDialog.Builder result = new AlertDialog.Builder(this);
//        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_kamar, null);
//        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
//        et_kapasitas = (EditText) alertview.findViewById(R.id.et_kapasitas);
//        et_biaya = (EditText) alertview.findViewById(R.id.et_biaya);
//        result.setTitle(title)
//                .setView(alertview)
//                .setPositiveButton("Simpan", null)
//                .setNegativeButton("Batal", null);
//        AlertDialog dialog = result.create();
//        dialog.show();
//        return dialog;
//    }
//
//    private boolean isEmpty() {
//        List<EditText> editTexts = new ArrayList<>();
//        editTexts.add(et_nama);
//        editTexts.add(et_kapasitas);
//        editTexts.add(et_biaya);
//        return Utils.setEmptyErrorMessage(editTexts, "Wajib diisi");
//    }
//
//    @Override
//    public void hapus(final int index) {
//        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
//        pilihan.setMessage("Anda ingin menghapus?");
//        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                kamars.remove(index);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        pilihan.setNegativeButton("Tidak", null);
//        AlertDialog alert = pilihan.create();
//        alert.show();
//    }
}
