package lk.ijse.gdse68.springsecurity.service;

import lk.ijse.gdse68.springsecurity.dto.CustomerDTO;
import lk.ijse.gdse68.springsecurity.dto.SignInDTO;


public interface CustomerService {

    CustomerDTO saveCustomer(CustomerDTO dto);

    CustomerDTO updateCustomer(CustomerDTO dto);

    boolean deleteCustomer(Long id);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO isCustomerExist(SignInDTO signInDTO);

    String verify(SignInDTO signInDTO);
}
