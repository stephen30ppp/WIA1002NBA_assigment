package NBA_Team;

public class Players {
    private String Position;
    private String name;
    private int salary;
    private boolean issuperstar;
    private int age;
    private double height;
    private int weight;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double blocks;


    public Players(){}
    public Players(String Position,String name,int salary,boolean issuperstar,int age,double height,int weight,double points,double assists,double rebounds,double steals,double blocks){
        this.Position=Position;
        this.name=name;
        this.salary=salary;
        this.issuperstar=issuperstar;
        this.age=age;
        this.height=height;
        this.weight=weight;
        this.points=points;
        this.rebounds=rebounds;
        this.assists=assists;
        this.steals=steals;
        this.blocks=blocks;
    }

    /**
     * 获取
     * @return Competition
     */
    public String getPosition() {
        return Position;
    }


    public void setPosition(String Position) {
        this.Position = Position;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * 设置
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * 获取
     * @return issuperstar
     */
    public boolean isIssuperstar() {
        return issuperstar;
    }

    /**
     * 设置
     * @param issuperstar
     */
    public void setIssuperstar(boolean issuperstar) {
        this.issuperstar = issuperstar;
    }

    public String toString() {
        return "Players{Competition = " + Position + ", name = " + name + ", salary = " + salary + ", issuperstar = " + issuperstar + "}";
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * 设置
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * 获取
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 设置
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * 获取
     * @return points
     */
    public double getPoints() {
        return points;
    }

    /**
     * 设置
     * @param points
     */
    public void setPoints(double points) {
        this.points = points;
    }

    /**
     * 获取
     * @return rebounds
     */
    public double getRebounds() {
        return rebounds;
    }

    /**
     * 设置
     * @param rebounds
     */
    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    /**
     * 获取
     * @return assists
     */
    public double getAssists() {
        return assists;
    }

    /**
     * 设置
     * @param assists
     */
    public void setAssists(double assists) {
        this.assists = assists;
    }

    /**
     * 获取
     * @return steals
     */
    public double getSteals() {
        return steals;
    }

    /**
     * 设置
     * @param steals
     */
    public void setSteals(double steals) {
        this.steals = steals;
    }

    /**
     * 获取
     * @return blocks
     */
    public double getBlocks() {
        return blocks;
    }

    /**
     * 设置
     * @param blocks
     */
    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }
}
