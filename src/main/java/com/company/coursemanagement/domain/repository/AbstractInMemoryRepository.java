package com.company.coursemanagement.domain.repository;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractInMemoryRepository {
    private final AtomicLong sequence = new AtomicLong(0);

    protected Long nextId() {
        return sequence.incrementAndGet();
    }
}
