package api.utils;

public class ApiUrls {
    public static String baseUrl = new ReadConfiguration().getAppUrl() + "/v2/";
    public static String findByStatus = "pet/findByStatus";
    public static String petEndpoint = "pet";
    public static String userLogin = "user/login?username=%s&password=%s";
}
