package com.example.buoi6lab6.Models;

public class Student {
    public int StudentId;
    public String StudentName;
    public String DTB;
    public int FalcultyId;

    public Student() {
    }

    public Student(int studentId, String studentName, String DTB, int FalcultyId) {
        StudentId = studentId;
        StudentName = studentName;
        this.DTB = DTB;
        this.FalcultyId = FalcultyId;
    }
}
