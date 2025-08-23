package edu.escuelaing.Lab02.service;

import edu.escuelaing.Lab02.http.HttpRequest;
import edu.escuelaing.Lab02.http.HttpResponse;

public interface Service{

    String invoke(HttpRequest req, HttpResponse res);

} 
