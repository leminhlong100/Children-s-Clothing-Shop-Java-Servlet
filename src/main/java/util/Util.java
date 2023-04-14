package util;

import entity.Account;
import entity.Permission;
import entity.Role;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringTokenizer;

public class Util {
    public static String getURL(HttpServletRequest httpRequest) {
        String requestedResource = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        StringTokenizer stringTokenizer = new StringTokenizer(requestedResource, "/");
        if (stringTokenizer.hasMoreTokens()) {
            requestedResource = "/" + stringTokenizer.nextToken();
        } else {
            requestedResource = "";
        }
        return requestedResource;
    }
    // Kiểm tra xem người dùng có quyền truy cập vào tài nguyên hay không

}
