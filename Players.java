package NBA_Team;

public class Players {
    private String Position;
    private String name;
    private double salary;
    private boolean issuperstar;
    public Players(){}
    public Players(String Position,String name,double salary,boolean issuperstar){
        this.Position=Position;
        this.name=name;
        this.salary=salary;
        this.issuperstar=issuperstar;
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
    public double getSalary() {
        return salary;
    }

    /**
     * 设置
     * @param salary
     */
    public void setSalary(double salary) {
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
}
