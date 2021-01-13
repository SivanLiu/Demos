
package com.example.spinner;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.enums.PopupType;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.XPopupCallback;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private SpinnerAdapter spinnerAdapter;
    private Spinner mSpinner;
    private RelativeLayout ll_view;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = findViewById(R.id.spinner_choose_family);
        ll_view = findViewById(R.id.ll_view);
        recyclerView = findViewById(R.id.recyclerView);



        List<SpinnerBean> spinnerBeans = new ArrayList<>();
        SpinnerBean spinnerBean = new SpinnerBean();
        spinnerBean.setUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM40paJx3MJG6HOy0sk2LA2IH5u85EO4zurFB1ZNZgkb8pyWtCyiajaLG2ITZwNdomR1E5YMEqibJeFA/132");
        spinnerBean.setName("刘小芸刘刘小芸刘刘小芸刘");

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
        spinnerAdapter = new SpinnerAdapter(this, mSpinner, spinnerBeans);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sss", "position = " + position);
//                view.setBackgroundColor(Color.TRANSPARENT);
                spinnerAdapter.select(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AttachPopupView attachPopupView = new XPopup.Builder(this)
                .setPopupCallback(new XPopupCallback() {
                    @Override
                    public void onCreated(BasePopupView popupView) {

                    }

                    @Override
                    public void beforeShow(BasePopupView popupView) {

                    }

                    @Override
                    public void onShow(BasePopupView popupView) {

                    }

                    @Override
                    public void onDismiss(BasePopupView popupView) {

                    }

                    @Override
                    public void beforeDismiss(BasePopupView popupView) {

                    }

                    @Override
                    public boolean onBackPressed(BasePopupView popupView) {
                        return false;
                    }

                    @Override
                    public void onKeyBoardStateChanged(BasePopupView popupView, int height) {

                    }

                    @Override
                    public void onDrag(BasePopupView popupView, int value, float percent, boolean upOrLeft) {

                    }
                })
                .hasShadowBg(false)
                .isCenterHorizontal(true)
                .popupPosition(PopupPosition.Bottom)
                .popupType(PopupType.Bottom)
                .autoDismiss(true)
//                            .isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .isDarkTheme(true)
//                        .popupAnimation(PopupAnimation.ScaleAlphaFromCenter) //NoAnimation表示禁用动画
//                        .isCenterHorizontal(true) //是否与目标水平居中对齐
//                        .offsetY(-60)
//                        .offsetX(80)
//                        .popupPosition(PopupPosition.Top) //手动指定弹窗的位置
//                        .popupWidth(500)
                .atView(ll_view)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                .asAttachList(new String[]{"分享", "编辑", "不带icon", "分享"},
                        new int[]{R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round},
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                Log.e("ssss", "click " + text);
                            }
                        }, R.layout.ll_recyclerview, R.layout.family_bean_item_layout);

        ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachPopupView.show();
            }
        });
    }
}