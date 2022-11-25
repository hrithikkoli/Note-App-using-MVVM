package com.example.roomlibrary;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
@Database(entities = Expense.class,version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public static final String DB_NAME = "expensedb";
    private static DatabaseHelper instance;
    public abstract ExpenseDao expenseDao();
    public static synchronized DatabaseHelper getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context,DatabaseHelper.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
