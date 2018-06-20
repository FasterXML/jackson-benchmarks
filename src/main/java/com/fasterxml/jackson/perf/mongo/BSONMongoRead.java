package com.fasterxml.jackson.perf.mongo;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bson.BSONDecoder;
import org.bson.BasicBSONDecoder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
public class BSONMongoRead
{
    private final byte[] _bsonContent;

    private final BasicBSONDecoder _bsonDecoder;

    public BSONMongoRead() {
        _bsonContent = MongoContentHelper.instance.mediaItemAsBSON();
        _bsonDecoder = new BasicBSONDecoder();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(_bsonContent, _bsonDecoder));
    }

    private final Map<?,?> read(byte[] content, BSONDecoder dec) {
        return (Map<?,?>) dec.readObject(content);
    }
}
