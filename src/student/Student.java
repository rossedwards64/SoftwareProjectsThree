package student;

public class Student {
    String studentID;
    String studentFName;
    String studentLName;
    String studentPassword;
    String studentCourse;
    String studentAttendance;

    public Student(String studentID, String studentFName, String studentLName, String studentPassword, String studentCourse, String studentAttendance) {
        this.studentID = studentID;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.studentPassword = studentPassword;
        this.studentCourse = studentCourse;
        this.studentAttendance = studentAttendance;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public String getStudentAttendance() {
        return studentAttendance;
    }
}
