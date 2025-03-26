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

public class PowerActivity extends MainActivity {

    private EditText editCosP;
    private EditText editAmp;
    private EditText editVoltP;
    private Button button_calc_p;
    private Button button_resetP;
    private Button button_backP;
    private TextView textP;

    class ConverterP {
        private double cos; //a
        private double i; //b
        private double kor = Math.sqrt(3);
        private double u;

        double convertP1() {
            double p;
            p = u * i * cos;
            return p;
        }

        double convertP3() {
            double p;
            p = kor * u * i * cos;
            return p;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        //прописываем элементы
        textP = (TextView) findViewById(R.id.textP);
        editCosP = (EditText) findViewById(R.id.editCosP);
        editAmp = (EditText) findViewById(R.id.editAmp);
        editVoltP = (EditText) findViewById(R.id.editVoltP);
        button_calc_p = (Button) findViewById(R.id.button_calc_p);
        button_backP = (Button) findViewById(R.id.button_backP);

        //проверка едит на ввод
        editCosP.addTextChangedListener(pTextWatcher);
        editAmp.addTextChangedListener(pTextWatcher);
        editVoltP.addTextChangedListener(pTextWatcher);

        button_backP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PowerActivity.this, MainActivity.class));
            }
        });
    }

    private TextWatcher pTextWatcher;
    {
        pTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //ввод в эдит
                String IInput = editAmp.getText().toString().trim();
                String UInput = editVoltP.getText().toString().trim();
                String cosInput = editCosP.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_p.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //ввод в эдит
                String IInput = editAmp.getText().toString().trim();
                String cosInput = editCosP.getText().toString().trim();
                String UInput = editVoltP.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_p.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //ввод в эдит
                String IInput = editAmp.getText().toString().trim();
                String cosInput = editCosP.getText().toString().trim();
                String UInput = editVoltP.getText().toString().trim();
                //если едит П и эдит кос пустые отключаем кнопку
                button_calc_p.setEnabled(!IInput.isEmpty() && !cosInput.isEmpty() && !UInput.isEmpty());

                button_calc_p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConverterP convP = new ConverterP();
                        double p;
                        String S1 = editCosP.getText().toString();
                        String S2 = editAmp.getText().toString();
                        String S3 = editVoltP.getText().toString();
                        convP.cos = Double.parseDouble(S1);
                        convP.i = Double.parseDouble(S2);
                        convP.u = Double.parseDouble(S3);

                        if (convP.cos <= 0 | convP.cos > 1) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "cos введен не корректный!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editCosP.getText().clear();
                        }
                        if (convP.u <= 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Напряжение введено не корректное!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editVoltP.getText().clear();
                        }
                        if (convP.i <= 0) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Ток введен не корректный!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);   // расположение
                            toast.show();
                            editAmp.getText().clear();
                        }
                        if (convP.u > 0 & convP.u < 380) {
                            p = convP.convertP1();
                        } else {
                            p = convP.convertP3();
                        }
                        String S = Double.toString(p);
/*
                        String S = Double.toString(p);
*/
                        textP.setText(String.format("% .0f Ватт", p));
                       // ("% .2f", i, "Ампер")
                      //  textP.setText(p + "Ватт");

                    }

                });



                /*button_resetP.setOnClickListener(v -> {
                    //textP.setText("");
                    editAmp.setText("");
                    editCosP.setText("");
                    editVoltP.setText("");
                }
                });*/

                /*button_resetP.setOnClickListener(v -> {
                    //textP.setText("");
                    //editAmp.getText().clear();
                    //editAmp.getText().clearSpans();
                    //editCosP.getText().clear();
                    //editVoltP.getText().clear();
                });*/
            }
        };
    }
}
