package gcs.moi.domain;

import lombok.Getter;

public enum Role {

    MEMBER("member"),
    ADMIN("admin")
    ;

    @Getter
    private String name;

    Role(String name) {
        this.name = name;
    }
}
