package com.example.testapp;

//import com.google.code.regexp.Matcher;
//import com.google.code.regexp.Pattern;

import android.os.Build;
import android.os.Bundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String regex = "(?<province>[^省]+省|.+自治区|[^市]+市|内蒙古|广西|西藏|宁夏|新疆|澳门|香港)" +
                "(?<city>[^自治州]+自治州|[^市]+市|[^盟]+盟|[^地区]+地区|.+区划|其他|澳门|香港)" +
                "(?<county>[^市]+市|[^县]+县|[^旗]+旗|.+区|其他)?(?<village>.*)";

        String regex2 = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";

        Matcher m = Pattern.compile(regex).matcher("浙江省杭州市余杭区仓前街道海曙路128号");
        while (m.find()) {
            String province = m.group("province");
            System.out.println("sss province = " + province == null ? "" : province.trim());

            String city = m.group("city");
            System.out.println("sss city = " + city == null ? "" : city.trim());

            String county = m.group("county");
            System.out.println("sss county = " + county == null ? "" : county.trim());

            String village = m.group("village");
            System.out.println("sss village = " + village == null ? "" : village.trim());
        }
    }
}