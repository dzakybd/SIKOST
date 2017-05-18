package id.ac.its.sikost.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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

    EditText et_nama, et_username, et_password;
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
        Admin admin = admins.get(index);
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_admin, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_username = (EditText) alertview.findViewById(R.id.et_username);
        et_password = (EditText) alertview.findViewById(R.id.et_password);
        et_nama.setText(admin.getNama());
        et_username.setText(admin.getUsername());
        et_password.setText(admin.getPassword());
        result.setTitle("Edit Admin");
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                Admin admin = new Admin(nama, username, password);
                AdminSingleton.getInstance().addAdmin(admin);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
    }

    @Override
    public void hapus(final int index) {
        Log.d("HAPUS", "" + adapter.getItemCount());
        AlertDialog.Builder pilihan = new AlertDialog.Builder(this);
        if (adapter.getItemCount() == 1) {
            pilihan.setMessage("Tidak dapat menghapus. Jumlah minimal admin adalah 1.");
            pilihan.setPositiveButton("OK", null);
        } else {
            pilihan.setMessage("Anda ingin menghapus?");
            pilihan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    admins.remove(index);
                    adapter.notifyDataSetChanged();
                }
            });
            pilihan.setNegativeButton("Tidak", null);
        }
        AlertDialog alert = pilihan.create();
        alert.show();

    }

    private void tambah() {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_admin, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_username = (EditText) alertview.findViewById(R.id.et_username);
        et_password = (EditText) alertview.findViewById(R.id.et_password);
        result.setTitle("Tambah Admin");
        result.setView(alertview).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = et_nama.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                Admin admin = new Admin(nama, username, password);
                AdminSingleton.getInstance().addAdmin(admin);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Batal", null);
        AlertDialog dialog = result.create();
        dialog.show();
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

}
