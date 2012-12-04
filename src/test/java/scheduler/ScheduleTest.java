package scheduler;

import datamodel.Course;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static util.ModelFactory.createCourse;

public class ScheduleTest {
    public static final int NUM_SEMESTERS = 2;
    public static final int FIRST_SEMESTER = 0;
    public static final int SECOND_SEMESTER = 1;

    private Course mockCourse;
    private Schedule schedule;

    @Before
    public void setUp() {
        mockCourse = createMock(Course.class);
        schedule = new Schedule(mockCourse, NUM_SEMESTERS);
    }

    @Test
    public void testNoDependencies() {
        expect(mockCourse.getPrerequisites()).andReturn(new HashSet<Course>());
        replay(mockCourse);

        schedule.sortDependencies();
        // verify nothing mutated
        assertEquals(mockCourse, schedule.getTargetCourse());
        assertEquals(NUM_SEMESTERS, schedule.getSemesters().length);
        // check sorted course list
        assertEquals(1, schedule.getSortedCourses().size());
        assertEquals(mockCourse, schedule.getSortedCourses().get(0));

        verify(mockCourse);   // verify expected command was called
    }

    @Test
    public void testOneDependency() {
        final Course otherMockCourse = createMock(Course.class);

        expect(mockCourse.getPrerequisites()).andReturn(newHashSet(otherMockCourse));
        expect(otherMockCourse.getPrerequisites()).andReturn(new HashSet<Course>());
        replay(mockCourse, otherMockCourse);

        schedule.sortDependencies();
        // verify nothing mutated
        assertEquals(mockCourse, schedule.getTargetCourse());
        assertEquals(NUM_SEMESTERS, schedule.getSemesters().length);
        // check sorted course list
        assertEquals(2, schedule.getSortedCourses().size());
        assertEquals(otherMockCourse, schedule.getSortedCourses().get(0));
        assertEquals(mockCourse, schedule.getSortedCourses().get(1));

        verify(mockCourse, otherMockCourse);   // verify expected commands were called
    }

    @Test
    public void testOrganizeSemesters() {
        final Course prereq = createCourse();

        expect(mockCourse.getPrerequisites()).andReturn(newHashSet(prereq)).times(2);
        replay(mockCourse);

        schedule.sortDependencies();
        schedule.organizeSemesters();

        assertEquals(1, schedule.getSemester(FIRST_SEMESTER).getSize());
        assertEquals(1, schedule.getSemester(SECOND_SEMESTER).getSize());
        assertEquals(prereq, schedule.getSemester(FIRST_SEMESTER).getCourses().get(0));
        assertEquals(mockCourse, schedule.getSemester(SECOND_SEMESTER).getCourses().get(0));

        verify(mockCourse);
    }
}
