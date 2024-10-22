package az.kibrit.library.service;
import az.kibrit.library.dto.CustomerDTO;
import az.kibrit.library.exception.ResourceNotFoundException;
import az.kibrit.library.mapper.CustomerMapper;
import az.kibrit.library.model.entity.Book;
import az.kibrit.library.model.entity.Customer;
import az.kibrit.library.repository.BookRepository;
import az.kibrit.library.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        return customerMapper.customerToCustomerDto(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    public void startReadingBook(Long customerId, Long bookId, LocalDate startDate) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        customer.getBookStartDates().put(book, startDate);
        customerRepository.save(customer);
    }

    public void finishReadingBook(Long customerId, Long bookId, LocalDate endDate) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        customer.getBookEndDates().put(book, endDate);
        customerRepository.save(customer);
    }

    public Map<String, Object> getReadingStatus(Long customerId, Long bookId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        LocalDate startDate = customer.getBookStartDates().get(book);
        LocalDate endDate = customer.getBookEndDates().get(book);
        Map<String, Object> status = new HashMap<>();
        if (startDate != null) {
            status.put("startDate", startDate);
            if (endDate != null) {
                status.put("endDate", endDate);
                status.put("status", "Finished Reading");
            } else {
                int pagesRead = book.getNumberOfPages();
                int remainingPages = book.getRemainingPages(book.getNumberOfPages(), pagesRead);
                status.put("status", "Currently Reading");
                status.put("remainingPages", remainingPages);
            }
        } else {
            status.put("status", "Not Started");
        }
        return status;
    }
}
