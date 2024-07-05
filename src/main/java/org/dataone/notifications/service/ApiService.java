package org.dataone.notifications.service;

import jakarta.servlet.http.HttpServletRequest;

public interface ApiService {
    Object processRequest(HttpServletRequest request);
}
