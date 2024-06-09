package com.catering.manager.api.common.util;

import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.common.mapper.IMapper;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.common.constant.CommonConstants;
import com.catering.manager.api.common.criteria.PaginationCriteria;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public static String cleanQueryConditions(String query){
        if(query.substring(query.length()-4, query.length()).contains("AND")){
            query=query.substring(0,query.length()-4);
        }
        return query;
    }

    public static String selectCritQueryBuilder(String entityName, Map<String,String> columnsValues, PaginationCriteria criteria) {

        String queryStr = String.format("SELECT en from %s en ", entityName);
                if(!columnsValues.isEmpty()){
                    queryStr+=" WHERE ";
                    for (Map.Entry<String, String> entry : columnsValues.entrySet()) {
                        queryStr+= " LOWER(en."+ entry.getKey()+ ") LIKE '%"+entry.getValue()+"%' AND ";
                    }
                    queryStr = CommonUtil.cleanQueryConditions(queryStr);
                }
        String orderBy = (Strings.isBlank(criteria.getSortColumn())) ? "id": criteria.getSortColumn();
        String sortType = (Strings.isBlank(criteria.getSortColumn())) ? " ASC" : " "+criteria.getSort();
        queryStr+=(" ORDER BY en."+ orderBy + sortType);
        return queryStr;
    }

    public static <T,S> GlobalPayload<T> globalPayloadBuilder(UnitCriteria criteria, Pageable paging, List<S> entityResultList, IMapper<T,S> mapper) {
        GlobalPayload<T> globalPayload = new GlobalPayload();

        int totalNumberOfElements = entityResultList.size();
        List<T> result = Arrays.asList();
        if(totalNumberOfElements>0){
            final int start = (int) paging.getOffset();
            final int end = Math.min((start + paging.getPageSize()), entityResultList.size());

            Page<S> entityPage = new PageImpl<>(entityResultList.subList(start,end), paging, criteria.getSize());

            result = (List<T>) mapper.entityListToPayload(entityPage.getContent());
        }

        globalPayload.setElements(result);
        globalPayload.setTotalNumberOfElements(totalNumberOfElements);
        globalPayload.setTotalNumberOfPages(CommonUtil.calculateNumberOfPages(totalNumberOfElements, criteria.getSize()));
        return globalPayload;
    }

    public static Pageable pageableBuilder(PaginationCriteria criteria ) {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        String sortCriteria = criteria.getSort();
        String sortColumnCriteria = criteria.getSortColumn();
        if (!sortCriteria.isEmpty()) {
            sort = Sort.by(
                    sortCriteria.equals(CommonConstants.ASC) ? Sort.Order.asc(sortColumnCriteria) : Sort.Order.desc(sortColumnCriteria)
            );
        }
        Pageable paging = PageRequest.of(criteria.getPages(), criteria.getSize(), sort);
        return paging;
    }

}
