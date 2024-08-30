package lk.ijse.gdse68.springsecurity.controller;


import lk.ijse.gdse68.springsecurity.dto.CustomerDTO;
import lk.ijse.gdse68.springsecurity.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO dto = customerService.updateCustomer(customerDTO);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Customer updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
        CustomerDTO dto = customerService.getCustomerById(id);
        if (dto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}

