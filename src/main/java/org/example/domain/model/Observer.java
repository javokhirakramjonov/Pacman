package org.example.domain.model;

public interface Observer<T> {
    void observe(T data);
}
