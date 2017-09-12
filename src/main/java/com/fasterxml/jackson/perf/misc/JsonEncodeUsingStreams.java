package com.fasterxml.jackson.perf.misc;

import java.io.IOException;
import java.io.Writer;

public class JsonEncodeUsingStreams extends JsonEncodeBase
{
    @Override
    protected void encode(StringBuilder builder, String[] values) throws Exception {
        builder.append('[');
        _encode(builder, values[0]);
        for (int i = 0, len = values.length; i < len; ++i) {
            _encode(builder, values[i]);
        }
        builder.append(']');
    }

    @Override
    protected void encode(Writer w, String[] values) throws Exception {
        w.write('[');
        _encode(w, values[0]);
        for (int i = 0, len = values.length; i < len; ++i) {
            _encode(w, values[i]);
        }
        w.write(']');
    }

    @Override
    protected void encode(StringBuilder builder, String value) throws Exception {
        builder.append('[');
        _encode(builder, value);
        builder.append(']');
    }

    @Override
    protected void encode(Writer w, String value) throws Exception {
        w.write('[');
        _encode(w, value);
        w.write(']');
    }
    
    private final void _encode(StringBuilder builder, String value) {
        value.chars().forEachOrdered((eachChar) -> {
            if ('\"' == eachChar) {
                builder.append("\\\"");
            } else if ('\\' == eachChar) {
                builder.append("\\\\");
            } else if ('/' == eachChar) {
                builder.append("\\/");
            } else if ('\b' == eachChar) {
                builder.append("\\b");
            } else if ('\f' == eachChar) {
                builder.append("\\f");
            } else if ('\n' == eachChar) {
                builder.append("\\n");
            } else if ('\r' == eachChar) {
                builder.append("\\r");
            } else if ('\t' == eachChar) {
                builder.append("\\t");
            } else {
                builder.append((char)eachChar);
            }
        });
    }

    private final void _encode(Writer w, String value) {
        value.chars().forEachOrdered((eachChar) -> {
            try {
                if ('\"' == eachChar) {
                    w.write("\\\"");
                } else if ('\\' == eachChar) {
                    w.write("\\\\");
                } else if ('/' == eachChar) {
                    w.write("\\/");
                } else if ('\b' == eachChar) {
                    w.write("\\b");
                } else if ('\f' == eachChar) {
                    w.write("\\f");
                } else if ('\n' == eachChar) {
                    w.write("\\n");
                } else if ('\r' == eachChar) {
                    w.write("\\r");
                } else if ('\t' == eachChar) {
                    w.write("\\t");
                } else {
                    w.write((char)eachChar);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
