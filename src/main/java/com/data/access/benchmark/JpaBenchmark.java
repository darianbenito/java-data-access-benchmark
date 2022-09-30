package com.data.access.benchmark;

import com.data.access.benchmark.entity.Foo;
import com.data.access.benchmark.repository.FooRepository;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class JpaBenchmark {

    @Autowired
    public static FooRepository fooRepository;
    @State(Scope.Benchmark)
    public static class BenchmarkState {



        @Param({"100000", "10000000", "1000000000"})
        public int dataSize;

        public List<Foo> data;

        @Setup(Level.Trial)
        public void setUp() {
            data = new ArrayList<>();

            for (int i = 0; i < dataSize; ++i) {
                Foo foo = new Foo();

                foo.setFirstName(UUID.randomUUID().toString());
                foo.setLastName(UUID.randomUUID().toString());
                data.add(foo);
            }
        }
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public List<Foo> findAllData(BenchmarkState benchmarkState) {
        return fooRepository.findAll();
    }

    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void loadData(BenchmarkState benchmarkState)
    {
        fooRepository.saveAll(benchmarkState.data);
    }
}
