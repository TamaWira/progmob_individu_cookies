package id.aryad.cookies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText editUpdateFoodname, editUpdateDescription, editUpdateRecipe, editUpdateTools, editUpdateSteps;
    private AppCompatButton btnUpdate, btnDelete, btnBackUpdate;

    private String id, foodname, description, recipe, tools, steps;

    private static final String TAG = "UpdateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editUpdateFoodname = findViewById(R.id.editUpdateFoodName);
        editUpdateDescription = findViewById(R.id.editUpdateDescription);
        editUpdateRecipe = findViewById(R.id.editUpdateRecipe);
        editUpdateTools = findViewById(R.id.editUpdateTools);
        editUpdateSteps = findViewById(R.id.editUpdateSteps);
        btnUpdate = findViewById(R.id.btnUpdateRecipe);
        btnBackUpdate = findViewById(R.id.btnBackRecipeUpdate);
        btnDelete = findViewById(R.id.btnDeleteRecipe);

        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                foodname = editUpdateFoodname.getText().toString().trim();
                description = editUpdateDescription.getText().toString().trim();
                recipe = editUpdateRecipe.getText().toString().trim();
                tools = editUpdateTools.getText().toString().trim();
                steps = editUpdateSteps.getText().toString().trim();
                myDB.updateData(id, foodname, description, recipe, tools, steps);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        btnBackUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("foodname") && getIntent().hasExtra("desc") &&
                getIntent().hasExtra("recipe") && getIntent().hasExtra("tools") && getIntent().hasExtra("steps")) {

            // get intent data
            id = getIntent().getStringExtra("id");
            foodname = getIntent().getStringExtra("foodname");
            description = getIntent().getStringExtra("desc");
            recipe = getIntent().getStringExtra("recipe");
            tools = getIntent().getStringExtra("tools");
            steps = getIntent().getStringExtra("steps");

            Log.d(TAG, "getAndSetIntentData: " + id);

            // set text to editText
            editUpdateFoodname.setText(foodname);
            editUpdateDescription.setText(description);
            editUpdateRecipe.setText(recipe);
            editUpdateTools.setText(tools);
            editUpdateSteps.setText(steps);
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + foodname);
        builder.setMessage("Are you sure you want to delete " + foodname + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDb = new MyDatabaseHelper(UpdateActivity.this);
                myDb.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}