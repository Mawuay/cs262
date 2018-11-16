package dma2.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Returns an integer result of the mathematical operation performed on two numbers.
 * <p>
 * This method always checks to make sure both values are not null,
 * and are whole numbers. When the inputs are not present the calculate button does not
 * calculate the result.
 *
 * param   value1 and value2 the numbers to calculated that need to be calculated.
 */
public class MainActivity extends AppCompatActivity {
    private EditText value1;
    private EditText value2;
    private TextView result;
    private Spinner functions_array;

    private float number1;
    private float number2;
    private float result_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = findViewById(R.id.editText);
        value2 = findViewById(R.id.editText2);
        Button calculate = findViewById(R.id.button_calculate);
        result = findViewById(R.id.result);
        functions_array = findViewById(R.id.operation);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value1.getText().length() == 0 && value2.getText().length() == 0) {
                    result.setText(R.string.set_text_string);
                } else {

                    number1 = Integer.parseInt(value1.getText().toString());
                    number2 = Integer.parseInt(value2.getText().toString());

                    switch (functions_array.getSelectedItem().toString()) {
                        case "+":
                            result_number = number1 + number2;
                            break;
                        case "-":
                            result_number = number1 - number2;
                            break;
                        case "x":
                            result_number = number1 * number2;
                            break;
                        case "/":
                            result_number = number1 / number2;
                            break;
                    }

                    result.setText(String.valueOf(result_number));
                }
            }
        });


    }


}
