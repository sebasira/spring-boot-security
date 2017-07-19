package com.sebasira.MediumTutorialSecurity.config;

import com.sebasira.MediumTutorialSecurity.model.Client;
import com.sebasira.MediumTutorialSecurity.model.Role;
import com.sebasira.MediumTutorialSecurity.model.User;
import com.sebasira.MediumTutorialSecurity.service.UserService;
import com.sebasira.MediumTutorialSecurity.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebas on 19/07/17.
 *
 * A Mix from:
 * -  http://javainsimpleway.com/spring-security-using-custom-authentication-provider/
 * -  https://stackoverflow.com/questions/36721212/spring-boot-custom-authentication-provider-with-java-configuration-fixed
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //private static final Logger logger =     LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    public CustomAuthenticationProvider() {
        //logger.info("*** CustomAuthenticationProvider created");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();

        User attempUser = userService.findUserByEmail(username);

        /*
        Para matchear las claves, no hay q re-hashearla porque como usa SALT las genera
        con hashes distintos para mismos valores de entrada. Hay que usar el metodo match

        Source: https://stackoverflow.com/a/34210582/2597775
         */

        if(username.equals(attempUser.getEmail())  && bCryptPasswordEncoder.matches(rawPassword, attempUser.getPassword())) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();

            /*
            Aca debo traer los roles de ese usuario y setearselos como grantedAuths,
            si tiene mas de un rol, se los seteo, sino, los puedo poner a manopla

            grantedAuths.add(new SimpleGrantedAuthority("USER"));
            grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
             */
            List<Role> userRoles = new ArrayList<Role>(attempUser.getRoles());

            for (int i=0; i<userRoles.size(); i++){
                grantedAuths.add(new SimpleGrantedAuthority(userRoles.get(i).getRole()));
            }

            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuths);
        } else {
            // Si retorno null, es error de login
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}