package com.example.realtimestreaming.Service;

import com.amazonaws.services.kms.model.AlreadyExistsException;
import com.example.realtimestreaming.Common.ErrorCode;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.User.UserSignupReq;
import com.example.realtimestreaming.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // TODO 추후 보안 적용
    @Transactional
    public User signup(UserSignupReq request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new AlreadyExistsException(ErrorCode.NICKNAME_IS_DUPLICATED.getMessage());
        } else if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException(ErrorCode.EMAIL_IS_DUPLICATED.getMessage());
        }

        var createdUser = userRepository.save(
                User.builder()
                        .nickname(request.getNickname())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );
        return createdUser;
    }
}
