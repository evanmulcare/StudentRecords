package com.mtu.studentrecords.TestSuite;

import com.mtu.studentrecords.Controller.StudentController;
import com.mtu.studentrecords.Model.Student;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNull;


public class ValidateStudentTest {

    private static StudentController studentCntrl;

    @BeforeClass
    public static void setUp() {
        studentCntrl = new StudentController();
    }

    @Test
    public void emptyField() {

        Student s2 = studentCntrl.studentValidation("", "Jane", "Doe", "jane.doe@example.com", "Female", "01/01/1995");
        assertNull(s2);

    }

    @Test
    public void existingStudentID() {
        Student s3 = studentCntrl.studentValidation("1", "Jane", "Doe", "jane.doe@example.com", "Female", "01/01/1995");
        assertNull(s3);
    }

    @Test
    public void invalidFirstName() {
        Student s4 = studentCntrl.studentValidation("2", "J", "Doe", "jane.doe@example.com", "Female", "01/01/1995");
        assertNull(s4);
    }

    @Test
    public void invalidLastName() {
        Student s5 = studentCntrl.studentValidation("3", "John", "D", "john.doe@example.com", "Male", "01/01/1990");
        assertNull(s5);
    }

    @Test
    public void invalidEmail() {
        Student s6 = studentCntrl.studentValidation("4", "John", "Doe", "johndoe", "Male", "01/01/1990");
        assertNull(s6);
    }

    @Test
    public void noGenderSelected() {
        Student s7 = studentCntrl.studentValidation("5", "John", "Doe", "john.doe@example.com", null, "01/01/1990");
        assertNull(s7);
    }

    @Test
    public void invalidDOB() {
        Student s8 = studentCntrl.studentValidation("6", "John", "Doe", "john.doe@example.com", "Male", "01-01-1990");
        assertNull(s8);
    }


}
