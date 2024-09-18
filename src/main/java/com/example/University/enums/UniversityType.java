package com.example.University.enums;


import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

/**
 * Mobile App Type Enum.
 */
@Log4j2
public enum UniversityType {
    PUBLIC(1, "Public University"),
    PRIVATE(2, "Private University"),
    NATIONAL(3, "National University"),
    INTERNATIONAL(4, "International University");



    private final long id;
    private final String type;

    UniversityType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public static UniversityType getByType(String type) {
        return Arrays.stream(UniversityType.values())
                .filter(appType -> appType.type.equals(type))
                .findAny()
                .orElse(null);
    }

    public static UniversityType getById(long id) {
        return Arrays.stream(UniversityType.values())
                .filter(appType -> appType.id == id)
                .findAny()
                .orElse(null);
    }



    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }


}

