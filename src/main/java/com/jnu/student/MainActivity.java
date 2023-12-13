package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class YourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 替换成你的布局文件名

        // 找到 TextView 对象
        TextView helloWorldTextView = findViewById(R.id.text_view_hello_world);

        // 获取字符串资源并设置到 TextView 中
        String helloAndroidString = getResources().getString(R.string.hello_android);
        helloWorldTextView.setText(helloAndroidString);
    }
}
