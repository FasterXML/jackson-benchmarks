package com.fasterxml.jackson.perf.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JsonArbitraryFieldNameBenchmark {

    public enum FactoryMode {
        DEFAULT() {
            @Override
            <F extends JsonFactory, B extends TSFBuilder<F, B>> B apply(B factory) {
                return factory;
            }
        },
        NO_INTERN() {
            @Override
            <F extends JsonFactory, B extends TSFBuilder<F, B>> B apply(B factory) {
                return factory.disable(JsonFactory.Feature.INTERN_FIELD_NAMES);
            }
        },
        NO_CANONICALIZE() {
            @Override
            <F extends JsonFactory, B extends TSFBuilder<F, B>> B apply(B factory) {
                return factory.disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
            }
        };

        abstract <F extends JsonFactory, B extends TSFBuilder<F, B>> B apply(B factory);
    }

    /**
     * Ideally we would not generate inputs within the measured component of the benchmark.
     */
    public enum InputType {
        INPUT_STREAM() {
            @Override
            JsonParser create(JsonFactory factory, Supplier<String> jsonSupplier) throws IOException {
                return factory.createParser(new ByteArrayInputStream(jsonSupplier.get().getBytes(StandardCharsets.UTF_8)));
            }
        },
        READER() {
            @Override
            JsonParser create(JsonFactory factory, Supplier<String> jsonSupplier) throws IOException {
                // Instead of using 'new StringReader(jsonSupplier.get())', we construct an InputStreamReader
                // to more closely match overhead of INPUT_STREAM for comparison.
                return factory.createParser(new InputStreamReader(
                        new ByteArrayInputStream(jsonSupplier.get().getBytes(StandardCharsets.UTF_8)),
                        StandardCharsets.UTF_8));
            }
        };

        abstract JsonParser create(JsonFactory factory, Supplier<String> jsonSupplier) throws IOException;
    }

    public enum InputShape {
        RANDOM_KEY_MAP(
                new TypeReference<Map<String, Boolean>>() {},
                () -> "{\"" + ThreadLocalRandom.current().nextInt() + "\":true}"),
        BEAN_WITH_RANDOM_KEY_MAP(
                new TypeReference<SimpleClass>() {},
                () -> "{\"fieldWithMap\":{\"" + ThreadLocalRandom.current().nextInt()
                        + "\":true},\"stringOne\":\"a\",\"stringTwo\":\"a\",\"stringThree\":\"a\"}");

        private final TypeReference<?> typereference;
        private final Supplier<String> jsonSupplier;
        InputShape(TypeReference<?> typereference, Supplier<String> jsonSupplier) {
            this.typereference = typereference;
            this.jsonSupplier = jsonSupplier;
        }
    }

    @Param
    public InputShape shape;

    @Param
    public InputType type;

    @Param
    public FactoryMode mode;

    private JsonFactory factory;
    private ObjectReader reader;

    @Setup
    public void setup() {
        factory = mode.apply(new JsonFactoryBuilder()).build();
        ObjectMapper mapper = JsonMapper.builder(factory)
                // Use FAIL_ON_UNKNOWN_PROPERTIES to ensure the benchmark inputs are valid
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
        reader = mapper.readerFor(shape.typereference);
    }

    @Benchmark
    public Object parse() throws IOException {
        try (JsonParser parser = type.create(factory, shape.jsonSupplier)) {
            return reader.readValue(parser);
        }
    }

    /**
     * This type primarily exists to wrap a map, but has additional
     * fields to cover a mix of reused and arbitrary json keys.
     */
    public static final class SimpleClass {
        @JsonProperty("fieldWithMap")
        public Map<String, Boolean> fieldWithMap;
        @JsonProperty("stringOne")
        public String stringOne;
        @JsonProperty("stringTwo")
        public String stringTwo;
        @JsonProperty("stringThree")
        public String stringThree;
    }
}
