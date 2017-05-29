package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.Utils;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.JenisPengeluaranSingleton;

public class DataJenisPengeluaranActivity extends AppCompatActivity {

    List<String> jenisPengeluarans;

    @BindView(R.id.ll_jenis)
    LinearLayout llJenis;
    @BindView(R.id.fab_add_jenis_pengeluaran)
    FloatingActionButton fabAddJenisPengeluaran;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    EditText etJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_jenis_pengeluaran);
        ButterKnife.bind(this);
        jenisPengeluarans = JenisPengeluaranSingleton.getInstance().getJenisPengeluarans();
        for (String jenisPengeluaran : jenisPengeluarans) {
            addJenisPengeluaran(jenisPengeluaran);
        }

        setTitle("Data Jenis Pengeluaran");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void addJenisPengeluaran(final String jenisPengeluaran) {
        final RelativeLayout layout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.item_jenis_pengeluaran, null);
        final TextView tvJenisPengeluaran = (TextView) layout.findViewById(R.id.tv_jenis_pengeluaran);
        Button btnHapus = (Button) layout.findViewById(R.id.btn_hapus_jenis_pengeluaran);
        Button btnEdit = (Button) layout.findViewById(R.id.btn_edit_jenis_pengeluaran);
        tvJenisPengeluaran.setText(jenisPengeluaran);
        btnHapus.setOnClickListener(getHapusListener(jenisPengeluaran, layout));
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = buildDialog("Edit Jenis Pengeluaran", tvJenisPengeluaran.getText().toString());
                dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isEmpty()) return;
                        String baru = etJenis.getText().toString();
                        tvJenisPengeluaran.setText(baru);
                        int index = jenisPengeluarans.indexOf(jenisPengeluaran);
                        jenisPengeluarans.remove(jenisPengeluaran);
                        jenisPengeluarans.add(index, baru);
                        dialog.dismiss();
                    }
                });
            }
        });
        llJenis.addView(layout);
    }

    @NonNull
    private View.OnClickListener getHapusListener(final String jenisPengeluaran, final RelativeLayout layout) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DataJenisPengeluaranActivity.this);
                builder.setMessage("Anda ingin menghapus?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                jenisPengeluarans.remove(jenisPengeluaran);
                                llJenis.removeView(layout);
                            }
                        })
                        .setNegativeButton("Tidak", null);
                builder.show();
            }
        };
    }

    private AlertDialog buildDialog(String title, String jenis) {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_jenis_pengeluaran, null);
        etJenis = (EditText) alertview.findViewById(R.id.et_jenis);
        etJenis.setText(jenis);
        result.setTitle(title)
                .setView(alertview)
                .setPositiveButton("Simpan", null)
                .setNegativeButton("Batal", null);
        final AlertDialog dialog = result.create();
        dialog.show();
        return dialog;
    }

    private boolean isEmpty() {
        List<EditText> editTexts = new ArrayList<>();
        editTexts.add(etJenis);
        return Utils.setEmptyErrorMessage(editTexts, "Wajib diisi");
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

    @OnClick(R.id.fab_add_jenis_pengeluaran)
    public void onViewClicked() {
        final AlertDialog dialog = buildDialog("Tambah Jenis Pengeluaran", null);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String jenisPengeluaran = etJenis.getText().toString();
                jenisPengeluarans.add(jenisPengeluaran);
                addJenisPengeluaran(jenisPengeluaran);
                dialog.dismiss();
            }
        });
    }

}
