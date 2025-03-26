package com.example.ConverterOfElectrik;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AmperActivity extends AppCompatActivity {

    private EditText editCosA;
    private EditText editP;
    private EditText editVoltA;
    private Button button_calc_a;
    private Button button_resetA;
    private Button button_backA;
    private TextView textA;

    class ConverterI {
        private double cos;
        private double p;
        private double kor = Math.sqrt(3);
        private double u;

        double convertI1() {
            double i;
            i = p / (u) / cos;
            return i;
        }

        double convertI3() {
            double i;
            i = p  / ((kor * u) * cos);
            return i;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amper);

        //прописываем элементы
        textA = (TextView) findViewById(R.id.textA);
        editCosA = (EditText) findViewById(R.id.editCosA);
        editP = (EditText) findViewById(R.id.editP);
        editVoltA = (EditText) findViewById(R.id.editVoltA);
        button_calc_a = (Button) findViewById(R.id.button_calc_a);
        button_backA = (Button) findViewById(R.id.button_backA);

        //проверка едит на ввод
        editCosA.addTextChangedListener(pTextWatcher);
        editP.addTextChangedListener(pTextWatcher);
        editVoltA.addTextChangedListener(pTextWatcher);

        button_backA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentI = new Intent(AmperActivity.this, MainActivity.class);
                startActivity(intentI);
            }
        });
    }

    private TextWatcher pTextWatcher;
    {
        pTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //ввод в эдит
                String PInput = editP.getText().toString().trim();
                String UInput = editVoltA.getText().toString().trim();
                String cosInput = editCosA.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_a.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //ввод в эдит
                String PInput = editP.getText().toString().trim();
                String cosInput = editCosA.getText().toString().trim();
                String UInput = editVoltA.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_a.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //ввод в эдит
                String PInput = editP.getText().toString().trim();
                String cosInput = editCosA.getText().toString().trim();
                String UInput = editVoltA.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_a.setEnabled(!PInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());

                button_calc_a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConverterI convI = new ConverterI();
                        double i;
                        String S1 = editCosA.getText().toString();
                        String S2 = editP.getText().toString();
                        String S3 = editVoltA.getText().toString();
                        convI.cos = Double.parseDouble(S1);
                        convI.p = Double.parseDouble(S2);
                        convI.u = Double.parseDouble(S3);

                        if (convI.cos <= 0 | convI.cos > 1) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "cos введен не корректный!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editCosA.getText().clear();
                        }
                        if (convI.u <= 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Напряжение введено не корректное!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editVoltA.getText().clear();
                        }
                        if (convI.p <= 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Ток введен не корректный!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editP.getText().clear();
                        }
                        if (convI.u > 0 & convI.u < 380) {
                            i = convI.convertI1();
                        } else {
                            i = convI.convertI3();
                        }
                        String S = Double.toString(i);
                       // textA.setText(i + "Ампер");
                        textA.setText(String.format("% .2f Ампер", i));
                    }
                });
            }
        };
    }
}
