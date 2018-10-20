package dma2.cs262.calvin.edu.homework2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class FetchPlayer extends AsyncTask<String, Void, String> {

    private ArrayList<TextView> textViews;

    private Context context;
    private LinearLayout layout;

    public FetchPlayer(Context context, LinearLayout layout) {
        this.context = context;
        this.layout = layout;
        textViews = new ArrayList<TextView>();
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getPlayerInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        layout.removeAllViews();

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");


            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject player = itemsArray.getJSONObject(i); //Get the current item
                extract(player);
            }

        } catch (JSONException e) {
            extract(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            if (s == null) {
                Toast.makeText(context, "There is no player with this ID", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void extract(JSONObject obj) {
        try {
            String id;
            String name = null;
            String email = null;

            id = obj.getString("id");

            try {
                name = obj.getString("name");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                email = obj.getString("emailAddress");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String result = id + ", ";

            if (id != null) {
                result += (name == null) ? "no name, " : name + ", ";
                result += (email == null) ? "no email" : email;
            }

            TextView textView = new TextView(context);
            textView.setText(result);
            textViews.add(textView);
            layout.addView(textView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}