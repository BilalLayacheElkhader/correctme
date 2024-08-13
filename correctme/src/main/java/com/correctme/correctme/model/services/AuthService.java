package com.correctme.correctme.model.services;

import com.correctme.correctme.model.dao.AuthResponse;
import com.correctme.correctme.model.dao.request.AuthenticationRequest;
import com.correctme.correctme.model.dao.response.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest request);
}
