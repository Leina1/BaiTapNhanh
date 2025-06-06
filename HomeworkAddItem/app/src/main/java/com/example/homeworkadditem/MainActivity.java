package com.example.homeworkadditem;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAddItem;
    RecyclerView recyclerView;
    ShoppingItemAdapter adapter;
    ArrayList<ShoppingItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddItem = findViewById(R.id.btnAddItem);
        recyclerView = findViewById(R.id.recyclerViewItems);

        // Set up the RecyclerView with the adapter
        adapter = new ShoppingItemAdapter(itemList, new ShoppingItemAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                // Handle delete item
                itemList.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onEditClick(int position) {
                // Handle edit item
                ShoppingItem item = itemList.get(position);
                showEditDialog(item, position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Add item button click listener
        btnAddItem.setOnClickListener(v -> showAddDialog());
    }

    private void showAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        EditText edtItemName = dialogView.findViewById(R.id.edtItemName);
        EditText edtQuantity = dialogView.findViewById(R.id.edtQuantity);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(view -> {
            String itemName = edtItemName.getText().toString().trim();
            String quantityStr = edtQuantity.getText().toString().trim();

            if (itemName.isEmpty()) {
                edtItemName.setError("Please enter item name");
                return;
            }

            if (quantityStr.isEmpty()) {
                edtQuantity.setError("Please enter quantity");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                edtQuantity.setError("Quantity must be a number");
                return;
            }

            ShoppingItem newItem = new ShoppingItem(itemName, quantity);
            itemList.add(newItem);
            adapter.notifyItemInserted(itemList.size() - 1);
            recyclerView.scrollToPosition(itemList.size() - 1);

            Toast.makeText(MainActivity.this, "Added: " + itemName + ", Qty: " + quantity, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        dialog.show();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.copyFrom(window.getAttributes());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
            params.width = width;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            window.setGravity(Gravity.CENTER);
        }
    }

    private void showEditDialog(ShoppingItem item, int position) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        EditText edtItemName = dialogView.findViewById(R.id.edtItemName);
        EditText edtQuantity = dialogView.findViewById(R.id.edtQuantity);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        edtItemName.setText(item.getName());
        edtQuantity.setText(String.valueOf(item.getQuantity()));

        btnAdd.setText("Update");
        btnAdd.setOnClickListener(view -> {
            String itemName = edtItemName.getText().toString().trim();
            String quantityStr = edtQuantity.getText().toString().trim();

            if (itemName.isEmpty()) {
                edtItemName.setError("Please enter item name");
                return;
            }

            if (quantityStr.isEmpty()) {
                edtQuantity.setError("Please enter quantity");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                edtQuantity.setError("Quantity must be a number");
                return;
            }

            item.setName(itemName);
            item.setQuantity(quantity);
            adapter.notifyItemChanged(position);

            Toast.makeText(MainActivity.this, "Updated: " + itemName + ", Qty: " + quantity, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        dialog.show();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.copyFrom(window.getAttributes());
            int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
            params.width = width;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
            window.setGravity(Gravity.CENTER);
        }
    }
}
