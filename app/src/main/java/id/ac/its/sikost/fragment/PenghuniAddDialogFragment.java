package id.ac.its.sikost.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.activity.DataPenghuniActivity;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PenghuniAddDialogFragment extends DialogFragment {

    @BindView(R.id.et_nama)
    EditText etNama;
    @BindView(R.id.et_ktp)
    EditText etKtp;
    @BindView(R.id.et_ttl)
    EditText etTtl;
    @BindView(R.id.spn_kamar)
    Spinner spnKamar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_penghuni, null);
        ButterKnife.bind(this, view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.planets_array, R.layout.dialog_add_penghuni);
        List<Kamar> kamars = KamarSingleton.getInstance().getKamars();
//        for (Kamar kamar : kamars) {
//            adapter.add(kamar.getNama());
//        }
        spnKamar.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tambah Kamar")
                .setView(view)
                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama = etNama.getText().toString();
                        String ktp = etKtp.getText().toString();
                        String ttl = etTtl.getText().toString();
                        String kamar = spnKamar.getSelectedItem().toString();
                        Penghuni penghuni = new Penghuni(nama, ktp, ttl, kamar);
                        PenghuniSingleton.getInstance().addPenghuni(penghuni);
                        Intent intent = new Intent(getContext(), DataPenghuniActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                })
                .setNegativeButton("Batal", null);
        return builder.create();
    }
}
