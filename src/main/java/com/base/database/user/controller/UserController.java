package com.base.database.user.controller;


import com.base.database.contact.util.dto.ContactResponseDTO;
import com.base.database.contact.util.service.ContactService;
import com.base.database.security.model.service.JwtService;
import com.base.database.user.util.dto.UserRequestDTO;
import com.base.database.user.util.dto.UserResponseDTO;
import com.base.database.user.util.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final ContactService contactService;

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserRequestDTO userRequestDTO) {
        Long userId = extractUserIdFromToken(token);
        return ResponseEntity.ok(userService.updateUser(userId, userRequestDTO));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String token) {
        Long userId = extractUserIdFromToken(token);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/contacts")
    public ResponseEntity<List<ContactResponseDTO>> getUserContacts(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUsername(token);
        List<ContactResponseDTO> contacts = contactService.findContactsByUsername(username);
        return ResponseEntity.ok(contacts);
    }

    // Extract user ID from token
    private Long extractUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtService.extractUserId(token);
    }
}




