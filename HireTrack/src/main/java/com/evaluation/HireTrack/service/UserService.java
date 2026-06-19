package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.model.User;
import com.evaluation.HireTrack.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}

