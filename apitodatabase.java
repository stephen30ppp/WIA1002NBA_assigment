package NBA_Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class apitodatabase {
    public static void main(String[] args) {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/tools/chromedriver/chromedriver.exe"); // 替换为你的ChromeDriver路径

        // Initialize WebDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        // Open the NBA players page
        driver.get("https://www.nba.com/players");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/nba_player";
        String dbUser = "root";
        String dbPassword = "Xyg66666";

        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            System.out.println("Database connected successfully!");

            // Prepare the SQL statement
            String sql = "INSERT INTO nba_allplayer (name, team, number, position, height, weight, last_attended, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            boolean hasMorePages = true;
            while (hasMorePages) {
                // Get the page source and parse with Jsoup
                String pageSource = driver.getPageSource();
                Document doc = Jsoup.parse(pageSource);

                // Parse the player data
                Elements players = doc.select("table tbody tr");
                System.out.println("Parsed player data. Number of players found: " + players.size());

                // Iterate over each player and insert data into the database
                for (Element player : players) {
                    Elements playerData = player.select("td");

                    if (playerData.size() > 0) {
                        String name = playerData.get(0).text();
                        String team = playerData.get(1).text();
                        String number = playerData.get(2).text();
                        String position = playerData.get(3).text();
                        String height = playerData.get(4).text();
                        String weight = playerData.get(5).text();
                        String lastAttended = playerData.get(6).text();
                        String country = playerData.get(7).text();

                        // Debug: Print the fetched data
                        System.out.println("Name: " + name + ", Team: " + team + ", Number: " + number + ", Position: " + position + ", Height: " + height + ", Weight: " + weight + ", Last Attended: " + lastAttended + ", Country: " + country);

                        // Set the parameters for the prepared statement
                        statement.setString(1, name);
                        statement.setString(2, team);
                        statement.setString(3, number);
                        statement.setString(4, position);
                        statement.setString(5, height);
                        statement.setString(6, weight);
                        statement.setString(7, lastAttended);
                        statement.setString(8, country);

                        // Execute the insert operation
                        statement.executeUpdate();
                    }
                }

                // Try to find the next page button and click it
                try {
                    WebElement nextPageButton = driver.findElement(By.cssSelector("button.pagination__next")); // Adjust the selector based on actual HTML structure
                    nextPageButton.click();
                    // Wait for the new page to load
                    Thread.sleep(3000);
                } catch (Exception e) {
                    hasMorePages = false;
                }
            }

            // Close the connection
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver
            driver.quit();
        }
    }
}
