package com.backend.service;

import com.backend.entity.User;

public interface IUserService {

    void isActive(Long id);

    void changeStatus(Long id, Long status_id);

    User findbyEmail(String email);


}
