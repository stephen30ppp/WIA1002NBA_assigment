package NBA_Team;

import org.jsoup.nodes.Range;

import java.sql.*;
import java.util.Scanner;

import static NBA_Team.Team.calculateAndPrintRankings;
import static NBA_Team.Team.findPlayers;

public class Databaseconnect {
    private static final String URL1="jdbc:mysql://127.0.0.1:3306/nba_player";
    private static final String USED="root";
    private static final String PASSWORD="Xyg66666";

    public static void main(String[] args) {
        Statement selectStatement = null;
        PreparedStatement pstmt;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Scanner sc=new Scanner(System.in);
        String url = "https://nba.com/players";
        try {
            // 注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开连接
            connection = DriverManager.getConnection(URL1, USED, PASSWORD);





            // Database connection





            // 执行查询
            ResultSet set=connection.createStatement().executeQuery("select  * from team_players ");
           Team team=new Team();
           InjuryReserve injuryReserve=new InjuryReserve();
            while (set.next()) {
                Players players1 = new Players(set.getString(5), set.getString(1), set.getInt(6), true, set.getInt(2), set.getDouble(3), set.getInt(4), set.getDouble(7), set.getDouble(9), set.getDouble(8), set.getDouble(10), set.getDouble(11));

                team.addPlayer(players1);

            }
            while (true) {
                System.out.println("please input number");
                switch (sc.nextInt()) {
                    case 1: team.printteam();
//                        calculateAndPrintRankings();
                    break;
                    case 2:
                        boolean issuper=false;
                        String insertSQL="INSERT INTO team_players (name,Age,Height,Weight,Position,Salary,Points,Rebounds,Assists,Steals,Blocks) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                        ResultSet set1=connection.createStatement().executeQuery("select  * from nba_allplayer ");
                        while (set1.next()){
                            System.out.println("Name: " + set1.getString(1) + ", Team: " + set1.getString(2) + ", Number: " + set1.getString(3) + ", Position: " + set1.getString(4) + ", Height: " + set1.getString(5) + ", Weight: " + set1.getString(6));
                        }
                        pstmt=connection.prepareStatement(insertSQL);
                        System.out.println("please input your select player and his information");
                        System.out.println("name:");
                        String name=sc.next();
                        pstmt.setString(1,name);
                        System.out.println("Age:");
                        int age=sc.nextInt();
                        pstmt.setInt(2,age);
                        System.out.println("Height:");
                        double height=sc.nextDouble();
                        pstmt.setDouble(3,height);
                        System.out.println("Weight:");
                        int weight=sc.nextInt();
                        pstmt.setInt(4,weight);
                        System.out.println("Position:");
                        String position=sc.next();
                        pstmt.setString(5, position);
                        System.out.println("Salary:");
                        int salary=sc.nextInt();
                        pstmt.setInt(6,salary);
                        System.out.println("Points:");
                        double points=sc.nextDouble();
                        if (points>20){
                             issuper=true;
                        }
                        pstmt.setDouble(7,points);
                        System.out.println("Rebounds:");
                        double rebounds=sc.nextDouble();
                        pstmt.setDouble(8,rebounds);
                        System.out.println("Assists：");
                        double assistants=sc.nextDouble();
                        pstmt.setDouble(9,assistants);
                        System.out.println("Steals:");
                        double steals=sc.nextDouble();
                        pstmt.setDouble(10,steals);
                        System.out.println("Block:");
                        double blocks=sc.nextDouble();
                        pstmt.setDouble(11,blocks);
                        int check=pstmt.executeUpdate();
                        if (check>0){
                            System.out.println("add player success");
                        }else {
                            System.out.println("add player fail");
                        }
                        Players newplayer=new Players(position,name,salary,issuper,age,height,weight,points,assistants,rebounds,steals,blocks);
                        team.addPlayer(newplayer);
                        break;
                    case 3:
                        System.out.println("Player:");
                        String p=sc.next();
                        System.out.println("Injury:");
                        String inj=sc.next();
                        injuryReserve.addInjuredPlayer(p,inj);

                    break;
                    case 4:
                        System.out.println("please input you want search player infomation");
                        System.out.println("minHeight:");
                        double minHeight=sc.nextDouble();
                        System.out.println("minWeight");
                        int minWeight= sc.nextInt();
                        System.out.println("Position:");
                        String positioninfo= sc.next();
                        findPlayers(minHeight,minWeight,positioninfo);
                        break;
                    case 5:
                        String deletsql="DELETE FROM team_players WHERE name=?";
                        pstmt =connection.prepareStatement(deletsql);
                        System.out.println("please select players you want to delete");
                        String deletename=sc.next();
                        pstmt.setString(1,deletename);
                        int checkdlete=pstmt.executeUpdate();
                        if (checkdlete>0){
                            System.out.println("you successful delete the "+deletename);
                        }else {
                            System.out.println("delete fail ");
                        }
                    case 6:calculateAndPrintRankings();
                        break;
                    case 7:System.exit(0);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        } finally {
            // 清理环境
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}