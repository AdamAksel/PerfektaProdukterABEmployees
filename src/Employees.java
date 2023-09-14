import java.util.Date;

// superklass anställd
class Employee {
    protected String name;
    protected String gender;
    protected int id;

    public Employee(String name, String gender, int id) {
        this.name = name;
        this.gender = gender;
        this.id = id;
    }
}

// subklass perm
class PermanentEmployee extends Employee {
    private double salary;
    private Date startDate;

    public PermanentEmployee(String name, String gender, int id, double salary, Date startDate) {
        super(name, gender, id);
        this.salary = salary;
        this.startDate = startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }
}

// subklass temp
class TemporaryEmployee extends Employee {
    private Date endDate;
    private String recommendation;

    public TemporaryEmployee(String name, String gender, int id, Date endDate) {
        super(name, gender, id);
        this.endDate = endDate;
        this.recommendation = "";
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
