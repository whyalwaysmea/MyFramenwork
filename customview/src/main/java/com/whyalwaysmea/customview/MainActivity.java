package com.whyalwaysmea.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CollapseView view1 = (CollapseView) findViewById(R.id.collapseview1);
        CollapseView view2 = (CollapseView) findViewById(R.id.collapseview2);
        CollapseView view3 = (CollapseView) findViewById(R.id.collapseview3);

        view1.setNumber("1");
        view1.setTitle("第一个");
        view1.setContentLayout(R.layout.expand_1);

        view2.setNumber("2");
        view2.setTitle("第2个");
        view2.setContentLayout(R.layout.expand_2);

        view3.setNumber("3");
        view3.setTitle("第3个");
        view3.setContentLayout(R.layout.expand_3);
    }
}
