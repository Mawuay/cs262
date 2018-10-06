package dma2.cs262.calvin.edu.lab05;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {


    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = (EditText) findViewById(R.id.book_input);
        mTitleText = (TextView) findViewById(R.id.titleText);
        mAuthorText = (TextView) findViewById(R.id.authorText);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        else {
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new dma2.cs262.calvin.edu.lab05.BookLoader(this, args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < itemsArray.length() || (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
                mBookInput.setText("");
            } else {
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
            }

        } catch (Exception e) {
            mTitleText.setText(R.string.no_results);
            mAuthorText.setText("");
            e.printStackTrace();
        }


    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }
}