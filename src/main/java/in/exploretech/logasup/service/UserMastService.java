package in.exploretech.logasup.service;

import dataman.dmbase.debug.Debug;
import in.exploretech.logasup.dto.UserMastDTO;
import in.exploretech.logasup.entity.Applicant;
import in.exploretech.logasup.entity.UserMast;
import in.exploretech.logasup.repository.ApplicantRepository;
import in.exploretech.logasup.repository.UserMastRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMastService implements UserDetailsService {


    @Autowired
    private UserMastRepository userMastRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserMast user = userMastRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();

    }


    // Add user registration method to save the user with an encrypted password
    @Transactional
    public void registerUser(UserMastDTO userMastDTO) {
        try {

            // Prepare the user data
            UserMast userMast = new UserMast();
            // Encrypt the password before saving
            String encodedPassword = passwordEncoder.encode(userMastDTO.getPassword());

            userMast.setUsername(userMastDTO.getUsername());
            userMast.setPassword(encodedPassword);


            // Create the UserMast object
            Applicant applicant = new Applicant();
            applicant.setAddress(userMastDTO.getAddress());
            applicant.setGender(userMastDTO.getGender());
            applicant.setUsername(userMastDTO.getUsername());
            applicant.setPassword(userMastDTO.getPassword());
            applicant.setEmail(userMastDTO.getEmail());
            applicant.setAge(userMastDTO.getAge());

            // Save both entities; if either fails, the transaction is rolled back
            applicantRepository.save(applicant);
            userMastRepository.save(userMast);

        } catch (DataIntegrityViolationException ex) {
            // Handle duplicate key (or any other integrity violation) gracefully.
            // You could log the error and/or throw a custom exception with a friendly message.
            // For example:

            Debug.printDebugBoundary();
            System.out.println("Exception fall in DataIntegrityViolationException");
            Debug.printDebugBoundary("\uD83C\uDF39\uD83D\uDE0A\uD83D\uDE09");
            throw new RuntimeException("Registration failed: The username might already exist.", ex);
        } catch (Exception ex) {
            // Optionally handle other exceptions
            Debug.printDebugBoundary();
            System.out.println("Exception fall in Exception");
            Debug.printDebugBoundary();
            throw new RuntimeException("An error occurred during user registration.", ex);
        }
    }

    public boolean isUserExist(String username){
        return userMastRepository.existsByUsername(username);
    }
}
