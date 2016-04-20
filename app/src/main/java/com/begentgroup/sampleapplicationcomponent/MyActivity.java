package com.begentgroup.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button btn = (Button)findViewById(R.id.btn_finish);
        Intent intent = getIntent();
        String value = intent.getStringExtra(EXTRA_NAME);
        Toast.makeText(this, "value : " + value , Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("result","aaa");
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
