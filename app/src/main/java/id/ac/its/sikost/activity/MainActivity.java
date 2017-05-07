package id.ac.its.sikost.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.ac.its.sikost.fragment.KamarListFragment;
import id.ac.its.sikost.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, PenghuniListActivity.class);
        startActivity(intent);
        finish();
    }
}
