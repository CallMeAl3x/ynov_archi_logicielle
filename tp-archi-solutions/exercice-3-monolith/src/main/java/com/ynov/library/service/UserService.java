package com.ynov.library.service;

import com.ynov.library.dto.CreateUserDTO;
import com.ynov.library.dto.UserDTO;
import com.ynov.library.exception.BusinessException;
import com.ynov.library.exception.ResourceNotFoundException;
import com.ynov.library.model.AppUser;
import com.ynov.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        return toDTO(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id)));
    }

    public UserDTO createUser(CreateUserDTO dto) {
        userRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new BusinessException("Email already registered: " + dto.getEmail());
        });
        AppUser user = AppUser.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .membershipDate(LocalDate.now())
                .build();
        return toDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, CreateUserDTO dto) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        return toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        userRepository.deleteById(id);
    }

    private UserDTO toDTO(AppUser user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .membershipDate(user.getMembershipDate())
                .build();
    }
}
