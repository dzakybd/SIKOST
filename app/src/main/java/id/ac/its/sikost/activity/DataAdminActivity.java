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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.Utils;
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


        setTitle("Data Admin");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void edit(final int index) {
        final Admin admin = admins.get(index);
        final AlertDialog dialog = buildDialog("Edit Admin");
        et_nama.setText(admin.getNama());
        et_username.setText(admin.getUsername());
        et_password.setText(admin.getPassword());
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String nama = et_nama.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                admin.setNama(nama);
                admin.setUsername(username);
                admin.setPassword(password);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private void tambah() {
        final AlertDialog dialog = buildDialog("Tambah Admin");
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty()) return;
                String nama = et_nama.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                Admin admin = new Admin(nama, username, password);
                AdminSingleton.getInstance().addAdmin(admin);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    private AlertDialog buildDialog(String title) {
        AlertDialog.Builder result = new AlertDialog.Builder(this);
        View alertview = getLayoutInflater().inflate(R.layout.dialog_data_admin, null);
        et_nama = (EditText) alertview.findViewById(R.id.et_nama);
        et_username = (EditText) alertview.findViewById(R.id.et_username);
        et_password = (EditText) alertview.findViewById(R.id.et_password);
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
        editTexts.add(et_nama);
        editTexts.add(et_username);
        editTexts.add(et_password);
        return Utils.setEmptyErrorMessage(editTexts, "Wajib diisi");
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

    @OnClick(R.id.fab_add_admin)
    public void onViewClicked() {
        tambah();
    }
}
