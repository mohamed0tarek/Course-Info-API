package org.courseAPI.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.courseAPI.domain.Course;
import org.courseAPI.repository.CourseReopsitory;
import org.courseAPI.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseReopsitory courseReopsitory;

    public CourseResource(CourseReopsitory courseReopsitory){
        this.courseReopsitory = courseReopsitory;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(){
        try {
            return courseReopsitory.getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id))
                    .toList();
        } catch (RepositoryException e){
            LOG.error("Can not retrieve courses from the database ", e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}/delete")
    public void deleteCourse(@PathParam("id") String id){
        courseReopsitory.deleteCourses(id);
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNote(@PathParam("id") String id, String note){
        courseReopsitory.addNotes(id, note);
    }

    @GET
    @Path("/add-course")
    public void addCourse(){
        String id, title, duration, level, URL;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course ID : ");
        id = scanner.nextLine();
        System.out.print("Enter course title : ");
        title = scanner.nextLine();
        System.out.print("Enter course duration : ");
        duration = scanner.nextLine();
        System.out.print("Enter course level : ");
        level = scanner.nextLine();
        System.out.print("Enter course URL : ");
        URL = scanner.nextLine();
        Course course = new Course(id, title, duration, level, URL, null);
        courseReopsitory.saveCourse(course);
        System.out.println("save courses called ok");
    }
}
