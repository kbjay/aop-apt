package com.xiaoice.example.aop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaoice.example.apt_api.BindViewManager;
import com.xiaoice.example.aptannotation.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewManager.bind(this);
        if (mTv != null) {
            mTv.setText("hello bindView!");
        }
    }
}
