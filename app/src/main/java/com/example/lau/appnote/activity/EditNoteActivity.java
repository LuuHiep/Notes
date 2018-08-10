package com.example.lau.appnote.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lau.appnote.R;

public class EditNoteActivity extends AppCompatActivity {
    private BottomNavigationView bnvBottomBarEdit;
    private LinearLayout llNewNoteEdit, llSpinnerDateTimeEdit;
    private EditText etTitleEdit, etContentEdit;
    private TextView tvNowDateEdit, tvNowTimeEdit;
    private ImageView ivImageEdit;
    private Button btAlarmEdit, btExitSpinner, btDateExit, btDateOk, btTimeExit, btTimeOk;
    private Spinner spDate, spTime;
    private Dialog dialogDate, dialogTime, dialogCamera, dialogSetColor;
    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        initView();
        initClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView(){
        bnvBottomBarEdit = (BottomNavigationView) findViewById(R.id.btv_edit);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                onBackPressed();
                break;
            case R.id.menu_camera:
                break;
            case R.id.menu_color:
                break;
            case R.id.menu_save:
                break;
            case R.id.menu_new_note:
                Intent intentEditNoteNewNote = new Intent(EditNoteActivity.this, NewNoteActivity.class);
                // chuyển màn hình có truyền data
                startActivity(intentEditNoteNewNote);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initClick(){
        bnvBottomBarEdit.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }

}
