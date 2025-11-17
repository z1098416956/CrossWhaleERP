package com.cross.whale.interceptor;

import java.util.List;

public class UserSecurityContextHolder {

    private static final ThreadLocal<UserSecurityContext> SECURITY_CONTEXT = new ThreadLocal<UserSecurityContext>();

    public static void setContext(UserSecurityContext context) {
        SECURITY_CONTEXT.set(context);
    }

    public static UserSecurityContext getContext() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new UserSecurityContext();
            SECURITY_CONTEXT.set(ctx);
        }
        return ctx;
    }

    public static Long getUserId() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        return ctx != null ? ctx.getUserId() : null;
    }

    public static List<String> getRoles() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        return ctx != null ? ctx.getRoles() : null;
    }

    public static String getUsername() {
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        return ctx != null ? ctx.getUsername() : null;
    }

    public static String getAccount(){
        UserSecurityContext ctx = SECURITY_CONTEXT.get();
        return ctx != null ? ctx.getAccount() : null;
    }

    public static void clear() {
        SECURITY_CONTEXT.remove();
    }

}
