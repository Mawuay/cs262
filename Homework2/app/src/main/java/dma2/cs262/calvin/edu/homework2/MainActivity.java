package dma2.cs262.calvin.edu.homework2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private FetchPlayer fetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        input = findViewById(R.id.input);
    }

    public void doSearch(View view) {

        ((LinearLayout)findViewById(R.id.home_layout)).removeAllViews();

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        String inputText = input.getText().toString().trim();

        if (inputText.length() != 0) {
            try {
                int id = Integer.parseInt(inputText);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Input must be a number", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            fetcher = new FetchPlayer(this, (LinearLayout)findViewById(R.id.home_layout));
            fetcher.execute(inputText);
        } else {
            Toast.makeText(this, "Internet connection unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }
}