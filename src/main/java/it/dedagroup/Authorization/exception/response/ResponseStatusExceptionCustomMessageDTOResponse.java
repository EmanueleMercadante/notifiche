package it.dedagroup.Authorization.exception.response;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class ResponseStatusExceptionCustomMessageDTOResponse {
    private String reason;
    private HttpStatusCode code;
    private int statusCodeNumber;
}
