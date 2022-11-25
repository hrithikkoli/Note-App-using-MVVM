package com.example.roomlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.roomlibrary.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            setTitle("update");
            binding.insTitle.setText(getIntent().getStringExtra("title"));
            binding.insPrice.setText(getIntent().getStringExtra("disc"));
            int id = getIntent().getIntExtra("id",0);
            binding.button.setText("Update");
            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.insTitle.getText().toString());
                    intent.putExtra("disc",binding.insPrice.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();

                }
            });
        }else {
            setTitle("Add Mode");
            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.insTitle.getText().toString());
                    intent.putExtra("disc",binding.insPrice.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}