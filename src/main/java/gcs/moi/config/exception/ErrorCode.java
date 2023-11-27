package gcs.moi.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_ALREADY_JOINED("ME001", "Member already joined"),
    MEMBER_NOT_FOUND("M002", "Member not found"),
    ITEM_NOT_FOUND("IT001","Item not found"),
    INVEST_NOT_FOUND("IV001", "Invest not found"),
    BAD_REQUEST("EE001", "Bad request")
    ;

    private String resultCode;
    private String message;
}
