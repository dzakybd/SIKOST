package id.ac.its.sikost.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.model.Kamar;

public class KamarInfoActivity extends AppCompatActivity {

    @BindView(R.id.l_item)
    View itemKamar;
    @BindView(R.id.btn_edit)
    Button btnEditKamar;
    @BindView(R.id.btn_hapus)
    Button btnHapusKamar;

    TextView tvNamaKamar;
    TextView tvKapasitasKamar;
    TextView tvBiayaKamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_info);
        ButterKnife.bind(this);

        tvNamaKamar = (TextView) itemKamar.findViewById(R.id.tv_nama_kamar);
        tvKapasitasKamar = (TextView) itemKamar.findViewById(R.id.tv_kapasitas_kamar);
        tvBiayaKamar = (TextView) itemKamar.findViewById(R.id.tv_biaya_kamar);

        Bundle extras = getIntent().getExtras();
        Kamar kamar = (Kamar) extras.getSerializable("KAMAR");

        tvNamaKamar.setText(kamar.getNama());
        String kapasitas = String.format(getString(R.string.kapasitas), kamar.getTerisi(), kamar.getKapasitas());
        tvKapasitasKamar.setText(kapasitas);
        String biaya = String.format(getString(R.string.biaya), kamar.getBiaya(), kamar.getBiayaSatuan());
        tvBiayaKamar.setText(biaya);

        btnEditKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BTN", "edit");
            }
        });

        btnHapusKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BTN", "hapus");
            }
        });
    }

//    @OnClick({R.id.btn_edit, R.id.btn_hapus})
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.btn_edit:
//                Log.d("BTN", "edit");
//                break;
//            case R.id.btn_hapus:
//                Log.d("BTN", "hapus");
//                break;
//        }
//    }

}
