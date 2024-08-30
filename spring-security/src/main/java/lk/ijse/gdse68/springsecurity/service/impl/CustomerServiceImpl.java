package lk.ijse.gdse68.springsecurity.service.impl;

import lk.ijse.gdse68.springsecurity.dto.CustomerDTO;
import lk.ijse.gdse68.springsecurity.dto.SignInDTO;
import lk.ijse.gdse68.springsecurity.entity.Customer;
import lk.ijse.gdse68.springsecurity.resository.CustomerRepository;
import lk.ijse.gdse68.springsecurity.service.CustomerService;
import lk.ijse.gdse68.springsecurity.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);

    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;


    //########################################################################
    //You cant do the following implementations because it leads to circular dependency error
    //Unless you remove the PasswordEncoder from the SecurityConfig class
    //########################################################################


    //###Implementation 1###

//    private final SecurityConfig securityConfig;
//
//    @Autowired
//    public CustomerServiceImpl(CustomerRepository customerRepository, SecurityConfig securityConfig) {
//        this.customerRepository = customerRepository;
//        this.securityConfig = securityConfig;
//    }
//

    //###Implementation 2###

    //    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public CustomerDTO saveCustomer(CustomerDTO dto) {



        Customer save = new Customer(
                dto.getCustomerId(),
                dto.getUserName(),
                dto.getPassword(),
                dto.isStatus());

        save.setPassword(passwordEncoder.encode(save.getPassword()));

        // securityConfig.passwordEncoder().encode(save.getPassword());

        Customer customer = customerRepository.save(save);
        return new CustomerDTO(customer.getCustomerId(), customer.getUserName(), customer.getPassword(), customer.isStatus());
    }


    public boolean deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
            return true;
        }
        return false;
    }


    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer != null) {
            return new CustomerDTO(customer.getCustomerId(), customer.getUserName(), customer.getPassword(), customer.isStatus());
        }
        return null;
    }

    public CustomerDTO updateCustomer(CustomerDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElse(null);
        if (customer != null) {
            customer.setUserName(dto.getUserName());
            customer.setPassword(dto.getPassword());
            customer.setStatus(dto.isStatus());
            Customer save = customerRepository.save(customer);
            return new CustomerDTO(save.getCustomerId(), save.getUserName(), save.getPassword(), save.isStatus());
        }
        return null;
    }


    @Override
    public CustomerDTO isCustomerExist(SignInDTO signInDTO) {
        Customer customer = customerRepository.findByCustomerUsernameAndPassword(signInDTO.getUserName()).orElse(null);

        if (customer != null && passwordEncoder.matches(signInDTO.getPassword(), customer.getPassword())) {
            return new CustomerDTO(customer.getCustomerId(), customer.getUserName(), customer.getPassword(), customer.isStatus());
        }
        return null;
    }

    @Override
    public String verify(SignInDTO signInDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUserName(), signInDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(signInDTO.getUserName());
            }
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}

