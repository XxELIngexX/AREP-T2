package edu.escuelaing.Lab02;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.*;

import edu.escuelaing.Lab02.app.WebApplication;
import edu.escuelaing.Lab02.http.HttpServer;

public class AppTest {

    @BeforeClass
    public static void initServices() {
        // Inicializa los servicios del WebApplication
        // con esto se registran /menu, /adduser, /getUser, /hello
        WebApplication.main(new String[]{});
    }

    @Test
    public void testGetMenu() throws IOException {
        String fakeRequest = "GET /menu HTTP/1.1\r\nHost: localhost\r\n\r\n";
        ByteArrayInputStream input = new ByteArrayInputStream(fakeRequest.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(input, output);
        HttpServer.handleRequest(fakeSocket);

        String response = output.toString();
        assertTrue("La respuesta debe ser 200 OK", response.contains("200 OK"));
        assertTrue("La respuesta debe contener un JSON de platos", response.contains("["));
    }

    @Test
    public void testPostHomeCreatesUser() throws IOException {
        String body = "{\"name\":\"cesar\"}";
        String fakeRequest = "POST /home HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Length: " + body.length() + "\r\n\r\n" +
                body;

        ByteArrayInputStream input = new ByteArrayInputStream(fakeRequest.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(input, output);
        HttpServer.handleRequest(fakeSocket);

        String response = output.toString();
        assertTrue("Debe devolver 200 OK", response.contains("200 OK"));
        assertTrue("Debe contener el nombre del usuario", response.contains("cesar"));
    }

    @Test
    public void testGetUserAfterPost() throws IOException {
        // Simulamos que ya existe "cesar" del test anterior
        String fakeRequest = "GET /getUser?name=cesar HTTP/1.1\r\nHost: localhost\r\n\r\n";
        ByteArrayInputStream input = new ByteArrayInputStream(fakeRequest.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        Socket fakeSocket = new FakeSocket(input, output);
        HttpServer.handleRequest(fakeSocket);

        String response = output.toString();
        assertTrue("Debe devolver 200 OK", response.contains("200 OK"));
        assertTrue("Debe contener el JSON del usuario cesar", response.contains("cesar"));
    }

    // --- FakeSocket para simular la conexión ---
    static class FakeSocket extends Socket {
        private final InputStream input;
        private final OutputStream output;

        FakeSocket(InputStream input, OutputStream output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public InputStream getInputStream() {
            return input;
        }

        @Override
        public OutputStream getOutputStream() {
            return output;
        }

        @Override
        public synchronized void close() {
            // no hacemos nada para evitar cerrar los streams
        }
    }
}
