package kz.talipovsn.sum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_b;// Переменная для доступа к компоненту со значением "b"
    EditText editText_x;
    TextView textView_sum; // Переменная для доступа к компоненту со значением результата
    Button buttonSum; // Переменная для доступа к компоненту кнопки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_sum = findViewById(R.id.textView_sum);
        buttonSum = findViewById(R.id.buttonSum);


        //Обработчик нажатий на клавиши
        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Проверка условия: если пусто в "a" или "b"
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("")
                        ||
                        editText_x.getText().toString().trim().equals("")) {
                    buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
                } else {
                    buttonSum.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };

        {
            //buttonSum.setEnabled(false); // Выключаем доступность нажатия у кнопки
            editText_a.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
            editText_b.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
            editText_x.setOnKeyListener(myKeyListener);
        }
        if (savedInstanceState != null) {
            textView_sum.setText(savedInstanceState.getString("y"));
        }
    }

    // Сохранение нужных нам значений из компонент при перевороте экрана
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("y", textView_sum.getText().toString());
    }

        // МЕТОД КНОПКИ РАСЧЕТА
        public void onClick (View v){
            // Объявление локальных переменных
            double a, b, y, x;

            try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

                // Чтение данных из компонент
                a = Double.parseDouble(editText_a.getText().toString().trim());
                b = Double.parseDouble(editText_b.getText().toString().trim());
                x = Double.parseDouble(editText_x.getText().toString().trim());
                // Основной алгоритм
                if (x >= 4) {
                    y = 10 * (x + Math.pow(a, 2))/(b + a);
                } else {
                    y = 5 * (x + Math.pow(a, 2) + b);
                }
                // Вывод полученного значения в компонент
                textView_sum.setText(String.format("%.3f",y));

            } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

                // Вывод сообщения об ошибке
                textView_sum.setText("Неверные входные данные для расчета!");

            }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА
        }
    }










