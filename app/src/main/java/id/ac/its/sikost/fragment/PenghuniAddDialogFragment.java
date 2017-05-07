package id.ac.its.sikost.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import butterknife.ButterKnife;
import id.ac.its.sikost.R;

/**
 * Created by Ilham Aulia Majid on 07-May-17.
 */

public class PenghuniAddDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_kamar, null);
        ButterKnife.bind(this, view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Tambah Kamar")
//                .setView(view)
//                .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String nama = etNamaKamar.getText().toString();
//                        int kapasitas = Integer.valueOf(etKapaistas.getText().toString());
//                        int biaya = Integer.valueOf(etBiaya.getText().toString());
//                        Kamar kamar = new Kamar(nama, kapasitas, 0, biaya, "bulan");
//                        KamarSingleton.getInstance().addKamar(kamar);
//                        Intent intent = new Intent(getContext(), DataKamarActivity.class);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }
//                })
//                .setNegativeButton("Batal", null);
        return builder.create();
    }

}
