package com.example.suli690.myandfix;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import org.w3c.dom.Text;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private final static String TAG =  MainActivity.class.getSimpleName();

    private EditText etAppVersion;
    private TextView tvSystemInfo;

    private RxPermissions rxPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxPermissions = new RxPermissions(this);

        etAppVersion = (EditText) findViewById(R.id.et_app_version);

        tvSystemInfo = (TextView) findViewById(R.id.tv_system_info);


        findViewById(R.id.btn_load_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxPermissions
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean granted) {
                                if (granted) {
                                    addPatch();

                                } else {
                                    Toast.makeText(getBaseContext(), "Require read storage permission!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

        findViewById(R.id.btn_remove_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApplication = (MyApplication) getApplicationContext();
                myApplication.removePatch();
            }
        });

        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        printSystemInfo();
    }

    private void addPatch() {
        String appVersion = etAppVersion.getText().toString();
        if (TextUtils.isEmpty(appVersion)) {
            Toast.makeText(getBaseContext(), "App version can't be empty!", Toast.LENGTH_LONG).show();
        } else {
            MyApplication myApplication = (MyApplication) getApplicationContext();
            if (myApplication.addPatch(appVersion)) {
                Toast.makeText(getBaseContext(), "Add patch successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), "Add patch failed!", Toast.LENGTH_LONG).show();

            }
        }
    }

    private void showToast() {
        Toast.makeText(this, "MainActivity private class", Toast.LENGTH_SHORT).show();

        Test test = new Test();
        test.printHotFixTest();
    }


    private void printSystemInfo() {
        String info = "Model:" + Build.MODEL
                + "\nBrand:" + Build.BRAND
                + "\nsdk:" + Build.VERSION.SDK_INT
                + "\nProduct:" + Build.PRODUCT
                + "\nDevice:" + Build.DEVICE
                + "\nDisplay:" + Build.DISPLAY
                + "\nHost:" + Build.HOST;
        tvSystemInfo.setText(info);
    }
}
