package com.example.application;

import com.ngrok.Session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class StartApplication {

//    public static void main(final String[] args) throws IOException {
//        // Session.withAuthtokenFromEnv() will create a new session builder, pulling NGROK_AUTHTOKEN env variable.
//        // You can get your authtoken by registering at https://dashboard.ngrok.com
//        final var sessionBuilder = Session.withAuthtokenFromEnv().metadata("my session");
//        // UTF-8 for encoding
//        final Charset utf8 = StandardCharsets.UTF_8;
//        // Session.Builder let you customize different aspects of the session, see docs for details.
//        // After customizing the builder, you connect:
//        try (final var session = sessionBuilder.connect()) {
//
//            final var listenerBuilder = session.httpEndpoint().metadata("my listener");
//
//            try (final var listener = listenerBuilder.listen()) {
//                System.out.println("ngrok url: " + listener.getUrl());
//                final var buf = ByteBuffer.allocateDirect(1024);
//
//                while (true) {
//                    // Accept a new connection
//                    final var conn = listener.accept();
//
//                    // Read from the connection
//                    buf.clear();
//                    conn.read(buf);
//
//                    System.out.println(utf8.decode(buf));
//
//                    // Or write to it
//                    buf.clear();
//                    buf.put("HTTP/1.0 200 OK\n\nHello from ngrok!".getBytes(utf8));
//                    buf.flip();
//                    conn.write(buf);
//                    conn.close();
//                }
//            }
//        }
//    }
}