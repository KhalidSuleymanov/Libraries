package az.kibrit.library.controller;
import az.kibrit.library.dto.CustomerDTO;
import az.kibrit.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO savedCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable @Min(1) Long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        return ResponseEntity.ok(customerDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCusomer(@Valid @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable @Min(1) Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/start-reading/{bookId}")
    public ResponseEntity<Void> startReading(@PathVariable Long customerId, @PathVariable Long bookId, @RequestParam LocalDate startDate) {
        customerService.startReadingBook(customerId, bookId, startDate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{customerId}/finish-reading/{bookId}")
    public ResponseEntity<Void> finishReading(@PathVariable Long customerId, @PathVariable Long bookId, @RequestParam LocalDate endDate) {
        customerService.finishReadingBook(customerId, bookId, endDate);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}/reading-status/{bookId}")
    public ResponseEntity<Map<String, Object>> getReadingStatus(@PathVariable Long customerId, @PathVariable Long bookId) {
        Map<String, Object> status = customerService.getReadingStatus(customerId, bookId);
        return ResponseEntity.ok(status);
    }
}
