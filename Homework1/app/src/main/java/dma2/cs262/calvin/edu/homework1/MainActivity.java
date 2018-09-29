package dma2.cs262.calvin.edu.homework1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
     EditText value1;
     EditText value2;
     Button Calculate;
     TextView result;
     Spinner functions_array;

     float number1, number2;
     float result_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = findViewById(R.id.editText);
        value2 = findViewById(R.id.editText2);
        Calculate = findViewById(R.id.button_calculate);
        result = findViewById(R.id.result);
        functions_array = findViewById(R.id.operation);


        Calculate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (value1.getText().length() == 0 && value2.getText().length() == 0) {
                result.setText("Make sure that input Values are not empty");
        } else {

                number1 = Integer.parseInt(value1.getText().toString());
                number2 = Integer.parseInt(value2.getText().toString());

                if (functions_array.getSelectedItem().toString().equals("+")) {
                    result_number = number1 + number2;
                } else if (functions_array.getSelectedItem().toString().equals("-")) {
                    result_number = number1 - number2;
                } else if (functions_array.getSelectedItem().toString().equals("x")) {
                    result_number = number1 * number2;
                } else if (functions_array.getSelectedItem().toString().equals("/")) {
                    result_number = number1 / number2;
                }

                result.setText( String.valueOf(result_number));
            }
            }
        } );


    }


}
