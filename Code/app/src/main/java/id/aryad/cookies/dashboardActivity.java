package id.aryad.cookies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class dashboardActivity extends AppCompatActivity {

    private TextView viewFullname, viewUsername;
    public String fullname, username;
    private AppCompatButton btnAddFood;
    private static final String TAG = "dashboardActivity";

    private RecyclerView mRecyclerView;
    CustomAdapter customAdapter;

    MyDatabaseHelper myDB;
    ArrayList<String> id, foodname, description, recipe, tools, steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mRecyclerView = (RecyclerView) findViewById(R.id.rcvResep);

        viewFullname = (TextView) findViewById(R.id.fullname);
        viewUsername = (TextView) findViewById(R.id.username);

        btnAddFood = (AppCompatButton) findViewById(R.id.btnAddFood);

        Intent intent = getIntent();
        fullname = intent.getStringExtra("fullname");
        username = intent.getStringExtra("username");

        viewFullname.setText("" + fullname);
        viewUsername.setText("" + username);

        myDB = new MyDatabaseHelper(dashboardActivity.this);

        id = new ArrayList<>();
        foodname = new ArrayList<>();
        description = new ArrayList<>();
        recipe = new ArrayList<>();
        tools = new ArrayList<>();
        steps = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(
                dashboardActivity.this, dashboardActivity.this,
                id, foodname, description, recipe, tools, steps
        );
        mRecyclerView.setAdapter(customAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(dashboardActivity.this));

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), formActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        while(cursor.moveToNext()){
            id.add(cursor.getString(0));
            foodname.add(cursor.getString(1));
            description.add(cursor.getString(2));
            recipe.add(cursor.getString(3));
            tools.add(cursor.getString(4));
            steps.add(cursor.getString(5));
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
        Log.d(TAG, "test onStart:");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "test onResume:");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "test onPause:");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "test onStop:");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Goodbye", Toast.LENGTH_LONG).show();
        Log.d(TAG, "test onDestroy:");
    }
}