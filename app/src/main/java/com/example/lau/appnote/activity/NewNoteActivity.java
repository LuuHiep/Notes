package com.example.lau.appnote.activity;


import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lau.appnote.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewNoteActivity extends AppCompatActivity {
    private LinearLayout llNewNote, llSpinnerDateTime;
    private EditText etTitle, etContent;
    private TextView tvNowDate, tvNowTime;
    private ImageView ivImage;
    private Button btAlarm, btExitSpinner, btDateExit, btDateOk, btTimeExit, btTimeOk;
    private Spinner spDate, spTime;
    private Dialog dialogDate, dialogTime, dialogCamera, dialogSetColor;
    private DatePicker datePicker;
    private TimePicker timePicker;

    private ArrayAdapter<String> adapterDate, adapterTime;
    private List<String> listDate, listTime;

    int REQUES_CODE_CAMERA = 123;
    int REQUES_CODE_FOLDER = 124;

    String title;
    String contet;
    String image = null;
    String nowTimeView;
    String nowDateView;
    String alarmDate = null;
    String alarmTime = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        initView();
        initClick();
        setNowDateTime();
    }

    private void initView() {
        llNewNote = (LinearLayout) findViewById(R.id.ll_new_note);
        llSpinnerDateTime = (LinearLayout) findViewById(R.id.ll_spinner_date_time);
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);
        tvNowDate = (TextView) findViewById(R.id.tv_date_now);
        tvNowTime = (TextView) findViewById(R.id.tv_time_now);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        btAlarm = (Button) findViewById(R.id.bt_alarm);
        btExitSpinner = (Button) findViewById(R.id.bt_exit_spinner);
        spDate = (Spinner) findViewById(R.id.sp_date);
        spTime = (Spinner) findViewById(R.id.sp_time);
    }

    private void initClick() {
        btAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btAlarm.setVisibility(View.INVISIBLE);
                llSpinnerDateTime.setVisibility(View.VISIBLE);
                showSpinnerTime();
                showSpinnerDate();
            }
        });

        btExitSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btAlarm.setVisibility(View.VISIBLE);
                llSpinnerDateTime.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Xử lý menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                onBackPressed();
                break;
            case R.id.menu_camera:
                showDialogCamera();
                break;
            case R.id.menu_color:
                showDialogSetColor();

                Button btSetColor1 = (Button) dialogSetColor.findViewById(R.id.bt_1);
                Button btSetColor2 = (Button) dialogSetColor.findViewById(R.id.bt_2);
                Button btSetColor3 = (Button) dialogSetColor.findViewById(R.id.bt_3);
                Button btSetColor4 = (Button) dialogSetColor.findViewById(R.id.bt_4);

                btSetColor1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor dataColor = MainActivity.database.getData("SELECT * FROM Color");
                        while (dataColor.moveToNext()) {
                            int id = dataColor.getInt(0);
                            int r = dataColor.getInt(1);
                            int g = dataColor.getInt(2);
                            int b = dataColor.getInt(3);
                            if (id == 1) {
                                llNewNote.setBackgroundColor(Color.rgb(r, g, b));
                            }
                        }
                        dialogSetColor.dismiss();
                    }
                });

                btSetColor2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor dataColor = MainActivity.database.getData("SELECT * FROM Color");
                        while (dataColor.moveToNext()) {
                            int id = dataColor.getInt(0);
                            int r = dataColor.getInt(1);
                            int g = dataColor.getInt(2);
                            int b = dataColor.getInt(3);
                            if (id == 2) {
                                llNewNote.setBackgroundColor(Color.rgb(r, g, b));
                            }
                        }
                        dialogSetColor.dismiss();
                    }
                });

                btSetColor3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor dataColor = MainActivity.database.getData("SELECT * FROM Color");
                        while (dataColor.moveToNext()) {
                            int id = dataColor.getInt(0);
                            int r = dataColor.getInt(1);
                            int g = dataColor.getInt(2);
                            int b = dataColor.getInt(3);
                            if (id == 3) {
                                llNewNote.setBackgroundColor(Color.rgb(r, g, b));
                            }
                        }
                        dialogSetColor.dismiss();
                    }
                });

                btSetColor4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor dataColor = MainActivity.database.getData("SELECT * FROM Color");
                        while (dataColor.moveToNext()) {
                            int id = dataColor.getInt(0);
                            int r = dataColor.getInt(1);
                            int g = dataColor.getInt(2);
                            int b = dataColor.getInt(3);
                            if (id == 4) {
                                llNewNote.setBackgroundColor(Color.rgb(r, g, b));
                            }
                        }
                        dialogSetColor.dismiss();
                    }
                });
                break;
            case R.id.menu_save:
                    title = etTitle.getText().toString();
                    contet = etContent.getText().toString();
                    // Thêm data vào note
                    MainActivity.database.queryData("INSERT INTO Note VALUES(null, '"+title+"', '"+contet+"'," +
                            "'"+nowDateView+"', '"+nowTimeView+"', '"+alarmDate+"', '"+alarmTime+"', '"+image+"')");
                    Intent intentMain = new Intent(NewNoteActivity.this, MainActivity.class);
                    startActivity(intentMain);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Xử lý Background
    private void showDialogSetColor() {
        dialogSetColor = new Dialog(NewNoteActivity.this);
        dialogSetColor.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSetColor.setContentView(R.layout.dialog_set_color);
        dialogSetColor.show();
    }

    // Xử lý Camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUES_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivImage.setImageBitmap(bitmap);
        }

        if (requestCode == REQUES_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialogCamera() {
        dialogCamera = new Dialog(this);
        dialogCamera.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCamera.setContentView(R.layout.dialog_camera);

        LinearLayout llTakePhoto = (LinearLayout) dialogCamera.findViewById(R.id.ll_take_phote);
        llTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, REQUES_CODE_CAMERA);
            }
        });

        LinearLayout llChoosePhoto = (LinearLayout) dialogCamera.findViewById(R.id.ll_choose_phote);
        llChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUES_CODE_FOLDER);
            }
        });
        dialogCamera.show();

    }

    // Xử lý SpinnerDate
    private void showSpinnerDate() {
        listDate = new ArrayList<>();
        listDate.add("Today");
        listDate.add("Tomorrow");
        listDate.add("Next Thu Hai");
        listDate.add("Other");
        adapterDate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDate);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDate.setAdapter(adapterDate);

        // Bắt sự kiện cho spinnerDate

        spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spDate.getItemAtPosition(i).toString().equals(listDate.get(0)) && spDate.getVisibility() == View.VISIBLE) {
                    //alarmDate = NextToDate.changeDate(nowDateView, 1);
                } else if (spDate.getItemAtPosition(i).toString().equals(listDate.get(1)) && spDate.getVisibility() == View.VISIBLE) {

                } else if (spDate.getItemAtPosition(i).toString().equals(listDate.get(2)) && spDate.getVisibility() == View.VISIBLE) {

                } else if (spDate.getItemAtPosition(i).toString().equals(listDate.get(3)) && spDate.getVisibility() == View.VISIBLE) {
                    showDateDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // Xử lý SpinnerTime
    private void showSpinnerTime() {
        listTime = new ArrayList<>();
        listTime.add("09:00");
        listTime.add("13:00");
        listTime.add("17:00");
        listTime.add("20:00");
        listTime.add("Other");
        adapterTime = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTime);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTime.setAdapter(adapterTime);

        // Bắt sự kiện cho spinnerTime

        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spTime.getItemAtPosition(i).toString().equals(listTime.get(0)) && spTime.getVisibility() == View.VISIBLE) {

                } else if (spTime.getItemAtPosition(i).toString().equals(listTime.get(1)) && spTime.getVisibility() == View.VISIBLE) {

                } else if (spTime.getItemAtPosition(i).toString().equals(listTime.get(2)) && spTime.getVisibility() == View.VISIBLE) {

                } else if (spTime.getItemAtPosition(i).toString().equals(listTime.get(3)) && spTime.getVisibility() == View.VISIBLE) {

                } else if (spTime.getItemAtPosition(i).toString().equals(listTime.get(4)) && spTime.getVisibility() == View.VISIBLE) {
                    showTimeDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // Lấy ngày giờ hiện tại
    private void setNowDateTime() {
        Date date = new Date();
        String strDateFormat = "dd/MM/yyyy";
        SimpleDateFormat nowDate = new SimpleDateFormat(strDateFormat);
        tvNowDate.setText(nowDate.format(date));
        nowDateView = tvNowDate.getText().toString();
        String strTimeFormat = "HH:mm";
        SimpleDateFormat nowTime = new SimpleDateFormat(strTimeFormat);
        tvNowTime.setText(nowTime.format(date));
        nowTimeView = tvNowTime.getText().toString();
    }

    // Xử lý DatePickerDialog
    private void showDateDialog() {
        dialogDate = new Dialog(this);
        dialogDate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDate.setContentView(R.layout.dialog_date_picker);
        dialogDate.show();

        datePicker = (DatePicker) dialogDate.findViewById(R.id.dp_date_picker);

        btDateOk = (Button) dialogDate.findViewById(R.id.bt_date_ok);
        btDateExit = (Button) dialogDate.findViewById(R.id.bt_date_exit);

        btDateExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogDate.dismiss();
            }
        });

        btDateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                // Lấy thời gian hiện tại
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        alarmDate = i2 + "/" + i1 + "/" + i;
                        dialogDate.dismiss();
                        listDate.set(3, alarmDate);
                        adapterDate.notifyDataSetChanged();

                    }
                });
            }
        });
    }

    // Xử lý TimePickerDialog
    private void showTimeDialog() {
        dialogTime = new Dialog(this);
        dialogTime.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogTime.setContentView(R.layout.dialog_time_picker);
        dialogTime.show();

        timePicker = (TimePicker) dialogTime.findViewById(R.id.tp_time_picker);
        btTimeOk = (Button) dialogTime.findViewById(R.id.bt_time_ok);
        btTimeExit = (Button) dialogTime.findViewById(R.id.bt_time_exit);

        btTimeExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTime.dismiss();
            }
        });

        btTimeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
