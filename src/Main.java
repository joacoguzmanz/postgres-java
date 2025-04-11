import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VehicleRepository vehicleRepository = new VehicleRepository();

        Scanner scanner = new Scanner(System.in);
        menuLogic(scanner, vehicleRepository);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nSelect an operation:");
        System.out.println("1. Insert Vehicle");
        System.out.println("2. Get All Vehicles");
        System.out.println("3. Update Vehicle");
        System.out.println("4. Delete Vehicle");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void menuLogic(Scanner scanner, VehicleRepository vehicleRepository) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    Vehicle vehicle = new Vehicle();
                    System.out.print("\n---- Crear nou vehicle ----\n");
                    System.out.print("Introdueix marca: ");
                    vehicle.setMarca(scanner.nextLine());
                    System.out.print("Introdueix model: ");
                    vehicle.setModel(scanner.nextLine());
                    System.out.print("Introdueix capacitat de maleter: ");
                    vehicle.setCapacitat_maleter(scanner.nextInt());
                    vehicleRepository.crear(vehicle);
                    break;
                case "2":
                    List<Vehicle> vehicles = vehicleRepository.listarTodos();
                    for (Vehicle vehicle1 : vehicles) {
                        System.out.println(vehicle1.getId() + " | " + vehicle1.getMarca() + " " + vehicle1.getModel() + " | Capacitat: " + vehicle1.getCapacitat_maleter() + "L");
                    }
                    break;
                case "3":
                    Vehicle vehicleActualitzat = new Vehicle();
                    System.out.print("\n---- Actualitzar dades del vehicle ----\n");
                    System.out.print("Introdueix id: ");
                    vehicleActualitzat.setId(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Introdueix marca: ");
                    vehicleActualitzat.setMarca(scanner.nextLine());
                    System.out.print("Introdueix model: ");
                    vehicleActualitzat.setModel(scanner.nextLine());
                    System.out.print("Introdueix capacitat de maleter: ");
                    vehicleActualitzat.setCapacitat_maleter(scanner.nextInt());
                    vehicleRepository.actualitzar(vehicleActualitzat);
                    break;
                case "4":
                    System.out.print("\n---- Borrar vehiculo ----\n");
                    System.out.print("Enter vehicle id to delete: ");
                    int vehicleId = Integer.parseInt(scanner.nextLine());
                    vehicleRepository.eliminar(vehicleId);
                    break;
                case "5":
                    exit = true;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}