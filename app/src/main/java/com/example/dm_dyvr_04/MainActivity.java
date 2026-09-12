package com.example.dm_dyvr_04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dm_dyvr_04.util.Basicas;

public class MainActivity extends AppCompatActivity {

    private EditText txtNum1, txtNum2;
    private TextView tvResult;

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

        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        tvResult = findViewById(R.id.tvResult);

        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);

        btnSumar.setOnClickListener(v -> calculate('+'));
        btnRestar.setOnClickListener(v -> calculate('-'));
        btnMultiplicar.setOnClickListener(v -> calculate('*'));
        btnDividir.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char operation) {
        String s1 = txtNum1.getText().toString();
        String s2 = txtNum2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double n1 = Double.parseDouble(s1);
            double n2 = Double.parseDouble(s2);
            double result = 0;

            switch (operation) {
                case '+':
                    result = Basicas.sumar(n1, n2);
                    break;
                case '-':
                    result = Basicas.restar(n1, n2);
                    break;
                case '*':
                    result = Basicas.multiplicar(n1, n2);
                    break;
                case '/':
                    if (n2 == 0) {
                        Toast.makeText(this, getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = Basicas.dividir(n1, n2);
                    break;
            }

            tvResult.setText(String.valueOf(result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
        }
    }
}
