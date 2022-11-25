package com.example.roomlibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomlibrary.databinding.ActivityDataInsertBinding;
import com.example.roomlibrary.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper helper;
    ActivityMainBinding binding;
     ExpenseViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider( this,(ViewModelProvider.Factory)ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ExpenseViewModel.class);
        helper = DatabaseHelper.getInstance(this);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        RVAdapter adapter = new RVAdapter();
        binding.recyclerView.setAdapter(adapter);

        viewModel.getAllExpense().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                adapter.submitList(expenses);
            }
        });
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               if (direction==ItemTouchHelper.RIGHT){
                   viewModel.delete(adapter.getExpense(viewHolder.getAdapterPosition()));
               }
               else {
                    Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("title",adapter.getExpense(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("disc",adapter.getExpense(viewHolder.getAdapterPosition()).getPrice());
                    intent.putExtra("id",adapter.getExpense(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);
               }


           }
       }).attachToRecyclerView(binding.recyclerView);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String title = data.getStringExtra("title");
            String disc = data.getStringExtra("disc");
            Expense expense = new Expense(title,disc);
            viewModel.insert(expense);

        }
        else if(requestCode==2){
            String title = data.getStringExtra("title");
            String disc = data.getStringExtra("disc");
            Expense expense = new Expense(title,disc);
            expense.setId(data.getIntExtra("id",0));
            viewModel.update(expense);
            Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
        }
    }
}