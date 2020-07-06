package sg.edu.rp.c346.id18054367.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGenderId = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", intGenderId);

                prefEdit.commit();

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        String strGPA = etGPA.getText().toString();
        int intGenderId = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name", strName);

        prefEdit.putFloat("gpa", Float.parseFloat(strGPA));

        prefEdit.putInt("genderId", intGenderId);

        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        String name = prefs.getString("name", "");
        etName.setText(name);

        Float gpa = prefs.getFloat("gpa", 0);
        etGPA.setText(gpa.toString());

        int intGenderId = prefs.getInt("genderId", R.id.radioButtonGenderMale);
        rgGender.check(intGenderId);




    }
}
