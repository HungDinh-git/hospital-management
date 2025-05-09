package com.example.qlbv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnSchedule;
    Button btnMedicalRecord;
    Button btnPrescription;
    Button btnManageAccounts;
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnSchedule = findViewById(R.id.btnSchedule);
        btnMedicalRecord = findViewById(R.id.btnMedicalRecord);
        btnPrescription = findViewById(R.id.btnPrescription);
        btnManageAccounts = findViewById(R.id.btnManageAccounts);

        // Nhận email từ LoginActivity
        email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("fullname");
        tvWelcome.setText("Xin chào, " + (name != null ? name : ""));

        // Sự kiện nút đặt lịch
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ScheduleActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        String role = getIntent().getStringExtra("role");

        // Hiển thị chức năng theo vai trò
        if ("Bệnh nhân".equalsIgnoreCase(role)) {
            btnSchedule.setVisibility(View.VISIBLE);
            btnMedicalRecord.setVisibility(View.VISIBLE);
            btnPrescription.setVisibility(View.GONE);
            btnManageAccounts.setVisibility(View.GONE);
        } else if ("Bác sĩ".equalsIgnoreCase(role)) {
            btnSchedule.setVisibility(View.GONE);
            btnMedicalRecord.setVisibility(View.VISIBLE);
            btnPrescription.setVisibility(View.VISIBLE);
            btnManageAccounts.setVisibility(View.GONE);
        } else if ("Admin".equalsIgnoreCase(role)) {
            btnSchedule.setVisibility(View.GONE);
            btnMedicalRecord.setVisibility(View.GONE);
            btnPrescription.setVisibility(View.GONE);
            btnManageAccounts.setVisibility(View.VISIBLE);
        }
        // Sự kiện nút Thông tin cá nhân
        Button btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        // Sự kiện nút Đăng xuất
        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Trở về LoginActivity và xóa ngăn xếp (clear task)
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // kết thúc activity hiện tại
            }
        });
        // Sự kiện nút Hồ sơ bệnh án
        Button btnMedicalRecord = findViewById(R.id.btnMedicalRecord);
        btnMedicalRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MedicalRecordActivity.class);
                intent.putExtra("email", email); // gửi email để truy vấn lịch sử khám
                intent.putExtra("role", role);// Truyền role từ trước đó
                startActivity(intent);
            }
        });


    }
}
