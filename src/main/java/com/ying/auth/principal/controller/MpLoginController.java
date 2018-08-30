package com.ying.auth.principal.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author bvvy
 * @date 2018/8/28
 */

@Data
class MpCode {

    private String code;
}
@RestController
public class MpLoginController {

    @Autowired
    private OAuth2ClientContext context;

    @PostMapping("/login/mp")
    public Object login(HttpServletRequest request,MpCode code) {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setAuthenticationScheme(AuthenticationScheme.form);
        resource.setClientAuthenticationScheme(AuthenticationScheme.form);
        resource.setClientId("wx3fdfbf00e5a3b9f9");
        resource.setClientSecret("4d17a936b70d0786f59d1fa972c92c49");
        resource.setUserAuthorizationUri("https://api.weixin.qq.com/sns/jscode2session");
        resource.setAccessTokenUri("https://api.weixin.qq.com/sns/oauth2/access_token");
        context.getAccessTokenRequest().setCurrentUri(request.getRequestURL().toString());
        OAuth2RestTemplate template = new WeixinOAuth2RestTemplate(resource, context);
        String url = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&openid=$openid$";
        ResponseEntity<Object> result = template.getForEntity(url, Object.class);
        return result.getBody();
    }

}
class WeixinAuthorizationCodeAccessTokenProvider extends AuthorizationCodeAccessTokenProvider {
    public WeixinAuthorizationCodeAccessTokenProvider(List<HttpMessageConverter<?>> messageConverters) {
        this.setMessageConverters(messageConverters);
        this.setTokenRequestEnhancer((request, resource, form, headers) -> {
            String clientId = form.getFirst("client_id");
            String clientSecret = form.getFirst("client_secret");
            form.set("appid", clientId);
            form.set("secret", clientSecret);
            form.remove("client_id");
            form.remove("client_secret");
        });
    }

    @Override
    public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details, AccessTokenRequest request)
            throws UserRedirectRequiredException, UserApprovalRequiredException,
            AccessDeniedException, OAuth2AccessDeniedException {
        try {
            return super.obtainAccessToken(details, request);
        } catch (UserRedirectRequiredException e) {
            Map<String, String> params = e.getRequestParams();
            String clientId = params.get("client_id");
            params.put("appid", clientId);
            params.remove("client_id");
            throw e;
        }
    }
}

class WeixinOAuth2RestTemplate extends OAuth2RestTemplate {

    public WeixinOAuth2RestTemplate(AuthorizationCodeResourceDetails resource, OAuth2ClientContext context) {
        super(resource, context);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        this.setMessageConverters(messageConverters);
        this.setAccessTokenProvider(new WeixinAuthorizationCodeAccessTokenProvider(messageConverters));
    }


    @Override
    protected URI appendQueryParameter(URI uri, OAuth2AccessToken accessToken) {
        uri = super.appendQueryParameter(uri, accessToken);
        String url = uri.toString();
        if (url.contains("$openid$")) {
            String openid = (String) accessToken.getAdditionalInformation().get("openid");
            try {
                uri = new URI(url.replace("$openid$", openid));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return uri;
    }

    @Override
    public OAuth2AccessToken getAccessToken() throws UserRedirectRequiredException {
        return super.getAccessToken();
    }
}