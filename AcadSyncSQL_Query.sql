

/*****************************************************Database and Table for our AcadSync.***************************************************/

-- Database: Student Management System
/*CREATE DATABASE AcadSyncDB;*/




-- AcadSync Admin Table
/*CREATE TABLE AcadSync_Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    AdminNumber INT NOT NULL UNIQUE,
    PasswordHash VARCHAR(255) NOT NULL,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    PhoneNumber VARCHAR(15),
    Role VARCHAR(50) DEFAULT 'Admin',
    LastLog TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Auto-updated log timestamp
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);




-- Academic Year Schedule Table
CREATE TABLE Academic_Year_Schedule (
    AcademicYearID INT PRIMARY KEY AUTO_INCREMENT,
	AcademicYear VARCHAR(150),
    Date DATE NOT NULL,
    Event VARCHAR(500) NOT NULL,
    Semester VARCHAR(50),
    Notes VARCHAR(500)
);



-- Students Table
CREATE TABLE Student_Profile (
    StudentNumber BIGINT PRIMARY KEY, -- Use BIGINT for larger numeric ranges
    AcadSyncEmail VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(100) NULL,
    LastName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(100) NOT NULL,
    MiddleName VARCHAR(100) NOT NULL,
    SuffixName VARCHAR(50) NULL,
    Sex VARCHAR (100) NOT NULL,
    BirthDate DATE NULL,
    Age INT NOT NULL,
    Religion VARCHAR(100),
    CivilStatus VARCHAR(100) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    Guardian VARCHAR(100) NOT NULL,
    ContactNumber VARCHAR(11) NULL,
    Course VARCHAR(50) NOT NULL,
    LastLog TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Auto-updated log timestamp
);



-- Sections and Subjects Table
CREATE TABLE Sections_Subjects (
    SectionID INT AUTO_INCREMENT PRIMARY KEY,
    Section VARCHAR(100) NOT NULL,
    Semester VARCHAR(100) NOT NULL, 
    AcademicYear VARCHAR(100) NOT NULL,
	SchedCode BIGINT NOT NULL,
    SubjectCode VARCHAR(200) NOT NULL,
    Description VARCHAR(200) NOT NULL,
    Unit INT NULL,
	CreditUnit INT NULL,
    Schedule1 VARCHAR(200),
    Schedule2 VARCHAR(200),
    Schedule3 VARCHAR(200),
    Schedule4 VARCHAR(200),
    Instructor VARCHAR(200),
    UNIQUE (Section, Semester, AcademicYear, SubjectCode)
);



-- Enrollments Table
/*CREATE TABLE Enrollment_Status (
    EnrollmentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentNumber BIGINT NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(100) NOT NULL,
    MiddleName VARCHAR(100) NOT NULL,
    SuffixName VARCHAR(50) NULL,
    Section VARCHAR(100) NULL,
    Semester VARCHAR(100) NULL,
    YearLevel VARCHAR(100) NULL,
    AcademicYear VARCHAR(100) NULL,
    Status VARCHAR(100) NULL DEFAULT 'Not Enrolled',
    State VARCHAR(100) NULL DEFAULT 'Regular',
    Type VARCHAR(100) NULL DEFAULT 'New',
    OldSection VARCHAR(150),
    OldSemester VARCHAR(150),
    OldAcademicYear VARCHAR(150),
    FOREIGN KEY (Section, Semester, AcademicYear) 
    REFERENCES Sections_Subjects (Section, Semester, AcademicYear)
    ON DELETE SET NULL);




-- Student Grades Table
CREATE TABLE Student_Grades (
    GradeID INT AUTO_INCREMENT PRIMARY KEY,
    StudentNumber BIGINT NOT NULL,
    AcademicYear VARCHAR(100) NULL,
    SchedCode BIGINT NOT NULL,
    SubjectCode VARCHAR(200) NULL,
    Description VARCHAR(200) NULL,
    Semester VARCHAR(100) NULL,
    Section VARCHAR(100) NULL,
    Grade DECIMAL(4,2),
    Unit INT NULL,
    CreditUnit INT NULL,
	Schedule1 VARCHAR(200) NULL,
    Schedule2 VARCHAR(200) NULL,
    Schedule3 VARCHAR(200) NULL,
    Schedule4 VARCHAR(200) NULL,
    Instructor VARCHAR(200) NULL,
    Completion VARCHAR(100) NULL,
    FOREIGN KEY (Section, Semester, AcademicYear, SubjectCode) 
    REFERENCES Sections_Subjects (Section, Semester, AcademicYear, SubjectCode)
    ON DELETE CASCADE);*/
    
    
    
    
    
    
