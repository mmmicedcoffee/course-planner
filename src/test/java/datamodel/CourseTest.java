package datamodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static util.ModelFactory.*;

public class CourseTest {

    private Course course;

    @Before
    public void setUp() throws Exception {
        course = createCourse();
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(NAME, course.getName());
    }

    @Test
    public void testGetPrerequisites() throws Exception {
        assertEquals(PREREQUISITES, course.getPrerequisites());
    }
}
