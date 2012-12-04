package util;

import datamodel.Course;

import java.util.HashSet;

import static com.google.common.collect.Sets.newHashSet;

public class ModelFactory {
    public static final String NAME = "test";
    public static final HashSet<Course> PREREQUISITES = newHashSet();

    public static Course createCourse() {
        return new Course(NAME, PREREQUISITES);
    }
}
