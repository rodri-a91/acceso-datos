package ceu.dam.ad.users.test;

import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.services.DuplicateUserException;
import ceu.dam.ad.users.services.UserNotFoundException;
import ceu.dam.ad.users.services.UserService;
import ceu.dam.ad.users.services.UserServiceImp;
import ceu.dam.ad.users.services.UserUnauthorizedException;

public class Test {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO BATERÍA DE PRUEBAS ===");

        UserService userService = new UserServiceImp();

        try {
            // --- 1. CREACIÓN DE USUARIO (ÉXITO) ---
            System.out.println("\n--- TEST 1: Crear usuarios válidos ---");
            
            User u1 = new User();
            u1.setUsername("jdoe");
            u1.setName("John Doe");
            u1.setEmail("john.doe@example.com");
            u1.setPassword("Secret123!");

            User u1Creado = userService.createUser(u1);
            System.out.println("✅ Usuario 1 creado correctamente con ID: " + u1Creado.getId());

            User u2 = new User();
            u2.setUsername("mgarcia");
            u2.setName("María García");
            u2.setEmail("mgarcia@example.com");
            u2.setPassword("ClaveSegura2026");

            User u2Creado = userService.createUser(u2);
            System.out.println("✅ Usuario 2 creado correctamente con ID: " + u2Creado.getId());


            // --- 2. CREACIÓN DUPLICADA (EXCEPCIONES) ---
            System.out.println("\n--- TEST 2: Control de duplicados ---");
            
            // Mismo username
            try {
                User dup = new User();
                dup.setUsername("jdoe");
                dup.setName("Otro Nombre");
                dup.setEmail("otro@example.com");
                dup.setPassword("Pass123");
                userService.createUser(dup);
                System.err.println("❌ FALLO: Debería haber saltado DuplicateUserException por username");
            } catch (DuplicateUserException e) {
                System.out.println("✅ Excepción capturada correctamente (Username duplicado): " + e.getMessage());
            }

            // Mismo email
            try {
                User dup = new User();
                dup.setUsername("usuarioUnico");
                dup.setName("Otro Nombre");
                dup.setEmail("john.doe@example.com");
                dup.setPassword("Pass123");
                userService.createUser(dup);
                System.err.println("❌ FALLO: Debería haber saltado DuplicateUserException por email");
            } catch (DuplicateUserException e) {
                System.out.println("✅ Excepción capturada correctamente (Email duplicado): " + e.getMessage());
            }


            // --- 3. BÚSQUEDA POR ID ---
            System.out.println("\n--- TEST 3: Obtener usuario por ID ---");
            
            User userObtenido = userService.getUser(u1Creado.getId());
            System.out.println("✅ Usuario obtenido de BBDD: " + userObtenido.getUsername());

            try {
                userService.getUser(999999L);
                System.err.println("❌ FALLO: Debería haber saltado UserNotFoundException");
            } catch (UserNotFoundException e) {
                System.out.println("✅ Excepción capturada correctamente (ID inexistente): " + e.getMessage());
            }


            // --- 4. LOGIN ---
            System.out.println("\n--- TEST 4: Proceso de Login ---");

            // Login con Username
            User login1 = userService.login("jdoe", "Secret123!");
            System.out.println("✅ Login exitoso por username. Último acceso: " + login1.getLastLoginDate());

            // Login con Email
            User login2 = userService.login("mgarcia@example.com", "ClaveSegura2026");
            System.out.println("✅ Login exitoso por email. Último acceso: " + login2.getLastLoginDate());

            // Credenciales erróneas
            try {
                userService.login("jdoe", "PasswordIncorrecta");
                System.err.println("❌ FALLO: Debería haber saltado UserUnauthorizedException");
            } catch (UserUnauthorizedException e) {
                System.out.println("✅ Excepción capturada correctamente (Contraseña incorrecta): " + e.getMessage());
            }

            try {
                userService.login("no_existo", "Password123");
                System.err.println("❌ FALLO: Debería haber saltado UserNotFoundException");
            } catch (UserNotFoundException e) {
                System.out.println("✅ Excepción capturada correctamente (Usuario inexistente): " + e.getMessage());
            }


            // --- 5. CAMBIO DE CONTRASEÑA ---
            System.out.println("\n--- TEST 5: Cambio de contraseña ---");

            // Cambio correcto
            userService.changePassword(u1Creado.getId(), "Secret123!", "NuevaClave456!");
            System.out.println("✅ Contraseña cambiada con éxito");

            // Verificar login con la clave nueva
            userService.login("jdoe", "NuevaClave456!");
            System.out.println("✅ Verificación: Login exitoso con la nueva clave");

            // Intentar cambiar con la clave antigua incorrecta
            try {
                userService.changePassword(u1Creado.getId(), "Secret123!", "OtraMas789!");
                System.err.println("❌ FALLO: Debería haber saltado UserUnauthorizedException por clave antigua mal puesta");
            } catch (UserUnauthorizedException e) {
                System.out.println("✅ Excepción capturada correctamente (Clave antigua errónea): " + e.getMessage());
            }

            // Intentar poner la misma clave
            try {
                userService.changePassword(u1Creado.getId(), "NuevaClave456!", "NuevaClave456!");
                System.err.println("❌ FALLO: Debería haber saltado UserUnauthorizedException por passwords iguales");
            } catch (UserUnauthorizedException e) {
                System.out.println("✅ Excepción capturada correctamente (Misma contraseña): " + e.getMessage());
            }

            System.out.println("\n=== BATERÍA DE PRUEBAS COMPLETADA CON ÉXITO ===");

        } catch (Exception e) {
            System.err.println("💥 ERROR EN LAS PRUEBAS:");
            e.printStackTrace();
        }
    }
}