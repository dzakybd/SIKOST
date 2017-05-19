package id.ac.its.sikost.activity;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.its.sikost.R;
import id.ac.its.sikost.Utils;
import id.ac.its.sikost.model.Admin;
import id.ac.its.sikost.model.AdminSingleton;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        if (Prefs.getBoolean("logged", false)) {
            move();
        }
    }

    private void move() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.login_button)
    public void onViewClicked() {
        Utils.setEmptyErrorMessage(loginUsername, "Username harus diisi.");
        Utils.setEmptyErrorMessage(loginPassword, "Password harus diisi");
        if(Utils.isEmpty(loginUsername) || Utils.isEmpty(loginPassword)){
            return;
        }
        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();
        List<Admin> admins = AdminSingleton.getInstance().getAdmins();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                Prefs.putBoolean("logged", true);
                move();
                return;
            }
        }
        Toast.makeText(this, "Username atau password yang anda masukkan salah. Mohon ulangi kembali.", Toast.LENGTH_LONG).show();
    }
}
