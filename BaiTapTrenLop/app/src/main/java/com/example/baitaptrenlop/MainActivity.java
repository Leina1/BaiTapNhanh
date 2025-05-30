package com.example.baitaptrenlop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        EditText editInput =findViewById(R.id.editInput);
        TextView Output = findViewById(R.id.textOutput);
        Button btnInput = findViewById(R.id.btnInput);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editInput.getText().toString();
                if (!inputStr.isEmpty()) {
                    try {
                        double kg = Double.parseDouble(inputStr);
                        double pounds = kg * 2.20462;
                        String ketqua = kg + " kg = " + String.format("%.2f", pounds) + " pounds";
                        Output.setText(ketqua);
                    } catch (NumberFormatException e) {
                        Output.setText("Vui lòng nhập số hợp lệ!");
                    }
                } else {
                    Output.setText("Vui lòng nhập số kg!");
                }
            }
        });
    }
}