package com.base.database.user.controller;


import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.service.ContactService;
import com.base.database.security.model.service.JwtService;
import com.base.database.user.util.dto.UserRequestDTO;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.model.User;
import com.base.database.user.util.repository.UserRepository;
import com.base.database.user.util.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token) throws ChangeSetPersister.NotFoundException {
        String jwt = token.substring(7);
        String username = jwtService.extractUsername(jwt);
        Long userId = getUserIdByUsername(username);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/contacts")
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




