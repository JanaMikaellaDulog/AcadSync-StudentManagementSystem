package acadsyncStudent;

public class GradesButtonSession {
	private static String academicYear;
	private static String Semester;

    // Set the Academic Year
    public static void setAcademicYear(String academicYear) {
    	GradesButtonSession.academicYear = academicYear;
    }

    // Get Academic Year
    public static String getAcademicYear() {
        return academicYear;
    }
    // Set the Semester
    public static void setSemester(String Semester) {
    	GradesButtonSession.Semester = Semester;
    }

    // Get the Semester
    public static String getSemester() {
        return Semester;
    }

}
