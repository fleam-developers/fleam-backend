package com.fleam.movieservice.util;

import com.fleam.movieservice.constants.ServiceConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ServiceUtility {


    public static long[] parseHttpRangeHeader(String rangeList){
        if (rangeList == null || rangeList == ""){
            return new long[]{0, ServiceConstants.CHUNK_SIZE};
        }
//        System.out.println(rangeList);
        String[] range = rangeList.split("bytes=")[1].split("-");
        long[] parsed_range = new long[2];
        parsed_range[0] = Long.parseLong(range[0]);
        if (range.length == 1){
            parsed_range[1] = parsed_range[0] + ServiceConstants.CHUNK_SIZE;
        }
        else {
            parsed_range[1] = Long.parseLong(range[1]);
        }
        return parsed_range;
    }

    public static HttpHeaders httpVideoBaseHeaders(long contentLength, long rangeStart, long rangeEnd){
        HttpHeaders headers = new HttpHeaders();
        headers.add(ServiceConstants.CONTENT_TYPE, ServiceConstants.CONTENT_TYPE_VALUE);
        headers.add(ServiceConstants.ACCEPT_RANGES, ServiceConstants.BYTES);
        headers.add(ServiceConstants.CONTENT_LENGTH, String.valueOf((rangeEnd - rangeStart) + 1));
        headers.add(ServiceConstants.CONTENT_RANGE, ServiceConstants.BYTES + " " +
                String.valueOf(rangeStart) + "-" + String.valueOf(rangeEnd) + "/" + contentLength);
        headers.add(ServiceConstants.KEEP_ALIVE, ServiceConstants.KEEP_ALIVE_VALUE);

        return headers;
    }

}
