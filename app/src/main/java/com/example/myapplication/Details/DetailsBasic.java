package com.example.myapplication.Details;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.cardview.widget.CardView;

public class DetailsBasic extends Activity {
EditText fname,lname,pemail,mob, height, weight, bday;
Spinner bg;
RadioGroup genderGroup;
int genderButton;
CardView Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);
        fname = findViewById(R.id.F_Name);
        lname = findViewById(R.id.L_Name);
        pemail = findViewById(R.id.P_Email);
        mob = findViewById(R.id.Mobile);
        bday = findViewById(R.id.Bday);
        height = findViewById(R.id.Height);
        weight = findViewById(R.id.Weight);
        bg = findViewById(R.id.Blood);
        genderGroup = findViewById(R.id.Gender_RG);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Blood_Group, R.layout.support_simple_spinner_dropdown_item);
        bg.setAdapter(arrayAdapter);
        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailsBasic.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Next = findViewById(R.id.Next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sfname, slname, spemail, sbg, sgender,dbday;
                long imob;
                int iheight, iweight;


                sfname=fname.getText().toString();
                slname=lname.getText().toString();
                spemail=pemail.getText().toString();
                sbg= bg.getSelectedItem().toString();
                imob=Long.parseLong(mob.getText().toString());
                iheight=Integer.parseInt(height.getText().toString());
                iweight=Integer.parseInt(weight.getText().toString());
                sgender =((RadioButton)findViewById(genderGroup.getCheckedRadioButtonId())).getText().toString();
                dbday=bday.getText().toString();
                Log.d("details",sfname+slname+spemail+sbg+imob+iheight+iweight+sgender+dbday);
//                DetailsBook detailsBook=new DetailsBook(sfname,slname,spemail,sbg,sgender,iheight,iweight,imob,dbday);
                DetailsBook detailsBook=new DetailsBook(sfname,slname,spemail,sbg,sgender,iheight,iweight,imob,dbday);
                Intent intent=new Intent(DetailsBasic.this,DetailsParent.class);
                intent.putExtra("Book",detailsBook);
                startActivity(intent);

            }
        });
    }

    final Calendar myCalendar = Calendar.getInstance();
//            EditText edittext= (EditText) findViewById(R.id.Birthday);
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        bday.setText(sdf.format(myCalendar.getTime()));
    }



    }
