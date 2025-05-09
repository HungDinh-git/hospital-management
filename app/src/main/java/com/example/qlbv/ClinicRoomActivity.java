package com.example.qlbv;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ClinicRoomActivity extends AppCompatActivity {

    ListView listViewRooms;
    Button btnAddRoom;
    DatabaseHelper dbHelper;
    String currentUserRole;
    String currentUserEmail;
    ArrayAdapter<String> adapter;
    List<String> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_room);

        listViewRooms = findViewById(R.id.listViewRooms);
        btnAddRoom = findViewById(R.id.btnAddRoom);

        dbHelper = new DatabaseHelper(this);
        currentUserEmail = getIntent().getStringExtra("email");
        currentUserRole = dbHelper.getRoleByEmail(currentUserEmail);

        loadRooms();

        // Chỉ Admin mới được thêm phòng
        if (!"Admin".equalsIgnoreCase(currentUserRole)) {
            btnAddRoom.setVisibility(View.GONE);
        }

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddRoomDialog();
            }
        });

        listViewRooms.setOnItemLongClickListener((adapterView, view, position, id) -> {
            if ("Admin".equalsIgnoreCase(currentUserRole)) {
                showEditDeleteDialog(position);
            }
            return true;
        });
    }

    private void loadRooms() {
        roomList = dbHelper.getAllClinicRooms();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);
        listViewRooms.setAdapter(adapter);
    }

    private void showAddRoomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm phòng khám");

        final EditText input = new EditText(this);
        input.setHint("Nhập tên phòng");
        builder.setView(input);

        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String roomName = input.getText().toString().trim();
            if (!roomName.isEmpty()) {
                dbHelper.insertClinicRoom(roomName);
                loadRooms();
            }
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void showEditDeleteDialog(int position) {
        String selectedRoom = roomList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tùy chọn");
        builder.setItems(new CharSequence[]{"Sửa", "Xoá"}, (dialog, which) -> {
            if (which == 0) {
                showEditRoomDialog(selectedRoom);
            } else {
                dbHelper.deleteClinicRoom(selectedRoom);
                loadRooms();
            }
        });
        builder.show();
    }

    private void showEditRoomDialog(String oldRoomName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa tên phòng");

        final EditText input = new EditText(this);
        input.setText(oldRoomName);
        builder.setView(input);

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String newRoomName = input.getText().toString().trim();
            if (!newRoomName.isEmpty()) {
                dbHelper.updateClinicRoom(oldRoomName, newRoomName);
                loadRooms();
            }
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
}
