package gcs.moi.config.exception;

import gcs.moi.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(MoiApplicationException.class)
    public ResponseEntity<?> applicationHandler(MoiApplicationException e) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.error(e.getErrorCode().getResultCode(), e.getErrorCode().getMessage()));
    }

}
