package edu.com.javaesencial07salesapi.apiresponse;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenResponse<T>(
    int status,
    String message,
    List<T> data
) {
}
