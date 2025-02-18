package acadsyncStudent;

public class SubjectDetails {
	    
	    private String instructor;
	    private String schedule1;
	    private String schedule2;
	    private String schedule3; 
	    private String schedule4;

	    public SubjectDetails(String instructor, String schedule1, String schedule2, String schedule3, String schedule4) {
	        this.instructor = instructor;
	        this.schedule1 = schedule1;
	        this.schedule2 = schedule2;
	        this.schedule3 = schedule3;
	        this.schedule4 = schedule4;
	    }

	    public String getInstructor() {
	        return instructor;
	    }

	    public String getSchedule1() {
	        return schedule1;
	    }

	    public String getSchedule2() {
	        return schedule2;
	    }

	    public String getSchedule3() {
	        return schedule3;
	    }

	    public String getSchedule4() {
	        return schedule4;
	    }
	}

