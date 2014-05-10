package com.fasterxml.jackson.perf.data;

import java.io.*;

public enum InputData
{
    CITM_CATALOG_WS("json/citm_catalog_ws.json"),
    WEBXML_WS("json/webxml_ws.json"),
    MENU_WS("json/menu_ws.json"),
    ;

    private final byte[] _json;

    private InputData(String filename) {
        try {
            _json = _read(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] bytes() { return _json; }
    
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
