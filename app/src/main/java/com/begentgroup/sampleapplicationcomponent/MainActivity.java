package com.begentgroup.sampleapplicationcomponent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_MY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn_my);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra(MyActivity.EXTRA_NAME, "ysi");
                startActivityForResult(intent, RC_MY);
            }
        });

        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        btn = (Button)findViewById(R.id.btn_read_contacts);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readContacts();
            }
        });
    }

    private static final int RC_CONTACTS = 100;

    private void readContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                // UI Display
                // OK Permission Request...
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, RC_CONTACTS);
            }
            return;
        }

        // read contacts...
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_CONTACTS) {
            if (permissions == null || permissions.length == 0) {
                return;
            }
            if (permissions[0].equals(Manifest.permission.READ_CONTACTS) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts();
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_MY) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                Toast.makeText(this, "result : " + result , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "fail...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
