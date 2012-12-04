package util;

import datamodel.Course;

import java.util.ArrayList;

import static com.google.common.collect.Lists.newArrayList;

public class ModelFactory {
    public static final String NAME = "test";
    public static final ArrayList<Course> PREREQUISITES = newArrayList();

    public static Course createCourse() {
        return new Course(NAME, PREREQUISITES);
    }
}
