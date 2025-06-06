package com.example.baitap;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView mytextview = findViewById(R.id.textview);
        mytextview.setText("Đăng ký");

        EditText editTaiKhoan = findViewById(R.id.editTaiKhoan);
        EditText editMatKhau = findViewById(R.id.eidtMatKhau);
        TextView textViewResult = findViewById(R.id.textViewResult);
        Button myButton = findViewById(R.id.btnDangKy);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = editTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();
                String ketqua = "Tài khoản : " + taikhoan +"\nMật Khẩu : "+matkhau;
                textViewResult.setText(ketqua);
            }
        });
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = editTaiKhoan.getText().toString();
                String matkhau = editMatKhau.getText().toString();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("taikhoan", taikhoan);
                intent.putExtra("matkhau", matkhau);
                startActivity(intent);
            }
        });

    }
}