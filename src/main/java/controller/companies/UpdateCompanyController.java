package controller.companies;

import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import repository.CompaniesRepository;
import service.CompaniesService;
import service.CompaniesServiceImpl;
import service.converter.CompaniesConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Set;

@WebServlet(urlPatterns = "/companies/update")
public class UpdateCompanyController extends HttpServlet {
    private CompaniesService companiesService;

    @Override
    public void init() throws ServletException {
        Connection connector = DataBaseManagerConnector.getInstance().getConnector();
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        if (!companiesService.findByName(companyName).isEmpty()) {
            String newName = req.getParameter("newCompanyName");
            int newStaff = Integer.parseInt(req.getParameter("newStaff"));
            Set<CompaniesDto> byName = companiesService.findByName(companyName);
            for (CompaniesDto company : byName) {
                company.setName(newName);
                company.setStaff(newStaff);
                companiesService.update(company);
                req.setAttribute("message", "Company: \"" + company.getName() + "\" updated");
            }
        } else {
            req.setAttribute("message", "Company not found");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/updatedCompany.jsp").forward(req, resp);
    }
}
