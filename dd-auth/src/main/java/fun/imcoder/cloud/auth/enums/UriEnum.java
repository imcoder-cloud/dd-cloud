package fun.imcoder.cloud.auth.enums;

public enum  UriEnum {
    //oauth2登录
    LOGIN_URL("/oauth/token"),
    ;

    private String uri;

    UriEnum(String uri) {
        this.uri = uri;

    }

    public String getUri() {
        return uri;
    }
}
