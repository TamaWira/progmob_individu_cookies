package id.aryad.cookies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class formActivity extends AppCompatActivity {

    private EditText editFoodName, editDescription, editRecipe, editTools, editSteps;
    private AppCompatButton btnAddRecipe, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editFoodName = (EditText) findViewById(R.id.editFoodName);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editRecipe = (EditText) findViewById(R.id.editRecipe);
        editTools = (EditText) findViewById(R.id.editTools);
        editSteps = (EditText) findViewById(R.id.editSteps);

        btnKembali = (AppCompatButton) findViewById(R.id.btnBackRecipe);
        btnAddRecipe = (AppCompatButton) findViewById(R.id.btnAddRecipe);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(formActivity.this, dashboardActivity.class);
                startActivity(intent_back);
            }
        });

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(formActivity.this);
                myDB.addData(
                        editFoodName.getText().toString().trim(),
                        editDescription.getText().toString().trim(),
                        editRecipe.getText().toString().trim(),
                        editTools.getText().toString().trim(),
                        editSteps.getText().toString().trim()
                );
            }
        });
    }
}