package id.ac.its.sikost.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.wang.avi.AVLoadingIndicatorView;

import id.ac.its.sikost.R;

public class Splashscreen extends AppCompatActivity {
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                avi.hide();
                Intent i = new Intent(Splashscreen.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
