package com.fasterxml.jackson.perf.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * Helper class used to force conditional "as-array" serialization
 * without requiring value classes to be annotated.
 */
public class AsArrayIntrospector extends JacksonAnnotationIntrospector
{
    private static final long serialVersionUID = 1L;

    @Override
    public JsonFormat.Value findFormat(MapperConfig<?> config, Annotated ann) {
        // 2.4 frowns upon trying to use this for Enums, so avoid those
        // also, limit to just claiming classes (POJOs) require it, not properties
        if (ann instanceof AnnotatedClass) {
            AnnotatedClass ac = (AnnotatedClass) ann;
            if (ac.getAnnotated().isEnum()) {
                return null;
            }
            return JsonFormat.Value.forShape(JsonFormat.Shape.ARRAY);
        }
        return null;
    }
}