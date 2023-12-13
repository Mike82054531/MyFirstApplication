package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewJNU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHello = findViewById(R.id.textViewHello);
        textViewJNU = findViewById(R.id.textViewJNU);

        Button buttonChangeText = findViewById(R.id.buttonChangeText);
        buttonChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 交换两个TextView的文本
                String tempText = textViewHello.getText().toString();
                textViewHello.setText(textViewJNU.getText());
                textViewJNU.setText(tempText);

                // 显示Toast
                Toast.makeText(MainActivity.this, "交换成功", Toast.LENGTH_SHORT).show();

                // 显示AlertDialog
                showAlertDialog("交换成功");
            }
        });
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }
}