-- Old Student Grades table to back up the Grade and Completion from Student Grade table
    /*CREATE TABLE OldStudent_Grades (
    OldStudentNumber BIGINT NOT NULL,
    OldAcademicYear VARCHAR(100) NULL,
    OldSchedCode BIGINT NOT NULL,
    OldSubjectCode VARCHAR(200) NULL,
    OldDescription VARCHAR(200) NULL,
    OldSemester VARCHAR(100) NULL,
    OldSection VARCHAR(100) NULL,
    OldGrade DECIMAL(4,2),
    OldUnit INT NULL,
    OldCreditUnit INT NULL,
	OldSchedule1 VARCHAR(200) NULL,
    OldSchedule2 VARCHAR(200) NULL,
    OldSchedule3 VARCHAR(200) NULL,
    OldSchedule4 VARCHAR(200) NULL,
    OldInstructor VARCHAR(200) NULL,
    OldCompletion VARCHAR(100) NULL
    );*/




-- BackUpStudent Grades table
/*CREATE TABLE BackUpStudent_Grades (
    BackUpStudentID INT PRIMARY KEY AUTO_INCREMENT,
    BackUpStudentNumber BIGINT NOT NULL,
    BackUpAcademicYear VARCHAR(100) NULL,
    BackUpSchedCode BIGINT NOT NULL,
    BackUpSubjectCode VARCHAR(200) NULL,
    BackUpDescription VARCHAR(200) NULL,
    BackUpSemester VARCHAR(100) NULL,
    BackUpSection VARCHAR(100) NULL,
    BackUpGrade DECIMAL(4,2),
    BackUpUnit INT NULL,
    BackUpCreditUnit INT NULL,
	BackUpSchedule1 VARCHAR(200) NULL,
    BackUpSchedule2 VARCHAR(200) NULL,
    BackUpSchedule3 VARCHAR(200) NULL,
    BackUpSchedule4 VARCHAR(200) NULL,
    BackUpInstructor VARCHAR(200) NULL,
    BackUpCompletion VARCHAR(100) NULL
    );*/



/********************************************************************************************************************************************/






/***************************************************Trigger for our Enrollment_Status table.*************************************************/



/*DELIMITER $$

CREATE TRIGGER AfterEnrollmentInsert
AFTER INSERT ON Enrollment_Status
FOR EACH ROW
BEGIN
    -- Ensure all columns are included, and provide default values if needed
    INSERT INTO Student_Grades (
        StudentNumber, 
        AcademicYear, 
        Semester, 
        Section, 
        SchedCode, 
        SubjectCode, 
        Description, 
        Unit, 
        CreditUnit, 
        Schedule1, 
        Schedule2, 
        Schedule3, 
        Schedule4, 
        Instructor,
        Grade, 
        Completion
    )
    SELECT 
        NEW.StudentNumber, 
        ss.AcademicYear, 
        ss.Semester, 
        ss.Section, 
        ss.SchedCode, 
        ss.SubjectCode, 
        ss.Description, 
        ss.Unit, 
        ss.CreditUnit, 
        ss.Schedule1, 
        ss.Schedule2, 
        ss.Schedule3, 
        ss.Schedule4, 
        ss.Instructor, 
        NULL AS Grade,          -- Default value for Grade
        NULL AS Completion -- Default value for Completion
    FROM Sections_Subjects ss
    WHERE ss.Section = NEW.Section
      AND ss.Semester = NEW.Semester
      AND ss.AcademicYear = NEW.AcademicYear;
END$$

DELIMITER ;*/





/*
DELIMITER $$

CREATE TRIGGER BeforeEnrollmentUpdate
BEFORE UPDATE ON Enrollment_Status
FOR EACH ROW
BEGIN
    -- Backup all the current data from Student_Grades into OldStudent_Grade
    INSERT INTO OldStudent_Grades (
        OldStudentNumber, 
        OldAcademicYear, 
        OldSchedCode, 
        OldSubjectCode, 
        OldDescription, 
        OldSemester, 
        OldSection, 
        OldGrade, 
        OldUnit, 
        OldCreditUnit, 
        OldSchedule1, 
        OldSchedule2, 
        OldSchedule3, 
        OldSchedule4, 
        OldInstructor, 
        OldCompletion
    )
    SELECT 
        sg.StudentNumber,
        sg.AcademicYear,
        sg.SchedCode,
        sg.SubjectCode,
        sg.Description,
        sg.Semester,
        sg.Section,
        sg.Grade,
        sg.Unit,
        sg.CreditUnit,
        sg.Schedule1,
        sg.Schedule2,
        sg.Schedule3,
        sg.Schedule4,
        sg.Instructor,
        sg.Completion
    FROM Student_Grades sg
    WHERE sg.StudentNumber = OLD.StudentNumber
      AND sg.Section = OLD.Section
      AND sg.Semester = OLD.Semester
      AND sg.AcademicYear = OLD.AcademicYear;
END $$

DELIMITER ;*/







