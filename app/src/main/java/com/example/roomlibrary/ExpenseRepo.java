package com.example.roomlibrary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class ExpenseRepo {
    private ExpenseDao expenseDao;
    private LiveData<List<Expense>> liveDataList;

    public ExpenseRepo(Application application) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(application);
        expenseDao = databaseHelper.expenseDao();
        liveDataList = expenseDao.getAllExpense();

    }
    public void insertData(Expense expense){new InsertTask(expenseDao).execute(expense);}
    public void updateData(Expense expense){new UpdateTask(expenseDao).execute(expense);}
    public void deleteData(Expense expense){new DeleteTask(expenseDao).execute(expense);}

    public LiveData<List<Expense>> getAllExpense(){
        return liveDataList;
    }

    public static class InsertTask extends AsyncTask<Expense,Void,Void>
    {
        private ExpenseDao expenseDao;

        public InsertTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insert(expenses[0]);
            return null;
        }
    }
    public static class UpdateTask extends AsyncTask<Expense,Void,Void>{
        private ExpenseDao expenseDao;

        public UpdateTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.update(expenses[0]);
            return null;
        }
    }
    public static class DeleteTask extends AsyncTask<Expense,Void,Void>{
        private ExpenseDao expenseDao;

        public DeleteTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.delete(expenses[0]);
            return null;
        }
    }

}
