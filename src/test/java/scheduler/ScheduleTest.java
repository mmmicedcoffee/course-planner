package scheduler;

import datamodel.Course;
import org.junit.Test;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static util.ModelFactory.NAME;
import static util.ModelFactory.createCourse;

public class ScheduleTest {
    public static final int NUM_SEMESTERS = 2;
    public static final int FIRST_SEMESTER = 0;
    public static final int SECOND_SEMESTER = 1;

    private Course course;
    private Schedule schedule;

    @Test
    public void testNoDependencies() {
        course = createCourse();
        schedule = new Schedule(course, NUM_SEMESTERS);

        schedule.sortDependencies();
        // verify nothing mutated
        assertEquals(course, schedule.getTargetCourse());
        assertEquals(NUM_SEMESTERS, schedule.getSemesters().length);
        // check sorted course list
        assertEquals(1, schedule.getSortedCourses().size());
        assertEquals(course, schedule.getSortedCourses().get(0));
    }

    @Test
    public void testOneDependency() {
        final Course prereq = createCourse();
        course = new Course(NAME, newHashSet(prereq));
        schedule = new Schedule(course, NUM_SEMESTERS);

        schedule.sortDependencies();
        // verify nothing mutated
        assertEquals(course, schedule.getTargetCourse());
        assertEquals(NUM_SEMESTERS, schedule.getSemesters().length);
        // check sorted course list
        assertEquals(2, schedule.getSortedCourses().size());
        assertEquals(prereq, schedule.getSortedCourses().get(0));
        assertEquals(course, schedule.getSortedCourses().get(1));
    }

    @Test
    public void testOrganizeSemesters() {
        final Course prereq = createCourse();
        course = new Course(NAME, newHashSet(prereq));
        schedule = new Schedule(course, NUM_SEMESTERS);

        schedule.sortDependencies();
        schedule.organizeSemesters();

        assertEquals(1, schedule.getSemester(FIRST_SEMESTER).getSize());
        assertEquals(1, schedule.getSemester(SECOND_SEMESTER).getSize());
        assertEquals(prereq, schedule.getSemester(FIRST_SEMESTER).getCourses().get(0));
        assertEquals(course, schedule.getSemester(SECOND_SEMESTER).getCourses().get(0));
    }
}