/*DELIMITER $$

CREATE TRIGGER AfterEnrollmentUpdate
AFTER UPDATE ON Enrollment_Status
FOR EACH ROW
BEGIN
    -- Remove old subjects from Student_Grades
    DELETE FROM Student_Grades
    WHERE StudentNumber = OLD.StudentNumber
      AND Section = OLD.Section
      AND Semester = OLD.Semester
      AND AcademicYear = OLD.AcademicYear;

    -- Add new subjects into Student_Grades from Sections_Subjects
    INSERT INTO Student_Grades (
        StudentNumber, 
        AcademicYear, 
        Semester, 
        Section, 
        SchedCode, 
        SubjectCode, 
        Description, 
        Unit, 
        CreditUnit, 
        Schedule1, 
        Schedule2, 
        Schedule3, 
        Schedule4, 
        Instructor
    )
    SELECT 
        NEW.StudentNumber,
        ss.AcademicYear,
        ss.Semester,
        ss.Section,
        ss.SchedCode,
        ss.SubjectCode,
        ss.Description,
        ss.Unit,
        ss.CreditUnit,
        ss.Schedule1,
        ss.Schedule2,
        ss.Schedule3,
        ss.Schedule4,
        ss.Instructor
    FROM Sections_Subjects ss
    WHERE ss.Section = NEW.Section
      AND ss.Semester = NEW.Semester
      AND ss.AcademicYear = NEW.AcademicYear;

		-- Update Grade and Completion from OldStudent_Grades to Student_Grades
		UPDATE Student_Grades sg
		JOIN OldStudent_Grades osg
			ON sg.StudentNumber = NEW.StudentNumber
			AND sg.Section = osg.OldSection
			AND sg.Semester = osg.OldSemester
			AND sg.AcademicYear = osg.OldAcademicYear
			AND sg.SubjectCode = osg.OldSubjectCode
		SET 
			sg.Grade = osg.OldGrade,
			sg.Completion = osg.OldCompletion;

		-- Delete corresponding records from OldStudent_Grades
		DELETE FROM OldStudent_Grades
		WHERE OldStudentNumber = NEW.StudentNumber
		  AND OldSection = NEW.Section
		  AND OldSemester = NEW.Semester
		  AND OldAcademicYear = NEW.AcademicYear;

END $$

DELIMITER ;*/







/*DELIMITER $$

CREATE TRIGGER AfterEnrollmentDelete
AFTER DELETE ON Enrollment_Status
FOR EACH ROW
BEGIN
    -- Delete corresponding rows from Student_Grades
    DELETE FROM Student_Grades
    WHERE StudentNumber = OLD.StudentNumber
      AND Section = OLD.Section
      AND Semester = OLD.Semester
      AND AcademicYear = OLD.AcademicYear;
END $$

DELIMITER ;*/




/********************************************************************************************************************************************/




/***************************************************Trigger for our Sections_Subjects table.*************************************************/


/*DELIMITER $$

CREATE TRIGGER AfterSubjectInsert
AFTER INSERT ON Sections_Subjects
FOR EACH ROW
BEGIN
    -- Insert into Student_Grades for unique students matching the section, semester, and academic year
    -- Ensure no duplicate subjects are added
    INSERT INTO Student_Grades (
        StudentNumber,
        AcademicYear,
        Semester,
        Section,
        SchedCode,
        SubjectCode,
        Description,
        Unit,
        CreditUnit,
        Schedule1,
        Schedule2,
        Schedule3,
        Schedule4,
        Instructor
    )
    SELECT 
        sg.StudentNumber, 
        NEW.AcademicYear, 
        NEW.Semester, 
        NEW.Section, 
        NEW.SchedCode, 
        NEW.SubjectCode, 
        NEW.Description, 
        NEW.Unit, 
        NEW.CreditUnit, 
        NEW.Schedule1, 
        NEW.Schedule2, 
        NEW.Schedule3, 
        NEW.Schedule4, 
        NEW.Instructor
    FROM (SELECT DISTINCT StudentNumber, Section, Semester, AcademicYear FROM Student_Grades) sg
    WHERE sg.Section = NEW.Section
      AND sg.Semester = NEW.Semester
      AND sg.AcademicYear = NEW.AcademicYear
      AND NOT EXISTS (
          SELECT 1
          FROM Student_Grades
          WHERE StudentNumber = sg.StudentNumber
            AND Section = NEW.Section
            AND Semester = NEW.Semester
            AND AcademicYear = NEW.AcademicYear
            AND SubjectCode = NEW.SubjectCode
      );
END$$

DELIMITER ;
*/




