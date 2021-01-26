package com.teamroffel.roffelstormAUTHAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.teamroffel.roffelstormAUTHAPI.models.ConfirmationToken;
import com.teamroffel.roffelstormAUTHAPI.models.User;
import com.teamroffel.roffelstormAUTHAPI.payload.response.MessageResponse;
import com.teamroffel.roffelstormAUTHAPI.repository.ConfirmationTokenRepository;
import com.teamroffel.roffelstormAUTHAPI.repository.UserRepository;
import com.teamroffel.roffelstormAUTHAPI.security.services.EmailSenderService;

@Controller
public class UserAccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

//    @RequestMapping(value="/api/auth/register", method = RequestMethod.GET)
//    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
//    {
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }
//    
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> registerUser(User user)
    {

        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null)
        {
            return ResponseEntity.ok(new MessageResponse("This email already exists!"));
        }
        else
        {
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("teamroffell@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8080/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public ModelAndView registerUser(ModelAndView modelAndView, User user)
//    {
//
//        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
//        if(existingUser != null)
//        {
//            modelAndView.addObject("message","This email already exists!");
//            modelAndView.setViewName("error");
//        }
//        else
//        {
//            userRepository.save(user);
//
//            ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//            confirmationTokenRepository.save(confirmationToken);
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(user.getEmail());
//            mailMessage.setSubject("Complete Registration!");
//            mailMessage.setFrom("teamroffell@gmail.com");
//            mailMessage.setText("To confirm your account, please click here : "
//            +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
//
//            emailSenderService.sendEmail(mailMessage);
//
//            modelAndView.addObject("emailId", user.getEmail());
//
//            modelAndView.setViewName("successfulRegisteration");
//        }
//
//        return modelAndView;
//    }
    
    
//    Funkar
//    @PostMapping("/api/auth/confirm-account/")
//    public String confirmUserAccount(@RequestBody String confirmationToken)
//    {
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//        if(token != null)
//        {
//            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
//            user.setEnabled(true);
//            userRepository.save(user);
//            return "accountVerified";
//        }
//        else
//        {
//        	return("The link is invalid or broken!");
//        }
//
//    }
    @RequestMapping(value="api/auth/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return "accountVerified";
        }
        else
        {
        	return("The link is invalid or broken!");
        }

    }


//    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
//    {
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//        if(token != null)
//        {
//            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
//            user.setEnabled(true);
//            userRepository.save(user);
//            modelAndView.setViewName("accountVerified");
//        }
//        else
//        {
//            modelAndView.addObject("message","The link is invalid or broken!");
//            modelAndView.setViewName("error");
//        }
//
//        return modelAndView;
//    }
}
