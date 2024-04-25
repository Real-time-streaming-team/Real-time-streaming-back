package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Common.AESEncryption;
import com.example.realtimestreaming.Common.ErrorCode;
import com.example.realtimestreaming.Common.UserApplicationException;
import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.User.LoginRequestDto;
import com.example.realtimestreaming.Dto.Request.User.SignupRequestDto;
import com.example.realtimestreaming.Dto.Response.User.LoginResponseDto;
import com.example.realtimestreaming.Dto.Response.User.SignupResponseDto;
import com.example.realtimestreaming.Provider.JwtTokenProvider;
import com.example.realtimestreaming.Repository.StreamRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final StreamRepository streamRepository;

    private final AESEncryption aesEncryption;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 20;

    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern passworPattern = Pattern.compile(PASSWORD_PATTERN);

    public void SignUpValidateCheck (SignupRequestDto signUpRequestDto) {
        if (signUpRequestDto.getEmail().length() <= 0 && !isValidEmail(signUpRequestDto.getEmail())) {
            throw new UserApplicationException(ErrorCode.EMAIL_IS_VALID);
        }


        if (signUpRequestDto.getPassword().length() <= 0 || !isValidPassword(signUpRequestDto.getPassword())) {
            throw new UserApplicationException(ErrorCode.PASSWORD_IS_VALID);
        }

        if (signUpRequestDto.getNickname().length() <= 0) {
            throw new UserApplicationException(ErrorCode.NICKNAME_IS_VALID);
        }
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = passworPattern.matcher(password);
        return matcher.matches();
    }

    public void SignUpDuplicateCheck (SignupRequestDto signUpRequestDto) {
        System.out.println(signUpRequestDto);
        System.out.println(signUpRequestDto.getEmail());
        System.out.println("!!!!!!!!!#123" + userRepository.existsByEmail(signUpRequestDto.getEmail()));
        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserApplicationException(ErrorCode.EMAIL_IS_DUPLICATED);
        }
        if (userRepository.existsByNickname(signUpRequestDto.getNickname())) {
            throw new UserApplicationException(ErrorCode.NICKNAME_IS_DUPLICATED);
        }

    }

    @Transactional
    public SignupResponseDto signUp (SignupRequestDto signUpRequestDto) throws Exception {
        SignUpDuplicateCheck(signUpRequestDto);
        SignUpValidateCheck(signUpRequestDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        User createdUser = userRepository.save(
                User.builder()
                        .nickname(signUpRequestDto.getNickname())
                        .email(signUpRequestDto.getEmail())
                        .password(encodedPassword)
                        .build()
        );

        Stream createdStream = streamRepository.save(
                Stream.builder()
                        .owner(createdUser)
                        .build()
        );
        String encryptStreamId = aesEncryption.encrypt(createdStream.getStreamId().toString());

        createdStream.setStreamKey(encryptStreamId);
        streamRepository.save(createdStream);
        SignupResponseDto userResponseDto = new SignupResponseDto(createdUser);
        userResponseDto.setStreamKey(encryptStreamId);

        return userResponseDto;
    }

    public void validateLoginDto (LoginRequestDto loginDto) {
        if (loginDto.getEmail() == null) {
            throw new UserApplicationException(ErrorCode.EMAIL_CANNOT_BE_NULL);
        }
        if (!Pattern.matches(EMAIL_PATTERN, loginDto.getEmail())) {
            throw new UserApplicationException(ErrorCode.EMAIL_IS_VALID);
        }
        if (loginDto.getPassword() == null ||
                loginDto.getPassword().length() < MIN_PASSWORD_LENGTH ||
                loginDto.getPassword().length() > MAX_PASSWORD_LENGTH) {
            throw new UserApplicationException(ErrorCode. PASSWORD_IS_VALID);
        }
    }



    public Boolean isSamePassword(String inputPassword, String databasePassword) {
        System.out.println(inputPassword + "------" + databasePassword);

        return passwordEncoder.matches(inputPassword, databasePassword);
    }

    public LoginResponseDto login (LoginRequestDto loginRequestDto) {
        validateLoginDto(loginRequestDto);
        User user = userRepository.findByEmail(loginRequestDto.getEmail());
        if (user == null) {
            throw new UserApplicationException(ErrorCode.NO_MATCHING_USER_FOUND_WITH_EMAIL);
        }
        System.out.println("여기까지도옴222222");
        Long userId = user.getUserId();
        if (isSamePassword(loginRequestDto.getPassword(), user.getPassword()) == false) {
            throw new UserApplicationException(ErrorCode.NO_MATCHING_USER_FOUND_WITH_PASSWORD);
        }
        String token = jwtTokenProvider.generateToken(user.getNickname());
        LoginResponseDto response = new LoginResponseDto(user);
        response.setToken(token);
        return response;
    }
}
