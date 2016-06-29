package com.fasterxml.jackson.perf.protob;

import java.io.IOException;

import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchemaLoader;

public class ProtobufHelper
{
    final protected static String PROTOC_MEDIA_ITEM =
"package serializers.protobuf.media;\n"+
"option java_package = \"serializers.protobuf.media\";\n"+
"option java_outer_classname = \"MediaContentHolder\";\n"+
"option optimize_for = SPEED;\n"+
"\n"+
"message MediaItem {\n"+
"  required Media content = 1;\n"+
"  repeated Image images = 2;\n"+
"}\n"+
"message Image {\n"+
"  required string uri = 1;\n"+
"  optional string title = 2;\n"+
"  required int32 width = 3;\n"+
"  required int32 height = 4;\n"+
"  enum Size {\n"+
"    SMALL = 0;\n"+
"    LARGE = 1;\n"+
"  }\n"+
"  required Size size = 5;\n"+
"}\n"+
"message Media {\n"+
"  required string uri = 1;\n"+
"  optional string title = 2;\n"+
"  required int32 width = 3;\n"+
"  required int32 height = 4;\n"+
"  required string format = 5;\n"+
"  required int64 duration = 6;\n"+
"  required int64 size = 7;\n"+
"  optional int32 bitrate = 8;\n"+
"  repeated string persons = 9;\n"+
"  enum Player {\n"+
"    JAVA = 0;\n"+
"    FLASH = 1;\n"+
"}\n"+
"required Player player = 10;\n"+
"optional string copyright = 11;\n"+
"}\n"
;

    public static ProtobufSchema mediaItemSchema() {
        try {
            return ProtobufSchemaLoader.std.parse(PROTOC_MEDIA_ITEM);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
