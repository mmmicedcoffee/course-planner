package datamodel;

import org.junit.Before;
import org.junit.Test;
import util.ModelFactory;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
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
    public void testAddCourse() {
        assertEquals(0, semester.getSize());
        semester.addCourse(createCourse());
        assertEquals(1, semester.getSize());
    }

    @Test
    public void testHasConflict() {
        final Course prereq = createCourse();
        final Course course = new Course(ModelFactory.NAME, newHashSet(prereq));

        semester.addCourse(prereq);
        assertTrue(semester.hasConflict(course));
    }

    @Test
    public void testGetCourses() {
        assertEquals(courseList, semester.getCourses());
    }

    @Test
    public void testIterator() {
        assertFalse(semester.iterator().hasNext());
        final Course course = createCourse();
        semester.addCourse(course);
        assertTrue(semester.iterator().hasNext());
        assertEquals(course, semester.iterator().next());
    }
}
