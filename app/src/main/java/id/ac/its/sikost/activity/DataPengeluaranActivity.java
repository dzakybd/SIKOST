package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.Utils;
import id.ac.its.sikost.adapter.PengeluaranAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.JenisPengeluaranSingleton;
import id.ac.its.sikost.model.Pengeluaran;
import id.ac.its.sikost.model.PengeluaranSingleton;

public class DataPengeluaranActivity extends AppCompatActivity implements EditHapusInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_pengeluaran)
    RecyclerView rvPengeluaran;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;
    List<Pengeluaran> pengeluarans;
    PengeluaranAdapter adapter;
    EditText et_nominal, et_judul;
    Spinner spJenis;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    int total = 0;
    List<String> jenisPengeluarans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengeluaran);
        ButterKnife.bind(this);
        setTitle("Data Pengeluaran");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        pengeluarans = PengeluaranSingleton.getInstance().getPengeluarans();
        for (Pengeluaran p : pengeluarans) {
            total += p.nominal;
        }
        tvTotal.setText("Total : " + String.valueOf(total));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPengeluaran.setLayoutManager(llm);
        rvPengeluaran.setHasFixedSize(true);
        adapter = new PengeluaranAdapter(this, pengeluarans, this);
        rvPengeluaran.setAdapter(adapter);
        jenisPengeluarans = JenisPengeluaranSingleton.getInstance().getJenisPengeluarans();
    }

    @OnClick(R.id.fab_add_penghuni)
    public void onViewClicked() {
        tambah();
    }


    private void tambah() {
        final AlertDialog dialog = buildDialog("Tambah Pengeluaran");
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String judul = et_judul.getText().toString();
                int nominal = Integer.valueOf(et_nominal.getText().toString());
                String jenis = spJenis.getSelectedItem().toString();
                total += nominal;
                tvTotal.setText("Total : " + String.valueOf(total));
                SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
                Date tanggal = new Date();
                Pengeluaran pengeluaran = new Pengeluaran(judul, Prefs.getString("admin", ""), sdfdate.format(tanggal), nominal, jenis);
                PengeluaranSingleton.getInstance().addPengeluaran(pengeluaran);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void edit(final int index) {
        final Pengeluaran pengeluaran = pengeluarans.get(index);
        final AlertDialog dialog = buildDialog("Edit Pengeluaran");
        et_judul.setText(pengeluaran.judul);
        et_nominal.setText(String.valueOf(pengeluaran.nominal));
        for (int i = 0; i < spJenis.getCount(); i++) {
            if (spJenis.getItemAtPosition(i).toString().equals(pengeluaran.jenis)) {
                spJenis.setSelection(i);
                break;
            }
        }
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String judul = et_judul.getText().toString();
                int nominal = Integer.valueOf(et_nominal.getText().toString());
                String jenis = spJenis.getSelectedItem().toString();
                total -= pengeluaran.nominal;
                total += nominal;
                tvTotal.setText("Total : " + String.valueOf(total));
                pengeluaran.judul = judul;
                pengeluaran.nominal = nominal;
                pengeluaran.jenis = jenis;
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private AlertDialog buildDialog(String title) {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_pengeluaran, null);
        et_judul = (EditText) alertview.findViewById(R.id.et_judul);
        et_nominal = (EditText) alertview.findViewById(R.id.et_nominal);
        spJenis = (Spinner) alertview.findViewById(R.id.spinner_jenis);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jenisPengeluarans);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenis.setAdapter(spinnerAdapter);
        result.setTitle(title)
                .setView(alertview)
                .setPositiveButton("Simpan", null)
                .setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
        return dialog;
    }

    private boolean isEmpty() {
        List<EditText> editTexts = new ArrayList<>();
        editTexts.add(et_judul);
        editTexts.add(et_nominal);
        return Utils.setEmptyErrorMessage(editTexts, "Wajib diisi");
    }

    @Override
    public void hapus(final int index) {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin menghapus?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                total -= pengeluarans.get(index).nominal;
                tvTotal.setText("Total : " + String.valueOf(total));
                pengeluarans.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        pilihan.setNegativeButton("Tidak", null);
        AlertDialog alert = pilihan.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jenis_pengeluaran, menu);
        menu.getItem(0).setIcon(R.drawable.ic_settings_white_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_jenis_pengeluaran:
                startActivity(new Intent(this, DataJenisPengeluaranActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
