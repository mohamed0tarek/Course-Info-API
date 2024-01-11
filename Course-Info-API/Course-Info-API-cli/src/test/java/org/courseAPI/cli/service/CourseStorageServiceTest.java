package org.courseAPI.cli.service;

import org.courseAPI.domain.Course;
import org.courseAPI.repository.CourseReopsitory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseStorageServiceTest {
        @Mock
        CourseReopsitory mockRepository;

        @InjectMocks
        CourseStorageService courseStorageService;

    @Test
    void testStorePluralsightCourses() {

        List<PluralsightCourse> psCourses = List.of(
                new PluralsightCourse("1", "Course 1", "1h", "Intermediate", "/course1"),
                new PluralsightCourse("2", "Course 2", "2h", "Beginner", "/course2")
        );

        courseStorageService.storeCourses(psCourses);

        verify(mockRepository, times(psCourses.size())).saveCourse(any(Course.class));

        verify(mockRepository, times(1)).saveCourse(
                argThat(course -> course.title().equals("Course 1"))
        );
    }
}
