package org.os.simulator;
// ─────────────────────────────────────────────────────────────────────────────
// OsApplication.java  — Spring Boot entry point
//
// This is the ONLY file with a main() method.
// It does three things when you run it:
//   1. Starts an embedded Tomcat web server on port 8080
//   2. Scans com.minios.** for @Component, @Service, @RestController etc.
//      and wires them all together (this is "dependency injection")
//   3. Calls our KernelSimulator to start the tick loop
// ─────────────────────────────────────────────────────────────────────────────

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// @SpringBootApplication is a shortcut for three annotations combined:
//   @Configuration       → this class can define Spring beans
//   @EnableAutoConfiguration → auto-configure Spring features based on
//                              what's on the classpath (e.g. sees websocket
//                              jar → auto-configures WebSocket support)
//   @ComponentScan       → scan this package and all sub-packages for
//                          Spring-managed classes (@Component, @Service, etc.)
@SpringBootApplication

// @EnableScheduling activates Spring's @Scheduled annotation support.
// Without this, any @Scheduled methods are silently ignored.
// We use this in KernelSimulator to drive the tick loop.
// NOTE: We're actually using ScheduledExecutorService directly (more control),
// but keeping this here for future use and Spring's task executor integration.
@EnableScheduling
public class OSApplication {

    public static void main(String[] args) {
        // SpringApplication.run() is the ignition key.
        // It bootstraps the entire application context:
        //   - Reads application.yml
        //   - Creates and wires all @Component beans
        //   - Starts the embedded Tomcat server
        //   - Calls any @PostConstruct methods (our kernel start)
        SpringApplication.run(OSApplication.class, args);
    }
}