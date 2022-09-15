package dev.gunlog.domain.member;

public enum Role {
    USER("사용자", "ROLE_USER"), ADMIN("관리자", "ROLE_ADMIN");

    private String title;
    private String value;

    Role(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}
