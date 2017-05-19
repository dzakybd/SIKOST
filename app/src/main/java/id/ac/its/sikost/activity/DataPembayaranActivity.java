package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
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
import id.ac.its.sikost.adapter.PembayaranAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;
import id.ac.its.sikost.model.Pembayaran;
import id.ac.its.sikost.model.PembayaranSingleton;

public class DataPembayaranActivity extends AppCompatActivity implements EditHapusInterface {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.spinner_kamar)
    Spinner spinnerKamar;
    @BindView(R.id.tv_rekap)
    TextView tvRekap;
    @BindView(R.id.rv_bayar)
    RecyclerView rvBayar;
    @BindView(R.id.fab_add_bayar)
    FloatingActionButton fabAddBayar;
    List<Pembayaran> pembayarans,pembayaran_kamar;
    List<Kamar> kamars;
    List<String> opsikamar;
    ArrayAdapter<String> adapter_kamar_spinner;
    PembayaranAdapter adapter;
    EditText et_nominal;

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
                .icon(FontAwesome.Icon.faw_money)
                .color(ResourcesCompat.getColor(getResources(), R.color.primary, null))
                .actionBar();
        img.setBounds(0, 0, 50, 50);
        tvRekap.setCompoundDrawables(img, null, null, null);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvBayar.setLayoutManager(llm);
        rvBayar.setHasFixedSize(true);
        opsikamar = new ArrayList<>();
        for (Kamar k : kamars) {
            opsikamar.add(k.getNama());
        }
        adapter_kamar_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opsikamar);
        adapter_kamar_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKamar.setAdapter(adapter_kamar_spinner);
        spinnerKamar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                showpembayaran();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        
    }
    
    private void showpembayaran() {
        pembayaran_kamar = new ArrayList<>();
        for (Pembayaran p : pembayarans){
            if(p.kamar.contentEquals(spinnerKamar.getSelectedItem().toString()))pembayaran_kamar.add(p);
        }
        adapter = new PembayaranAdapter(this, pembayaran_kamar, this);
        rvBayar.setAdapter(adapter);
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
        tambah();
    }


    private void tambah() {
        final AlertDialog dialog = buildDialog("Tambah Pembayaran");
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                int nominal = Integer.valueOf(et_nominal.getText().toString());
                SimpleDateFormat sdfdate = new SimpleDateFormat("dd-MM-yyyy");
                Date tanggal = new Date();
                Pembayaran pembayaran = new Pembayaran(spinnerKamar.getSelectedItem().toString(), Prefs.getString("admin", ""), sdfdate.format(tanggal), nominal);
                PembayaranSingleton.getInstance().addPembayaran(pembayaran);
                pembayaran_kamar.add(pembayaran);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void edit(final int index) {
        final Pembayaran pembayaran = pembayaran_kamar.get(index);
        final AlertDialog dialog = buildDialog("Edit Pembayaran");
        et_nominal.setText(String.valueOf(pembayaran.nominal));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                int nominal = Integer.valueOf(et_nominal.getText().toString());
                for(Pembayaran p :pembayarans){
                    if(p.kamar.contentEquals(pembayaran.kamar)&&p.tanggal.contentEquals(pembayaran.petugas)&&p.tanggal.contentEquals(pembayaran.tanggal))
                        p.nominal=nominal;
                }
                pembayaran.nominal = nominal;
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private AlertDialog buildDialog(String title) {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_pembayaran, null);
        et_nominal = (EditText) alertview.findViewById(R.id.et_nominal);
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
                final Pembayaran pembayaran = pembayaran_kamar.get(index);
                for(Pembayaran p :pembayarans){
                    if(p.kamar.contentEquals(pembayaran.kamar)&&p.tanggal.contentEquals(pembayaran.petugas)&&p.tanggal.contentEquals(pembayaran.tanggal))
                        pembayarans.remove(p);
                }
                pembayaran_kamar.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        pilihan.setNegativeButton("Tidak", null);
        AlertDialog alert = pilihan.create();
        alert.show();
    }
}
