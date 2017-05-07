package id.ac.its.sikost.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.KamarAdapter;
import id.ac.its.sikost.fragment.KamarAddDialogFragment;
import id.ac.its.sikost.model.Kamar;
import id.ac.its.sikost.model.KamarSingleton;

public class DataKamarActivity extends AppCompatActivity {

    @BindView(R.id.rv_kamar)
    RecyclerView rvKamar;
    @BindView(R.id.fab_add_kamar)
    FloatingActionButton fabAddKamar;

    List<Kamar> kamars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_list);
        ButterKnife.bind(this);

        kamars = KamarSingleton.getInstance().getKamars();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvKamar.setLayoutManager(llm);
        rvKamar.setHasFixedSize(true);

        KamarAdapter adapter = new KamarAdapter(this, kamars);
        rvKamar.setAdapter(adapter);

        fabAddKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KamarAddDialogFragment dialogFragment = new KamarAddDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "add");
            }
        });
    }
}
