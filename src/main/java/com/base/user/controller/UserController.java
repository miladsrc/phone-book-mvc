package com.base.user.controller;


import com.base.contact.dto.ContactResponseDTO;
import com.base.contact.service.ContactService;
import com.base.security.service.JwtService;
import com.base.user.dto.UserRequestDTO;
import com.base.user.dto.UserResponseDTO;
import com.base.user.entity.User;
import com.base.user.repository.UserRepository;
import com.base.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final ContactService contactService;
    private final UserRepository userRepository;

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserRequestDTO userRequestDTO) throws ChangeSetPersister.NotFoundException {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        Long userId = getUserIdByUsername(username);
        UserResponseDTO userResponseDTO = userService.updateUser(userId, userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/me/contacts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ContactResponseDTO>> getUserContacts(@RequestHeader("Authorization") String token) throws ChangeSetPersister.NotFoundException {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        Long userId = getUserIdByUsername(username);
        List<ContactResponseDTO> contacts = contactService.findContactsByUserId(userId);
        return ResponseEntity.ok(contacts);
    }

    //find user by username
    public Long getUserIdByUsername(String username) throws ChangeSetPersister.NotFoundException {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

}




