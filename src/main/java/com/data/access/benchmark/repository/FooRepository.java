package com.data.access.benchmark.repository;

import com.data.access.benchmark.entity.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FooRepository extends JpaRepository<Foo, Long> {

    List<Foo> findByFirstNameAndLastName(String firstName, String lastName);
}