package com.fasterxml.jackson.perf;

import java.io.*;

public enum TestFiles
{
    CITM_CATALOG_WS("citm_catalog_ws.json"),
    WEBXML_WS("webxml_ws.json"),
    MENU_WS("menu_ws.json"),
    ;

    private final byte[] _json;
    
    private TestFiles(String filename) {
        try {
            _json = _read(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] jsonBytes() { return _json; }
    
    private static byte[] _read(String filename) throws IOException
    {
        File f = new File(filename);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(5000);
        byte[] buf = new byte[4000];
        int count;
        FileInputStream in = new FileInputStream(f);
        
        while ((count = in.read(buf)) > 0) {
            bytes.write(buf, 0, count);
        }
        in.close();
        bytes.close();
        return bytes.toByteArray();
    }
}
