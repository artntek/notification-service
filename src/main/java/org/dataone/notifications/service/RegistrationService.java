package org.dataone.notifications.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RegistrationService implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    @Override
    public Object processRequest(HttpServletRequest request) {
        // Implementation of registration logic
        // TODO: handle error cases

        String userId = request.getParameter("userId");
        String targetId = request.getParameter("targetId");

        LOGGER.debug("RegistrationService called with userId: " + userId
                         + " and targetId: " + targetId);
        return true;
    }
}
