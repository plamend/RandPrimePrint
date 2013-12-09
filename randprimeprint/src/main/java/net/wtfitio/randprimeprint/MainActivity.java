package net.wtfitio.randprimeprint;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by plamend on 12/9/13.
 */
public class MainActivity extends Activity {

    EditText min;
    EditText max;
    Button but;
    TextView text;
    private Context context;
    ProgressDialog pd;
    ArrayList<String> list1;
    String listString="";


    private class fprime extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String min_number = min.getText().toString();
            String max_number = max.getText().toString();
            if (!min_number.equals("")&&!max_number.equals("")) {

                int int_min = Integer.parseInt(min_number);
                int int_max = Integer.parseInt(max_number);
                int i = int_min;
                while (i!=int_max) {
                    BigInteger to_test = BigInteger.valueOf(i);
                    boolean out = to_test.isProbablePrime(10);
                    if (out) {

                        Log.v("Prime:",String.valueOf(i));
                        listString += String.valueOf(i) +","+ "\t";

                    }
                    i++;
                }


            }
            else{

               // Toast.makeText(getApplicationContext(),"Enter numbers!",Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        protected void onPreExecute() {
             pd = new ProgressDialog(context);
            pd.setTitle("Processing...");
            pd.setMessage("Please wait.");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        protected void onPostExecute(Void result) {
            if (pd!=null) {
                pd.dismiss();
            }
            text.setText(listString);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        context=this;
        min = (EditText)findViewById(R.id.min);
        max=(EditText)findViewById(R.id.max);
        but=(Button)findViewById(R.id.button);
        text= (TextView)findViewById(R.id.text);
        list1=new ArrayList<String>();

but.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        new fprime().execute();






    }
});



    }
}
