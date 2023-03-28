package com.mtu.studentrecords.TestSuite;

import com.mtu.studentrecords.Controller.ModuleController;
import com.mtu.studentrecords.Model.Module;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class ValidateModuleTest {

    private static ModuleController moduleCntrl;

    @BeforeClass

    public static void setUp()
    {
        moduleCntrl = new ModuleController();
    }

    @Test
    public void emptyFields() {
        // Test empty title and CRN fields
        Module m1 = moduleCntrl.validateModule("", "");
        assertNull(m1);
    }

    @Test
    public void invalidTitle() {
        // Test invalid title (contains non-alphabetic characters)
        Module m2 = moduleCntrl.validateModule("123 Title", "12345");
        assertNull(m2);
    }

    @Test
    public void invalidTitleLength() {
        // Test invalid title length (less than 2 characters)
        Module m3 = moduleCntrl.validateModule("A", "12345");
        assertNull(m3);
    }

}
