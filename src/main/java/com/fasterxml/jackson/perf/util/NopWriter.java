package com.fasterxml.jackson.perf.util;

import java.io.Writer;

public class NopWriter extends Writer
{
    @Override
    public void write(int ch) { }

    @Override
    public void write(char[] cbuf) { }

    @Override
    public void write(char[] cbuf, int off, int len) { }

    @Override
    public void flush() { }

    @Override
    public void close() { }
}
