package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.User;
import com.backend.repository.IHabilitiesRepository;
import com.backend.repository.IRoleRepository;
import com.backend.repository.ITeamRepository;
import com.backend.repository.IUserRepository;
import com.backend.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    ITeamRepository teamDao;
    @Autowired
    IHabilitiesRepository habilitiesDao;
    @Autowired
    IRoleRepository roleDao;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public void isActive(Long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setActive(!user.getActive());
    }


    @Override
    @Transactional
    public void changeStatus(Long id, Long status_id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setStatusId(status_id);
    }


    @Override
    public User findbyEmail(String email) {
              return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User usuario = userRepository.findByEmail(s);

        if(usuario==null){
            logger.error("Error en el loggin: no existe El usuario en el sistema");
            throw new UsernameNotFoundException("Error en el loggin: no existe El usuario en el sistema");

        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> {
                    return new SimpleGrantedAuthority(role.getName());
                }).peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), usuario.getActive(), true, true, true, authorities);

    }
}
