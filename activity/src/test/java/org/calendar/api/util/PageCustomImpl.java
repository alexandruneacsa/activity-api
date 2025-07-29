package org.calendar.api.util;

import java.util.List;

public record PageCustomImpl<T>(List<T> content, Integer totalElements, Integer totalPages, Integer size) {}
