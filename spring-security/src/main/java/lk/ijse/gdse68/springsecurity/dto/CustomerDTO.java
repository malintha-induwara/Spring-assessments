package lk.ijse.gdse68.springsecurity.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private Long customerId;
    private String userName;
    private String password;
    private boolean status;
}

