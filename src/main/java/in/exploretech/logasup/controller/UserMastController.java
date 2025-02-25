package in.exploretech.logasup.controller;

import in.exploretech.jwt.JwtTokenUtil;
import in.exploretech.logasup.dto.AuthRequestDTO;
import in.exploretech.logasup.dto.LoginResponseDTO;
import in.exploretech.logasup.dto.UserMastDTO;
import in.exploretech.logasup.service.UserMastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserMastController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMastService userMastService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Fetch user details
        UserDetails userDetails = userMastService.loadUserByUsername(authRequest.getUsername());

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        // Optionally, store the token in a cache (e.g., Redis) with the associated username for further validation

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setAuthKey(null);
        loginResponseDTO.setToken(token);
        loginResponseDTO.setSecretKey(null);
        return ResponseEntity.ok(loginResponseDTO);
    }



    @PostMapping("/is-user-exists")
    public ResponseEntity<String> isUserExist(@RequestParam String username){
        if(userMastService.isUserExist(username)){
            return ResponseEntity.ok("this usename is taken");
        }else{
            return ResponseEntity.ok("this usename is available");
        }
    }



    @PostMapping("/register")
    public String registerNewUser(@RequestBody UserMastDTO userMastDTO) {

        userMastService.registerUser(userMastDTO);

        return "user registered successfully";

    }

    @GetMapping("/secured")
    public String securedEndpoint( @RequestHeader(value = "Authorization", required = true) String token) {
        return "You have accessed a secured endpoint!";
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("You have accessed url pattern based post api");
    }

    @PostMapping("/test-with-token")
    public ResponseEntity<?> testWithToken(){
        return ResponseEntity.ok("You have accessed post api with token authentication");
    }



}
