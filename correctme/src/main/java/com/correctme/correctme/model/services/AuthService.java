package com.correctme.correctme.model.services;

import com.correctme.correctme.model.dao.response.AuthResponse;
import com.correctme.correctme.model.dao.request.AuthenticationRequest;

public interface AuthService {
    AuthResponse register (AuthenticationRequest.RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest request);
}
