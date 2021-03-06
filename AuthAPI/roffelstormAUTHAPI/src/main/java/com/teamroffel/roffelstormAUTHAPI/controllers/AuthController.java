package com.teamroffel.roffelstormAUTHAPI.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamroffel.roffelstormAUTHAPI.models.ERole;
import com.teamroffel.roffelstormAUTHAPI.models.Role;
import com.teamroffel.roffelstormAUTHAPI.models.User;
import com.teamroffel.roffelstormAUTHAPI.payload.request.LoginRequest;
import com.teamroffel.roffelstormAUTHAPI.payload.request.SignupRequest;
import com.teamroffel.roffelstormAUTHAPI.payload.response.JwtResponse;
import com.teamroffel.roffelstormAUTHAPI.payload.response.MessageResponse;
import com.teamroffel.roffelstormAUTHAPI.repository.RoleRepository;
import com.teamroffel.roffelstormAUTHAPI.repository.UserRepository;
import com.teamroffel.roffelstormAUTHAPI.security.jwt.JwtUtils;
import com.teamroffel.roffelstormAUTHAPI.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    
    @PersistenceContext
	private EntityManager em;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
    	Query q = em.createQuery("select user from User user");
    	List<User> userlist = q.getResultList();
    	return userlist;   	
    }
    
    @GetMapping("/user/{id}")
    public List<User> getSpecificUser(@PathVariable("id") long id) {
    	Query q = em.createQuery("select user from User user where user.id = :id");
    	q.setParameter("id", id);
    	List<User> user = q.getResultList();
    	return user;   	
    }
    
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("User removed"));
    }
    
    @GetMapping("/weeklyuser")
    public User getWeeklyUser() {
    	Calendar sDateCalendar = new GregorianCalendar();
    	long weekOfYear = sDateCalendar.get(Calendar.WEEK_OF_YEAR);
    	Query q = em.createQuery("select user from User user");
    	List<User> userlist = q.getResultList();
    	User weeklyuser = userlist.get((int) weekOfYear);
    	System.out.println(weeklyuser);
    	if(weeklyuser == null) {
    		weeklyuser = userlist.get(1);
    		System.out.println("null but: "+ weeklyuser);
    	}
    	
    	return weeklyuser;   	
    }
}
