package org.courseAPI.repository;

import org.courseAPI.domain.Course;

import java.io.IOException;
import java.sql.*;
import java.util.*;

class CourseJdbcRepository implements CourseReopsitory {

    private static Properties prop = new Properties();
    private static String url, user, pass;

    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CourseJdbcRepository() {
        try {
            prop.load(CourseJdbcRepository.class.getClassLoader().getResourceAsStream("DBinfo.properties"));
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            pass = prop.getProperty("pass");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCourse(Course course) {
        String query = "INSERT INTO COURSES (id, name, length, level, url) VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=VALUES(name), length=VALUES(length), level=VALUES(level), url=VALUES(url)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, course.id());
            ps.setString(2, course.title());
            ps.setString(3, course.duration());
            ps.setString(4, course.level());
            ps.setString(5, course.contentUrl());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("failed to save " + course, e);
        }
    }

    @Override
    public void addNotes(String id, String notes) {
        String query = "update courses set notes=? where id=?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, notes);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException("failed to add the note to " + id, e);
        }
    }

    @Override
    public void deleteCourses(String id) {
        String query = "delete from courses where id=?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException("failed to delete the course of " + id, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String query = "select * from COURSES";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(
                            new Course(rs.getString("id"),
                                    rs.getString("name"),
                                    rs.getString("length"),
                                    rs.getString("level"),
                                    rs.getString("url"),
                                    Optional.ofNullable(rs.getString("notes")))
                    );
                }
            }
            return Collections.unmodifiableList(list);
        } catch (SQLException e) {
            throw new RepositoryException("failed to retrieve courses", e);
        }
    }
}
