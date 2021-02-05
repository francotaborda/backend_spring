package com.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.backend.entity.User;
import com.backend.service.IUserService;

import java.util.HashMap;
import java.util.Map;
@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private IUserService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User usuario = usuarioService.findbyEmail(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info_adicional", "User Details add: ".concat(authentication.getName()));

        info.put("nombre", usuario.getFirstName());
        info.put("apellido", usuario.getLastName());
        info.put("email", usuario.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
