package com.ead.authuser.services.impl;

import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // mostra para o spring framework que esta classe vai ser um bin que ele vai gerenciar
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
}
