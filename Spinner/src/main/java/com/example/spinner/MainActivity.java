
package com.example.spinner;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SpinnerAdapter spinnerAdapter;
    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = findViewById(R.id.spinner_choose_family);

        List<SpinnerBean> spinnerBeans = new ArrayList<>();
        SpinnerBean spinnerBean = new SpinnerBean();
        spinnerBean.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean.setName("刘小芸");

        SpinnerBean spinnerBean1 = new SpinnerBean();
        spinnerBean1.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean1.setName("刘小");

        SpinnerBean spinnerBean2 = new SpinnerBean();
        spinnerBean2.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean2.setName("刘小芸1");

        SpinnerBean spinnerBean3 = new SpinnerBean();
        spinnerBean3.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean3.setName("刘小芸2");

        SpinnerBean spinnerBean4 = new SpinnerBean();
        spinnerBean4.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean4.setName("刘小芸3");

        spinnerBeans.add(spinnerBean);
        spinnerBeans.add(spinnerBean1);
        spinnerBeans.add(spinnerBean2);
        spinnerBeans.add(spinnerBean3);
        spinnerBeans.add(spinnerBean4);
        spinnerAdapter = new SpinnerAdapter(this, spinnerBeans);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sss", "position = " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}