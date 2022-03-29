package com.microservices.demo.elastic.query.service.security;

import com.microservices.demo.elastic.query.service.business.QueryUserService;
import com.microservices.demo.elastic.query.service.transformer.UserPermissionToUserDetailTransformer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TwitterQueryUserDetailsService implements UserDetailsService {

    private final QueryUserService queryUserService;

    private final UserPermissionToUserDetailTransformer userPermissionToUserDetailTransformer;


    public TwitterQueryUserDetailsService(QueryUserService queryUserService,
                                          UserPermissionToUserDetailTransformer userPermissionToUserDetailTransformer
    ) {
        this.queryUserService = queryUserService;
        this.userPermissionToUserDetailTransformer = userPermissionToUserDetailTransformer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return queryUserService.findAllPermissionsByUsername(username)
                .map(userPermissionToUserDetailTransformer::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username " + username));
    }
}
