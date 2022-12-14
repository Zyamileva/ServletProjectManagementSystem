package controller.customers;

import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersService;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/customers/allCustomers")
public class FindAllCustomerController extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersRepository customersRepository = new CustomersRepository(connector);
        customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CustomersDto> allCustomers = customersService.findAll();
        req.setAttribute("customers", allCustomers);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/findAllCustomers.jsp").forward(req, resp);
    }
}
