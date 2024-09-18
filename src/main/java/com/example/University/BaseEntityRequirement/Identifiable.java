package com.example.University.BaseEntityRequirement;

/**
 * Interface for Identifiable.
 */
public interface Identifiable<T> {
    T getId();

    void setId(T id);
}
