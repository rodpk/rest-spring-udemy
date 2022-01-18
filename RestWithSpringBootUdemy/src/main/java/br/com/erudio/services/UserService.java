package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.repository.UserRepository;

// spring injeta as dependencias - n precisa dar new ou static;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) return user;
        else throw new UsernameNotFoundException("username " + username + " not found");
    }

}
