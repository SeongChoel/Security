package com.example.security.global.security;

import com.example.security.domain.member.member.entity.Member;
import com.example.security.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //필요로 하는게 useername, password, authorities
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: "));

        UserDetails userDetails = new User(member.getUsername(),
                member.getPassword(),
                List.of());

        return userDetails;
    }
}
