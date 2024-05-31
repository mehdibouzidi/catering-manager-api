package com.catering.manager.api.business.service.impl;

import com.catering.manager.api.business.service.inter.IFileReader;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileReader implements IFileReader {

    @Override
    public List<String[]> byteArrayToStringList(byte[] bytes) {
        String content = byteArrayToString(bytes);
        String lines[] = content.split("\\r?\\n");
        List<String[]> result = new ArrayList<>();
        for (String line: lines) {
            result.add(line.split(";"));
        }
        return result;
    }

    private String byteArrayToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
