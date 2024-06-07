package com.catering.manager.api.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CommonUtil {

    public static byte[] multipartFileToBytes(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                return file.getBytes();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    public static Integer calculateNumberOfPages(Integer totalNumberOfElements, Integer numberOfEltsPerPage){
        double doubleDiv = (double) totalNumberOfElements / (double) numberOfEltsPerPage;
        Integer numberOfPages = (int) doubleDiv;
        if(doubleDiv - (double) numberOfPages > 0){numberOfPages+=1;}
        return numberOfPages;
    }
}