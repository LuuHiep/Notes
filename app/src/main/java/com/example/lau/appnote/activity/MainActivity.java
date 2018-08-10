package com.example.lau.appnote.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lau.appnote.R;
import com.example.lau.appnote.adapter.NoteAdapter;
import com.example.lau.appnote.database.Database;
import com.example.lau.appnote.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gvNote;

    ArrayList<Note> noteArrayList;
    NoteAdapter adapter;

    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        customActionBar();
        selectData();
        initClick();
    }

    private void initView() {
        gvNote = (GridView) findViewById(R.id.gv_note);

        noteArrayList = new ArrayList<>();
        adapter = new NoteAdapter(this, R.layout.item_note, noteArrayList);
        gvNote.setAdapter(adapter);
        database = new Database(this, "SQLite.Note", null, 1);
        database.queryData(" CREATE TABLE IF NOT EXISTS Note(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Title VARCHAR (100), Content VARCHAR (200), " +
                "NowDateView VARCHAR (100), NowTimeView VARCHAR (100), AlarmDate VARCHAR (500)," +
                " AlarmTime VARCHAR(500), Image VARCHAR (1000))");

        database.queryData(" CREATE TABLE IF NOT EXISTS Color(Id INTEGER PRIMARY KEY AUTOINCREMENT, R VARCHAR (100), G VARCHAR (100), B VARCHAR (100))");
        database.queryData(" INSERT INTO Color VALUES(null, '255','255','255')");
        database.queryData(" INSERT INTO Color VALUES(null, '0','255','127')");
        database.queryData(" INSERT INTO Color VALUES(null, '151','255','255')");
        database.queryData(" INSERT INTO Color VALUES(null, '255','225','255')");
    }


    public void selectData() {
        Cursor testData = database.getData("Select * from Note");
        noteArrayList.clear();
        while (testData.moveToNext()) {
            int id = testData.getInt(0);
            String title = testData.getString(1);
            String content = testData.getString(2);
            String nowDateView = testData.getString(3);
            String nowTimeView = testData.getString(4);
            String alarmDate = testData.getString(5);
            String alarmTime = testData.getString(6);
            String image= testData.getString(7);
            noteArrayList.add(new Note(id,title,content,nowDateView,nowTimeView,alarmDate,alarmTime,image));
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intentNewNoteActivity = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(intentNewNoteActivity);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void customActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Note"); //Thiết lập tiêu đề nếu muốn
        String title = actionBar.getTitle().toString(); //Lấy tiêu đề nếu muốn
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.icon_note);
        actionBar.setDisplayUseLogoEnabled(true);
    }

    // Xử lý click item
    private void initClick(){
        gvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentEditNote = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivity(intentEditNote);
            }
        });
    }
}
