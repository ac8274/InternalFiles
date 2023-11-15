package com.example.internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "inttest.txt";

    EditText Text_Input;
    TextView textView;
    Button Save_Button;
    Button Reset_Button;
    Button Exit_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Text_Input = findViewById(R.id.Text_Input);
        textView = findViewById(R.id.textView2);
        Save_Button = findViewById(R.id.Save_Button);
        Reset_Button = findViewById(R.id.Reset_Button);
        Exit_Button = findViewById(R.id.Exit_Button);
        textView.setText(Internal_reader());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.Credits)
        {
            Intent si = new Intent(this,credits.class);
            startActivity(si);
        }
        return true;
    }

    public void Save_Internal_File(View view) {
        writer_Internal(Text_Input.getText().toString());
        textView.setText(Internal_reader() + Text_Input.getText().toString());
    }


    public void Reset_Internal_File(View view) {
        writer_Internal("");
        textView.setText("");
    }

    public void Exit_func(View view) {
        writer_Internal(Internal_reader() + Text_Input.getText().toString());
        System.exit(1);
    }

    public String Internal_reader()
    {
        StringBuilder sB = new StringBuilder();
        sB.append("");
        try{
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            fOS.close();
            FileInputStream fIS= openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            String line = bR.readLine();
            while (line != null) {
                sB.append(line+'\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sB.toString();
    }

    public void writer_Internal(String new_line)
    {
        try{
            FileOutputStream fOS = openFileOutput(FILENAME,MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(new_line);
            bW.close();
            oSW.close();
            fOS.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}