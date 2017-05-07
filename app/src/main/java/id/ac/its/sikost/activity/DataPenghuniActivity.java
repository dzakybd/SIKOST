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
import id.ac.its.sikost.adapter.PenghuniAdapter;
import id.ac.its.sikost.fragment.PenghuniAddDialogFragment;
import id.ac.its.sikost.model.Penghuni;
import id.ac.its.sikost.model.PenghuniSingleton;

public class DataPenghuniActivity extends AppCompatActivity {


    @BindView(R.id.rv_penghuni)
    RecyclerView rvPenghuni;
    @BindView(R.id.fab_add_penghuni)
    FloatingActionButton fabAddPenghuni;

    List<Penghuni> penghunis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penghuni_list);

        ButterKnife.bind(this);

        penghunis = PenghuniSingleton.getInstance().getPenghunis();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvPenghuni.setLayoutManager(llm);
        rvPenghuni.setHasFixedSize(true);

        PenghuniAdapter adapter = new PenghuniAdapter(this, penghunis);
        rvPenghuni.setAdapter(adapter);

        fabAddPenghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PenghuniAddDialogFragment dialogFragment = new PenghuniAddDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "add");
            }
        });
    }
}
