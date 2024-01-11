package org.courseAPI.domain;

import java.util.Optional;

public record Course(String id, String title, String duration, String level, String contentUrl, Optional<String> notes) {
}
