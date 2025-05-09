package com.example.qlbv;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    Spinner spinnerDoctor;
    DatePicker datePicker;
    TimePicker timePicker;
    EditText edtSymptoms;
    Button btnSubmit;

    DatabaseHelper dbHelper;
    String currentUserEmail; // Nhận từ Login hoặc Dashboard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        spinnerDoctor = findViewById(R.id.spinnerDoctor);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        edtSymptoms = findViewById(R.id.edtSymptoms);
        btnSubmit = findViewById(R.id.btnSubmit);

        dbHelper = new DatabaseHelper(this);

        // Lấy email người dùng từ Intent
        currentUserEmail = getIntent().getStringExtra("email");

        // Lấy danh sách bác sĩ từ database
        List<String> doctors = dbHelper.getDoctors();
        if (doctors.isEmpty()) {
            doctors.add("Không có bác sĩ");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(adapter);

        // Nút đặt lịch
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctor = spinnerDoctor.getSelectedItem().toString().replace("Bs. ", "").trim();
                String symptoms = edtSymptoms.getText().toString().trim();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = String.format("%02d/%02d/%04d", day, month, year);

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String time = String.format("%02d:%02d", hour, minute);

                if (symptoms.isEmpty()) {
                    Toast.makeText(ScheduleActivity.this, "Vui lòng nhập triệu chứng", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = dbHelper.insertAppointment(currentUserEmail, doctor, date, time, symptoms);

                if (success) {
                    Toast.makeText(ScheduleActivity.this, "Đặt lịch khám thành công!", Toast.LENGTH_SHORT).show();
                    finish(); // Quay lại màn hình trước
                } else {
                    Toast.makeText(ScheduleActivity.this, "Lỗi khi đặt lịch", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
