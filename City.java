import java.util.*;

public class City {
    private String cityName;
    private List<City> neighbors; // List of neighboring cities
    private List<Double> distances; // Distances to neighboring cities
    private Graph graph; // Reference to the graph containing connections
    private String teamName; // Team name associated with the city
    private boolean isMainNode; // Indicates if this city is the main node (your team)

    // Constructor
    public City(String cityName, String teamName, boolean isMainNode, Graph graph) {
        this.cityName = cityName;
        this.neighbors = new ArrayList<>();
        this.distances = new ArrayList<>();
        this.teamName = teamName;
        this.isMainNode = isMainNode;
        this.graph = graph; // Set the graph object
    }

    // Method to add a neighboring city with distance
    public void addNeighbor(City neighbor, double distance) {
        neighbors.add(neighbor);
        distances.add(distance);
    }

    // Method to get neighboring cities
    public List<City> getNeighbors() {
        return neighbors;
    }

    // Method to get distances to neighboring cities
    public List<Double> getDistances() {
        return distances;
    }

    public String getCityName() {
        return cityName;
    }

    // Method to set the graph containing connections
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    // Method to check if two cities are directly connected
    public boolean isConnected(City city) {
        return neighbors.contains(city);
    }

    // Getter for team name
    public String getTeamName() {
        return teamName;
    }

    // Setter for team name
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    // Method to check if this city is the main node
    public boolean isMainNode() {
        return isMainNode;
    }


    // Method to get the shortest distance between cities of different teams using BFS
    public List<List<City>> getShortestDistanceBetweenTeams(String team1, String team2) {
        List<City> cities1 = graph.getCitiesByTeam(team1);
        List<City> cities2 = graph.getCitiesByTeam(team2);
        double shortestDistance = Double.MAX_VALUE;
        List<List<City>> shortestPaths = new ArrayList<>();

        for (City city1 : cities1) {
            for (City city2 : cities2) {
                List<List<City>> paths = bfs(city1, city2);
                for (List<City> path : paths) {
                    double distance = calculateDistance(path);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        shortestPaths.clear();
                        shortestPaths.add(path);
                    } else if (distance == shortestDistance) {
                        shortestPaths.add(path);
                    }
                }
            }
        }

