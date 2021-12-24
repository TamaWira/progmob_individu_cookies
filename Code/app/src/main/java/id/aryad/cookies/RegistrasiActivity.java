package id.aryad.cookies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class RegistrasiActivity extends AppCompatActivity {

    private EditText editFullname, editUsername, editEmail;
    private RadioGroup radioGroup;
    private RadioButton selectedRadio;
    private TextView seekbarPercent;

    private String fullname, username, email, radioFocus, seekbarValueString;
    private int selectedId, seekbarValueInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        editFullname = (EditText) findViewById(R.id.editFullName);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editEmail = (EditText) findViewById(R.id.editEmail);

        seekbarPercent = (TextView)findViewById(R.id.seekbarPercent);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnRegis = (Button) findViewById(R.id.btnRegis);

        radioGroup = (RadioGroup) findViewById(R.id.groupFocus);

        SeekBar seekbarLove = (SeekBar) findViewById(R.id.seekbarLove);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(RegistrasiActivity.this, Landing.class);
                startActivity(intentBack);
            }
        });

        seekbarLove.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValueInt = progress;
                seekbarValueString = Integer.toString(seekbarValueInt);
                seekbarPercent.setText(": " + seekbarValueString + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname = editFullname.getText().toString();
                username = editUsername.getText().toString();
                email = editEmail.getText().toString();
//
                selectedId = radioGroup.getCheckedRadioButtonId();
                selectedRadio = (RadioButton) findViewById(selectedId);
                radioFocus = selectedRadio.getText().toString();
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RegistrasiActivity.this);

        // set title dialog
        alertDialogBuilder.setTitle("User's Data");

        // Set pesan dari dialog
        alertDialogBuilder
                .setMessage(
                        "Fullname : " + fullname.toString() + "\n" +
                        "Username : " + username.toString() + "\n" +
                        "Email : " + email.toString() + "\n" +
                        "Food Focus : " + radioFocus.toString() + "\n" +
                        "Cook Love Percent : " + seekbarValueString.toString() + "%")
                .setIcon(R.drawable.logo_cookies_black)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RegistrasiActivity.this, dashboardActivity.class);
                        intent.putExtra("fullname", fullname.toString());
                        intent.putExtra("username", username.toString());
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // Menampilkan alert dialog
        alertDialog.show();
    }
}