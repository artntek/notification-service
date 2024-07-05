package org.dataone.notifications.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dataone.notifications.service.ApiService;
import org.dataone.notifications.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ApiServlet", urlPatterns = "/notify")
public class ApiServlet extends HttpServlet {

    private final Map<String, ApiService> serviceMapping;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServlet.class);

    @Inject
    private RegistrationService registrationService;

    public ApiServlet() {
        serviceMapping = new HashMap<>();
        serviceMapping.put("register", new RegistrationService());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        LOGGER.debug("doPost called");
        String[] parts = request.getRequestURI().split("/notify/");
        if (parts.length > 1) {
            String apiType = parts[1]; // This is the service name extracted from the URL
            if (serviceMapping.containsKey(apiType)) {
                ApiService service = serviceMapping.get(apiType);
                Object result = service.processRequest(request);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(result.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("API endpoint not found");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid request URI");
        }
    }
}