        return shortestPaths;
    }

    // Method to perform Breadth-First Search (BFS) and return all paths found
    private List<List<City>> bfs(City start, City target) {
        Queue<List<City>> queue = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        List<List<City>> paths = new ArrayList<>();
        List<City> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            List<City> currentPath = queue.poll();
            City currentCity = currentPath.get(currentPath.size() - 1);
            if (currentCity.equals(target)) {
                paths.add(currentPath);
            }
            visited.add(currentCity);
            for (City neighbor : currentCity.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    List<City> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.offer(newPath);
                }
            }
        }

        return paths;
    }





    // Method to calculate the total distance of a path
    private static double calculateDistance(List<City> path) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int index = path.get(i).getNeighbors().indexOf(path.get(i + 1));
            if (index != -1) {
                totalDistance += path.get(i).getDistances().get(index);
            } else {
                // Handle case where neighbors and distances are not synchronized
                throw new IllegalArgumentException("Neighbors and distances are not synchronized");
            }
        }
        return totalDistance;
    }

    public static void main(String[] args) {

        Graph graph=new Graph();

        // Create cities
        City city1 = new City("San Antonio", "Spurs", true, graph);
        City city2 = new City("Golden State", "Warriors", false, graph);
        City city3 = new City("Boston", "Celtics", false, graph);
        City city4 = new City("Miami", "Heat", false, graph);
        City city5 = new City("Los Angeles", "Lakers", false, graph);
        City city6 = new City("Phoenix", "Suns", false, graph);
        City city7 = new City("Orlando", "Magic", false, graph);
        City city8 = new City("Denver", "Nuggets", false, graph);
        City city9 = new City("Oklahoma City", "Thunder", false, graph);
        City city10 = new City("Houston", "Rockets", false, graph);


        // Add edges between cities with distances(km)
        graph.addEdge(city1, city6, 500.0);
        graph.addEdge(city1, city9, 678.0);
        graph.addEdge(city1, city10, 983.0);
        graph.addEdge(city1, city7, 1137.0);
        graph.addEdge(city2, city8, 1507.0);
        graph.addEdge(city2, city5, 554.0);
        graph.addEdge(city2, city9, 2214.0);
        graph.addEdge(city3, city8, 2845.0);
        graph.addEdge(city3, city10, 2584.0);
        graph.addEdge(city3, city4, 3045.0);
        graph.addEdge(city4, city7, 268.0);
        graph.addEdge(city5, city9, 1901.0);
        graph.addEdge(city5, city6, 577.0);
        graph.addEdge(city7, city10, 458.0);
        graph.addEdge(city8, city9, 942.0);
        graph.addEdge(city9, city10, 778.0);

        // Display the distance matrix
        graph.displayDistanceMatrix();

        // Find the shortest distance between teams
        List<List<City>> shortestPaths = city1.getShortestDistanceBetweenTeams("Spurs", "Warriors");

        // Output the shortest paths
        System.out.println("Shortest Paths:");
        for (int i = 0; i < shortestPaths.size(); i++) {
            List<City> shortestPath = shortestPaths.get(i);
            System.out.print("Path " + (i + 1) + ": ");
            for (int j = 0; j < shortestPath.size(); j++) {
                System.out.print(shortestPath.get(j).getTeamName());
                if (j < shortestPath.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}

class Graph {
    private Map<String, List<City>> teamAdjacencyList;

    // Constructor
    public Graph() {
        teamAdjacencyList = new HashMap<>();
    }

    // Method to add an edge between two cities with distance
    public void addEdge(City source, City destination, double distance) {
        String teamName = source.getTeamName();
        teamAdjacencyList.putIfAbsent(teamName, new ArrayList<>());
        teamAdjacencyList.get(teamName).add(source);
        teamAdjacencyList.get(teamName).add(destination);
        source.addNeighbor(destination, distance);
        destination.addNeighbor(source, distance);
    }

    // Method to get cities belonging to a team
    public List<City> getCitiesByTeam(String teamName) {
        return teamAdjacencyList.getOrDefault(teamName, new ArrayList<>());
    }

    // Method to get the shortest distance between cities of different teams
    public double getShortestDistanceBetweenTeams(String team1, String team2) {
        List<City> cities1 = getCitiesByTeam(team1);
        List<City> cities2 = getCitiesByTeam(team2);
        double shortestDistance = Double.MAX_VALUE;

        for (City city1 : cities1) {
            for (City city2 : cities2) {
                double distance = getShortestDistance(city1, city2);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                }
            }
        }

        return shortestDistance;
    }

    // Method to get the shortest distance between two cities
    public double getShortestDistance(City source, City destination) {
        List<Double> distances = source.getDistances();
        List<City> neighbors = source.getNeighbors();
        int index = neighbors.indexOf(destination);
        if (index != -1) {
            return distances.get(index);
        } else {
            return Double.MAX_VALUE;
        }
    }

    // Method to get neighboring cities of a city
    public List<City> getDistances(City city) {
        String teamName = city.getTeamName();
        return teamAdjacencyList.getOrDefault(teamName, new ArrayList<>());
    }

    // Method to check if two cities are connected
    public boolean isConnected(City city1, City city2) {
        return city1.isConnected(city2);
    }


    public void displayDistanceMatrix() {
        // Collect all unique team names
        Set<String> teamNames = new TreeSet<>(teamAdjacencyList.keySet());

        // Determine the maximum width needed for team names
        int maxTeamNameWidth = teamNames.stream().mapToInt(String::length).max().orElse(0);

        // Create the matrix header
        String headerFormat = "%-" + maxTeamNameWidth + "s";
        System.out.printf(headerFormat + "\t", ""); // Placeholder for corner
        for (String teamName : teamNames) {
            System.out.printf(headerFormat + "\t", teamName);
        }
        System.out.println();

        // Create the matrix rows
        for (String rowTeam : teamNames) {
            String rowFormat = "%-" + maxTeamNameWidth + "s";
            System.out.printf(rowFormat + "\t", rowTeam);
            for (String colTeam : teamNames) {
                List<City> cities1 = getCitiesByTeam(rowTeam);
                List<City> cities2 = getCitiesByTeam(colTeam);
                double shortestDistance = Double.MAX_VALUE;

                for (City city1 : cities1) {
                    for (City city2 : cities2) {
                        double distance = getShortestDistance(city1, city2);
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                        }
                    }
                }

                System.out.printf("%-" + maxTeamNameWidth + "s\t", shortestDistance != Double.MAX_VALUE ? String.valueOf(shortestDistance) : "-");
            }
            System.out.println();
        }
    }



    // Method to get a city by its name
    private City getCityByName(String cityName) {
        for (List<City> cities : teamAdjacencyList.values()) {
            for (City city : cities) {
                if (city.getCityName().equals(cityName)) {
                    return city;
                }
            }
        }
        return null;
    }
}
