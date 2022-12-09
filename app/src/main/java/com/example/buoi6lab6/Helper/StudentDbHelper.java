package com.example.buoi6lab6.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.buoi6lab6.Models.Faculty;
import com.example.buoi6lab6.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "studentDB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STUDENT_NAME = "Student";
    public static final String COL_Student_ID = "StudentId";
    public static final String COL_Student_NAME = "StudentName";
    public static final String COL_Student_SCORE = "AverageScore";
    public static final String COL_Student_FACULTY = "FalcultyId";
    public static final String TABLE_FALCULTY_NAME = "Faculty";
    public static final String COL_FALCULTY_ID = "FacultyId";
    public static final String COL_FALCULTY_NAME = "falcultyName";

    public StudentDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
// Tạo bảng Student
        String query = "CREATE TABLE " + TABLE_STUDENT_NAME + " ("
                + COL_Student_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_Student_NAME + " TEXT,"
                + COL_Student_SCORE + " TEXT,"
                + COL_Student_FACULTY + " INTEGER)";
        sqLiteDatabase.execSQL(query);
//Tạo bảng Faculty
        String query2 = "CREATE TABLE " + TABLE_FALCULTY_NAME + " ("
                + COL_FALCULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FALCULTY_NAME + " TEXT)";
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//xóa bảng Student
        String dropDB = "DROP TABLE IF EXISTS " + TABLE_STUDENT_NAME;
        sqLiteDatabase.execSQL(dropDB);
//xóa bảng Khoa
        String dropDB2 = "DROP TABLE IF EXISTS " + TABLE_FALCULTY_NAME;
        sqLiteDatabase.execSQL(dropDB2);
        onCreate(sqLiteDatabase);
    }

    //2 -- CRUID for Faculty
// Có thể tách vào FacultyDAO hoặc đưa vào Models/Faculty
    public List<Faculty> GetAllFalculty() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Faculty> listFalculty = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_FALCULTY_NAME;
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            Faculty temp = new Faculty();
            temp.FacultyId = c.getInt(0);
            temp.FacultyName = c.getString(1);
            listFalculty.add(temp);
        }
        return listFalculty;
    }

    public void Insert(Faculty f) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("INSERT INTO %s VALUES(%d,'%s')",
                TABLE_FALCULTY_NAME, f.FacultyId, f.FacultyName);
        db.execSQL(query);
    }

    public void Update(Faculty f) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("UPDATE %s SET %s = '%s' WHERE %s = %d",
                TABLE_FALCULTY_NAME, COL_FALCULTY_NAME, f.FacultyName,
                COL_FALCULTY_ID, f.FacultyId);
        db.execSQL(query);
    }

    public void Delete(Faculty f) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %d",
                TABLE_FALCULTY_NAME, COL_FALCULTY_ID, f.FacultyId);
        db.execSQL(query);
    }

    public void Delete(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM %s WHERE %s = %d",
                TABLE_STUDENT_NAME, COL_Student_ID, student.StudentId);
        db.execSQL(query);
    }

    //3- CRUID for Student...
// Có thể tách vào StudentDAO hoặc đưa vào Models/Student
    public List<Student> GetAllStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STUDENT_NAME;
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            Student temp = new Student();
            temp.StudentId = c.getInt(0);
            temp.StudentName = c.getString(1);
            temp.DTB = c.getString(2);
            temp.FalcultyId = c.getInt(3);

            studentList.add(temp);
        }
        return studentList;
    }


    public void Insert(Student obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("INSERT INTO %s VALUES(%d,'%s','%s','%d')",
                TABLE_STUDENT_NAME, obj.StudentId, obj.StudentName,obj.DTB,obj.FalcultyId);
        db.execSQL(query);
    }
}