package com.example.roomlibrary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private ExpenseRepo repo;
    private LiveData<List<Expense>> expList;
    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repo = new ExpenseRepo(application);
        expList = repo.getAllExpense();
    }
    public void insert(Expense expense){
        repo.insertData(expense);
    }
    public void delete(Expense expense){
        repo.deleteData(expense);
    }
    public void update(Expense expense){
        repo.updateData(expense);
    }
    public LiveData<List<Expense>> getAllExpense(){
        return expList;
    }
}
