//John Hereshko - Java I - Project 2

package com.jeshko.android.hereshko_john_ji_project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    EditText enteredText;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private TextView lengthView;
    private int charLengthTotal;
    private TextView charView;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredText = (EditText)this.findViewById(R.id.editText);
        ListView listView = (ListView) this.findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        lengthView = (TextView)this.findViewById(R.id.textView2);
        charView = (TextView)this.findViewById(R.id.textView3);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String clickedLine = getResources().getString(R.string.clicked_line);
                final String removeLine = getResources().getString(R.string.remove);
                final String item = ((TextView) view).getText().toString();
                String lineNumber;
                lineNumber = Integer.toString(position + 1);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(clickedLine);
                alertDialog.setMessage(lineNumber + ". " + item);
                alertDialog.setCancelable(true);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton(removeLine, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String itemLengthString = getResources().getString(R.string.item_length);
                        final String averageLengthString = getResources().getString(R.string.average_length);
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();

                        int lengthInt = arrayList.size();
                        String length = Integer.toString(lengthInt);

                        lengthView.setText(length + " " + itemLengthString);

                        charLengthTotal =  charLengthTotal - item.length();
                        if (lengthInt != 0) {
                            int charLengthCurrent = charLengthTotal / lengthInt;
                            String charLengthString = Integer.toString(charLengthCurrent);
                            charView.setText(charLengthString + " " + averageLengthString);
                            adapter.notifyDataSetChanged();


                        }
                        else if (lengthInt == 0)
                        {
                            charView.setText("0 " + averageLengthString);
                        }
                    }

                });


                AlertDialog alert = alertDialog.create();
                alert.show();
            }
        });
    }

    public void buttonClick(View v)
    {
        enteredText = (EditText)this.findViewById(R.id.editText);
        final String s = enteredText.getText().toString();
        if (s.isEmpty())
        {
            //if empty, add nothing
        }
        else
        {
            if (arrayList.contains(s))
            {
                //add nothing when item is the same
            }
            else
            {
                final String itemLengthString = getResources().getString(R.string.item_length);
                final String averageLengthString = getResources().getString(R.string.average_length);
                arrayList.add(s);
                adapter.notifyDataSetChanged();

                enteredText.setText("");

                int lengthInt = arrayList.size();
                String length = Integer.toString(lengthInt);
                lengthView.setText(length + " " + itemLengthString);

                charLengthTotal = s.length() + charLengthTotal;
                int charLengthCurrent = charLengthTotal / lengthInt;
                String charLengthString = Integer.toString(charLengthCurrent);
                charView.setText(charLengthString + " " + averageLengthString);
            }

        }
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


