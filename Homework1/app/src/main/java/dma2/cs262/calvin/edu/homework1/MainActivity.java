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
     Spinner math_function;

     int number1, number2;
     float result_number;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1 = findViewById(R.id.editText);
        value2 = findViewById(R.id.editText2);
        Calculate = findViewById(R.id.button_calculate);
        result = findViewById(R.id.result);
        math_function = findViewById(R.id.operation);


    }


}
