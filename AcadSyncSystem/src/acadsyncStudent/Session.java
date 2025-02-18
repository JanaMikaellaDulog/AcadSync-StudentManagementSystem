package acadsyncStudent;

public class Session {
    private static int studentNumber;

    // Set the student number
    public static void setStudentNumber(int studentNumber) {
        Session.studentNumber = studentNumber;
    }

    // Get the student number
    public static int getStudentNumber() {
        return studentNumber;
    }
}