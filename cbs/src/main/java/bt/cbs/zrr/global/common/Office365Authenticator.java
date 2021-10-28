/*
package com.springapp.mvc.global.common;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.Authenticator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

*/
/**
 * Created by RMA on 5/27/2020.
 *//*

public class Office365Authenticator implements Authenticator {

    private static final String DEFAULT_AUTHORITY = "https://login.windows.net/common/oauth2/authorize";
    private static final String DEFAULT_POWER_BI_RESOURCE_ID = "https://analysis.windows.net/powerbi/api";
    private static final boolean DEFAULT_VALIDATE_AUTHORITY = false;
    private String authority = DEFAULT_AUTHORITY;
    private String powerBiResourceId = DEFAULT_POWER_BI_RESOURCE_ID;
    private boolean validateAuthority = DEFAULT_VALIDATE_AUTHORITY;
    private String nativeClientId;
    private String tenant;
    private String username;
    private String password;

    private ExecutorService executor;
    private ReadWriteLock tokenLock = new ReentrantReadWriteLock();
    private String cachedToken;

    private String _authenticate() throws AuthenticationFailureException {
        try {
            AuthenticationContext authenticationContext = new AuthenticationContext(
                    authority,
                    validateAuthority,
                    executor
            );

            String result = getAccessToken(
                    authenticationContext,
                    powerBiResourceId,
                    nativeClientId,
                    username + "@" + tenant,
                    password
            );

            if (StringUtils.isEmpty(result)) {
                throw new AuthenticationFailureException("Returned access token is null.");
            }

            return result;
        } catch (ExecutionException | InterruptedException | IOException e) {
            throw new AuthenticationFailureException(e);
        }
    }

    private String getAccessToken(AuthenticationContext authenticationContext, String resourceId, String clientId,
                                  String username, String password) throws ExecutionException, InterruptedException {
        return authenticationContext.acquireToken(
                resourceId,
                clientId,
                username,
                password,
                null
        ).get().getAccessToken();
    }
}
*/
