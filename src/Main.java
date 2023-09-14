import java.util.*;
import java.text.SimpleDateFormat;

public class Main {

    private static List<Employee> employees = new ArrayList<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Permanent Employee");
            System.out.println("2. Add Temporary Employee");
            System.out.println("3. Display total employees");
            System.out.println("4. Display average pay for permanent employees");
            System.out.println("5. Display average pay based on gender for permanent employees");
            System.out.println("6. Display employees by hiring order");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // lägg till employee
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter gender:");
                    String gender = scanner.nextLine();
                    System.out.println("Enter ID:");
                    int id = scanner.nextInt();
                    System.out.println("Enter salary:");
                    double salary = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter start date (dd/MM/yyyy):");
                    Date startDate = null;
                    try {
                        startDate = sdf.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }
                    employees.add(new PermanentEmployee(name, gender, id, salary, startDate));
                    break;

                case 2:
                    // lägg till temp employee
                    System.out.println("Enter name:");
                    name = scanner.nextLine();
                    System.out.println("Enter gender:");
                    gender = scanner.nextLine();
                    System.out.println("Enter ID:");
                    id = scanner.nextInt();
                    System.out.println("Enter recommendation");
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter end date (dd/MM/yyyy):");
                    Date endDate = null;
                    try {
                        endDate = sdf.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }
                    TemporaryEmployee tempEmp = new TemporaryEmployee(name, gender, id, endDate);
                    employees.add(tempEmp);

                    System.out.println("Enter recommendation for the employee:");
                    String recommendation = scanner.nextLine();
                    tempEmp.setRecommendation(recommendation);
                    break;

                case 3:
                    // visa totalt antal employees
                    System.out.println("Total employees: " + employees.size());
                    break;

                case 4:
                    // genomsnittslön på alla
                    double totalSalary = 0;
                    int permCount = 0;
                    for (Employee e : employees) {
                        if (e instanceof PermanentEmployee) {
                            totalSalary += ((PermanentEmployee) e).getSalary();
                            permCount++;
                        }
                    }
                    double averageSalary = (permCount == 0) ? 0 : totalSalary / permCount;
                    System.out.println("Average salary of permanent employees: " + averageSalary);
                    break;

                case 5:
                    // genomsnittslön baserat på kön
                    double maleTotalSalary = 0;
                    double femaleTotalSalary = 0;
                    int maleCount = 0;
                    int femaleCount = 0;
                    for (Employee e : employees) {
                        if (e instanceof PermanentEmployee) {
                            if ("male".equalsIgnoreCase(e.gender)) {
                                maleTotalSalary += ((PermanentEmployee) e).getSalary();
                                maleCount++;
                            } else if ("female".equalsIgnoreCase(e.gender)) {
                                femaleTotalSalary += ((PermanentEmployee) e).getSalary();
                                femaleCount++;
                            }
                        }
                    }
                    double maleAvgSalary = (maleCount == 0) ? 0 : maleTotalSalary / maleCount;
                    double femaleAvgSalary = (femaleCount == 0) ? 0 : femaleTotalSalary / femaleCount;
                    System.out.println("Average salary for male permanent employees: " + maleAvgSalary);
                    System.out.println("Average salary for female permanent employees: " + femaleAvgSalary);
                    break;

                case 6:
                    // visa anställda efter datum
                    employees.sort(Comparator.comparing(e -> {
                        if (e instanceof PermanentEmployee) {
                            return ((PermanentEmployee) e).getStartDate();
                        }
                        return new Date(Long.MAX_VALUE);  // Ensure temporary employees come last
                    }));
                    for (Employee e : employees) {
                        System.out.println(e.name + " - " + (e instanceof PermanentEmployee ? ((PermanentEmployee) e).getStartDate() : "Temporary"));
                    }
                    break;

                case 7:
                    System.out.println("I am afraid i can't do that dave");
                    try {
                        for (int i = 0; i < 3; i++) {
                            Thread.sleep(1000);
                            System.out.println(".");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Just Kidding, Bye Bye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
