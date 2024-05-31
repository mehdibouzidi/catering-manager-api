package com.catering.manager.api.business.service.inter;

import java.util.List;

public interface IFileReader {

    List<String[]>  byteArrayToStringList(byte[] bytes);
}
