package com.example.roomlibrary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

     @Insert
     void insert(Expense expense);

     @Update
     void update(Expense expense);

     @Delete
     void delete(Expense expense);

     @Query("SELECT * FROM my_expense")
     LiveData<List<Expense>> getAllExpense();



}
