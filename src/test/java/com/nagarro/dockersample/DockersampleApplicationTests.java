package com.nagarro.dockersample;




import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.nagarro.dockersample.controller.UserController;
import com.nagarro.dockersample.Entity.User;
import java.util.ArrayList;
import java.util.List;
import com.nagarro.dockersample.Entity.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class DockersampleApplicationTests {

    private UserController userController;
 

    @BeforeEach
    public void setUp() {
        userController = new UserController();
    }

    @Test
    public void testGetAllCustomers_ReturnsAllCustomers() {
        List<User> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new User(1L, "John", "Doe", "john@example.com"));
        expectedCustomers.add(new User(2L, "Jane", "Smith", "jane@example.com"));

        List<User> actualCustomers = userController.getAllCustomers();

        assertEquals(expectedCustomers.size(), actualCustomers.size());

        // Compare each user individually
        for (int i = 0; i < expectedCustomers.size(); i++) {
            User expectedUser = expectedCustomers.get(i);
            User actualUser = actualCustomers.get(i);
            
            assertEquals(expectedUser.getId(), actualUser.getId());
            assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
            assertEquals(expectedUser.getLastName(), actualUser.getLastName());
            assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        }
    }

    @Test
    public void testGetCustomerById_ExistingCustomer() {
        Long id = 1L;
        User expectedCustomer = new User(1L, "John", "Doe", "john@example.com");

        ResponseEntity<?> responseEntity = userController.getCustomerById(id);
        User actualCustomer = (User) responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertUserEquals(expectedCustomer, actualCustomer);
    }

    // Custom method to compare User objects without relying on equals method
    private void assertUserEquals(User expected, User actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

//
    @Test
    public void testGetCustomerById_NonExistingCustomer() {
        Long id = 3L;

        ResponseEntity<?> responseEntity = userController.getCustomerById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}




