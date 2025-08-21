package edu.escuelaing.Lab02;

import edu.escuelaing.Lab02.HttpRequest;
import edu.escuelaing.Lab02.HttpResponse;

public interface Service{

    String invoke(HttpRequest req, HttpResponse res);

} 
