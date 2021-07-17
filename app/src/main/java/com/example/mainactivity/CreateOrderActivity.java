package com.example.mainactivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import android.widget.Toast;

import android.os.Bundle;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // setter for toolbar
        ActionBar actionBar = getSupportActionBar();//getter for toolbar
        assert actionBar != null; /* assert is used for performing a check before using sth. it checks whether
        actionbar is null or not, if it is it throws a error */
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickDone(View view){
        String text ="Your order has been updated";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        //coordinator is the id of the coordinate layout
        snackbar.setAction("undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(CreateOrderActivity.this, "Undone", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        snackbar.show();
    }
}