package datamodel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static util.ModelFactory.createCourse;

public class SemesterTest {
    private ArrayList<Course> courseList;
    private Semester semester;

    @Before
    public void before() {
        courseList = newArrayList();
        semester = new Semester(courseList);
    }

    @Test
    public void testAddCourse() throws Exception {
        assertEquals(0, semester.getSize());
        semester.addCourse(createCourse());
        assertEquals(1, semester.getSize());
    }

    @Test
    public void testGetCourses() throws Exception {
        assertEquals(courseList, semester.getCourses());
    }

    @Test
    public void testIterator() throws Exception {
        assertFalse(semester.iterator().hasNext());
        final Course course = createCourse();
        semester.addCourse(course);
        assertTrue(semester.iterator().hasNext());
        assertEquals(course, semester.iterator().next());
    }
}
