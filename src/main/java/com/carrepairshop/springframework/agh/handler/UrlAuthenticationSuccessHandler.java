package com.carrepairshop.springframework.agh.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    public static boolean isManager = false;
    public static boolean isAccountant = false;
    public static boolean isMechanic = false;
    public static boolean isLogistician = false;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Iterator authIterator = authentication.getAuthorities().iterator();
        if (!authIterator.hasNext())
            throw new IllegalStateException();

        GrantedAuthority grantedAuthority = (GrantedAuthority) authIterator.next();
        switch (grantedAuthority.getAuthority()) {
            case "ROLE_MANAGER":
                isManager = true;
                return "/manager";
            case "ROLE_MECHANIC":
                isMechanic = true;
                return "/mechanic";
            case "ROLE_ACCOUNTANT":
                isAccountant = true;
                return "/accountant";
            case "ROLE_LOGISTICIAN":
                isLogistician = true;
                return "/logistician";
            default:
                throw new IllegalStateException();
        }
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
