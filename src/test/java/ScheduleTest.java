import datamodel.Course;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class ScheduleTest {
    public static final int NUM_SEMESTERS = 2;

    private Course mockCourse;
    private Schedule schedule;

    @Before
    public void setUp() throws Exception {
        mockCourse = createMock(Course.class);
        schedule = new Schedule(mockCourse, NUM_SEMESTERS);
    }

    @Test
    public void testNoDependencies() throws Exception {
        expect(mockCourse.getPrerequisites()).andReturn(new ArrayList<Course>());
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
    public void testOneDependency() throws Exception {
        final Course otherMockCourse = createMock(Course.class);

        expect(mockCourse.getPrerequisites()).andReturn(newArrayList(otherMockCourse));
        expect(otherMockCourse.getPrerequisites()).andReturn(new ArrayList<Course>());
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
}
