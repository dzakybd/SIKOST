package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.its.sikost.R;
import id.ac.its.sikost.adapter.AdminAdapter;
import id.ac.its.sikost.interfaces.EditHapusInterface;
import id.ac.its.sikost.model.Admin;
import id.ac.its.sikost.model.AdminSingleton;

public class DataAdminActivity extends AppCompatActivity implements EditHapusInterface {

    AdminAdapter adapter;
    List<Admin> admins;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_admin)
    RecyclerView rvAdmin;
    @BindView(R.id.fab_add_admin)
    FloatingActionButton fabAddAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_admin);
        ButterKnife.bind(this);
        admins = AdminSingleton.getInstance().getAdmins();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvAdmin.setLayoutManager(llm);
        rvAdmin.setHasFixedSize(true);
        adapter = new AdminAdapter(this, admins, this);
        rvAdmin.setAdapter(adapter);

        fabAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambah();
            }
        });
        setTitle("Data Admin");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void edit(final int index) {

    }

    @Override
    public void hapus(final int index) {
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        pilihan.setMessage("Anda ingin menghapus?");
        pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                admins.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        pilihan.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alert = pilihan.create();
        alert.show();

    }

    private void tambah(){

    }
}