/*DELIMITER $$

CREATE TRIGGER Update_student_grades_trigger
AFTER UPDATE ON Sections_Subjects
FOR EACH ROW
BEGIN
    -- Update student grades for existing students when section/subject details are updated
    UPDATE Student_Grades
    SET SchedCode = NEW.SchedCode,
        SubjectCode = NEW.SubjectCode,
        Description = NEW.Description,
        Semester = NEW.Semester,
        Section = NEW.Section,
        AcademicYear = NEW.AcademicYear,
        Unit = NEW.Unit,
        CreditUnit = NEW.CreditUnit,
        Schedule1 = NEW.Schedule1,
        Schedule2 = NEW.Schedule2,
        Schedule3 = NEW.Schedule3,
        Schedule4 = NEW.Schedule4,
        Instructor = NEW.Instructor
    WHERE Section = OLD.Section
      AND Semester = OLD.Semester
      AND AcademicYear = OLD.AcademicYear
      AND SubjectCode = OLD.SubjectCode;

    -- Insert new student grades when a subject is reassigned to another section
    IF NOT EXISTS (
        SELECT 1
        FROM Student_Grades
        WHERE Section = NEW.Section
          AND Semester = NEW.Semester
          AND AcademicYear = NEW.AcademicYear
          AND SubjectCode = NEW.SubjectCode
    ) THEN
        INSERT INTO Student_Grades (StudentNumber, AcademicYear, SchedCode, SubjectCode, Description, Semester, Section, Unit, CreditUnit, Schedule1, Schedule2, Schedule3, Schedule4, Instructor)
        SELECT StudentNumber, NEW.AcademicYear, NEW.SchedCode, NEW.SubjectCode, NEW.Description, NEW.Semester, NEW.Section, NEW.Unit, NEW.CreditUnit, NEW.Schedule1, NEW.Schedule2, NEW.Schedule3, NEW.Schedule4, NEW.Instructor
        FROM Student_Grades
        WHERE Section = OLD.Section
          AND Semester = OLD.Semester
          AND AcademicYear = OLD.AcademicYear;
    END IF;
END$$

DELIMITER ;
*/




/*DELIMITER $$

CREATE TRIGGER Backup_And_Handle_Before_Delete
BEFORE DELETE ON Sections_Subjects
FOR EACH ROW
BEGIN
    -- Backup original values for related rows in Enrollment_Status
    UPDATE Enrollment_Status
    SET OldSection = Section,   -- Save current Section
        OldSemester = Semester, -- Save current Semester
        OldAcademicYear = AcademicYear -- Save current AcademicYear
    WHERE Section = OLD.Section
      AND Semester = OLD.Semester
      AND AcademicYear = OLD.AcademicYear;
      
	-- Handle related rows in Student_Grades (if needed)
    DELETE FROM Student_Grades
    WHERE Section = OLD.Section
      AND Semester = OLD.Semester
      AND AcademicYear = OLD.AcademicYear;
      
END$$

DELIMITER ;*/





/*DELIMITER $$

CREATE TRIGGER After_delete_sections_subjects
AFTER DELETE ON Sections_Subjects
FOR EACH ROW
BEGIN
        -- Restore values from OldSection, OldSemester, and OldAcademicYear if available
        UPDATE Enrollment_Status
        SET Section = OldSection,
            Semester = OldSemester,
            AcademicYear = OldAcademicYear
        WHERE OldSection IS NOT NULL
          AND OldSemester IS NOT NULL
          AND OldAcademicYear IS NOT NULL
          AND Section IS NULL
          AND Semester IS NULL
          AND AcademicYear IS NULL;
END $$

DELIMITER ;*/





/********************************************************************************************************************************************/




/***************************************************Trigger for our Student_Grades table.*************************************************/


/*DELIMITER $$

CREATE TRIGGER After_student_grades_delete
AFTER DELETE ON Student_Grades
FOR EACH ROW
BEGIN

    -- Back up all remaining data from Student_Grades into BackUpStudent_Grades
    INSERT INTO BackUpStudent_Grades (
        BackUpStudentNumber, 
        BackUpAcademicYear, 
        BackUpSchedCode, 
        BackUpSubjectCode, 
        BackUpDescription, 
        BackUpSemester, 
        BackUpSection, 
        BackUpGrade, 
        BackUpUnit, 
        BackUpCreditUnit, 
        BackUpSchedule1, 
        BackUpSchedule2, 
        BackUpSchedule3, 
        BackUpSchedule4, 
        BackUpInstructor, 
        BackUpCompletion
    )
    SELECT 
        StudentNumber, 
        AcademicYear, 
        SchedCode, 
        SubjectCode, 
        Description, 
        Semester, 
        Section, 
        Grade, 
        Unit, 
        CreditUnit, 
        Schedule1, 
        Schedule2, 
        Schedule3, 
        Schedule4, 
        Instructor, 
        Completion
    FROM Student_Grades;
END$$

DELIMITER ;




/********************************************************************************************************************************************/






























 







