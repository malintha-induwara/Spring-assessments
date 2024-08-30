package lk.ijse.gdse68.springsecurity.service.impl;

import lk.ijse.gdse68.springsecurity.entity.Customer;
import lk.ijse.gdse68.springsecurity.entity.CustomerPrincipal;
import lk.ijse.gdse68.springsecurity.resository.CustomerRepository;
import lk.ijse.gdse68.springsecurity.service.CustomerDetailService;
import lk.ijse.gdse68.springsecurity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(username).orElse(null);
        if (customer == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomerPrincipal(customer);
    }
}

