public class Players {
    private int playerId;
    private String firstName;
    private String lastName;
    private String position;
    private int age;
    private double height;
    private double weight;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double blocks;
    private int salary;
    private boolean issuperstar;


    public Players() {
    }

    public Players(int playerId, String firstName, String lastName, String position, int age, double height, double weight, double points, double rebounds, double assists, double steals, double blocks, int salary, boolean issuperstar) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.salary = salary;
        this.issuperstar = issuperstar;
    }
    public Players(String firstName, String lastName, String position, int age, double height, double weight, double points, double rebounds, double assists, double steals, double blocks, int salary, boolean issuperstar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.salary = salary;
        this.issuperstar = issuperstar;
    }

    public Players(int playerId, String firstName, String lastName, String position, double points, double assists, double rebounds, double steals, double blocks) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.position = position;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public double getSteals() {
        return steals;
    }

    public void setSteals(double steals) {
        this.steals = steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isIssuperstar() {
        return issuperstar;
    }

    public void setIssuperstar(boolean issuperstar) {
        this.issuperstar = issuperstar;
    }
    
    public String getNameOnly() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Players{" + "Position=" + Position + ", name=" + name + ", salary=" + salary + ", issuperstar=" + issuperstar + ", age=" + age + ", height=" + height + ", weight=" + weight + ", points=" + points + ", rebounds=" + rebounds + ", assists=" + assists + ", steals=" + steals + ", blocks=" + blocks + '}';
    }
}
