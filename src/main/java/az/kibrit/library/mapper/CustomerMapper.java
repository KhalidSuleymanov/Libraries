package az.kibrit.library.mapper;
import az.kibrit.library.dto.CustomerDTO;
import az.kibrit.library.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
