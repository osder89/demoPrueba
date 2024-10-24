package com.example.demo;

import com.example.demo.model.Tarea;
import com.example.demo.model.dto.TareaDto;
import com.example.demo.service.TareaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(TareaService tareaService) {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Crear tarea");
                System.out.println("2. Listar tareas");
                System.out.println("3. Actualizar tarea");
                System.out.println("4. Listar tareas pendientes");
                System.out.println("5. Eliminar tarea");
                System.out.println("6. Salir");

                String opcion = scanner.nextLine();
                if ("1".equals(opcion)) {
                    System.out.print("Ingrese el nombre de la tarea: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();

                    TareaDto tareaDto = new TareaDto();
                    tareaDto.setName(nombre);
                    tareaDto.setDescription(descripcion);

                    Tarea tareaCreada = tareaService.create(tareaDto);
                    System.out.println("Tarea creada: " + tareaCreada);
                } else if ("2".equals(opcion)) {
                    System.out.println(tareaService.listAll());
                }else if ("3".equals(opcion)) {
                    System.out.print("Ingrese el codigo de la tarea: ");
                    String codigo = scanner.nextLine();
                    tareaService.completeTarea(Long.parseLong(codigo));
                }else if ("4".equals(opcion)) {
                    System.out.println(tareaService.findByStatusPendiente());
                }else if ("5".equals(opcion)) {
                    System.out.print("Ingrese el codigo de la tarea: ");
                    String codigo = scanner.nextLine();
                    tareaService.delete(Long.parseLong(codigo));
                }else if ("6".equals(opcion)) {
                    break;
                } else {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
            scanner.close();
        };
    }
    // todo Se implemento el siguiente modulo siguiendo una arquitectura MVC ya que da un enfoque claro a la hora de implementar
    //todo nuevas tablas y nuevos modulos. Siendo esta una arquitectura que permite ser escalable pudiendo ser micro servicios o como un modulo en concreto
}
