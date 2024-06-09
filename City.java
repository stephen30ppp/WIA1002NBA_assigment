package views;

import java.util.ArrayList;
import java.util.List;

class City {
    private final String name;
    private final List<City> neighbors;
    private final List<Double> distances;
    private boolean visited;

    public City(String name) {
        this.name = name;
        this.neighbors = new ArrayList<>();
        this.distances = new ArrayList<>();
        this.visited = false;
    }

    public String getName() {
        return name;
    }

    public List<City> getNeighbors() {
        return neighbors;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void addNeighbor(City neighbor, double distance) {
        neighbors.add(neighbor);
        distances.add(distance);
    }
}

class Graph {
    private static Graph instance = null;
    private final List<City> cities;

    private Graph() {
        cities = new ArrayList<>();
        initializeGraph();
    }

    public static Graph getInstance() {
        if (instance == null) {
            instance = new Graph();
        }
        return instance;
    }

    private void initializeGraph() {
        // Adding paths based on your input
        addPath("Spurs", "Suns", 500);
        addPath("Spurs", "Thunder", 678);
        addPath("Spurs", "Rockets", 983);
        addPath("Spurs", "Magic", 1137);
        addPath("Suns", "Lakers", 577);
        addPath("Lakers", "Warriors", 554);
        addPath("Thunder", "Lakers", 1901);
        addPath("Thunder", "Nuggets", 942);
        addPath("Thunder", "Rockets", 778);
        addPath("Thunder", "Warriors", 2214);
        addPath("Rockets", "Celtics", 2584);
        addPath("Rockets", "Magic", 458);
        addPath("Celtics", "Nuggets", 2845);
        addPath("Nuggets", "Warriors", 1507);
        addPath("Magic", "Heat", 268);
        addPath("Heat", "Celtics", 3045);
    }

    private void addPath(String from, String to, double distance) {
        City fromCity = getCity(from);
        City toCity = getCity(to);
        fromCity.addNeighbor(toCity, distance);
        // Unidirectional paths
        toCity.addNeighbor(fromCity, distance);
    }

    public List<String> findPaths() {
        List<String> paths = new ArrayList<>();
        City start = getCity("Spurs");
        List<City> path = new ArrayList<>();
        path.add(start);
        findPathsUtil(start, paths, path, 0);
        return paths;
    }

    private void findPathsUtil(City current, List<String> paths, List<City> path, double distance) {
        current.setVisited(true);

        if (path.size() == cities.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i).getName()).append(" -> ");
            }
            sb.append(path.get(path.size() - 1).getName()).append("  Distance: ").append(distance).append(" km");
            paths.add(sb.toString());
        } else {
            List<City> neighbors = current.getNeighbors();
            List<Double> distances = current.getDistances();

            for (int i = 0; i < neighbors.size(); i++) {
                City neighbor = neighbors.get(i);
                double dist = distances.get(i);

                if (!neighbor.isVisited()) {
                    path.add(neighbor);
                    findPathsUtil(neighbor, paths, path, distance + dist);
                    path.remove(path.size() - 1);
                }
            }
        }

        current.setVisited(false);
    }

    public String findShortestPath() {
        List<String> paths = findPaths();
        double minDistance = Double.MAX_VALUE;
        String shortestPath = "";

        for (String path : paths) {
            String[] parts = path.split("Distance: ");
            double distance = Double.parseDouble(parts[1].split(" km")[0]);
            if (distance < minDistance) {
                minDistance = distance;
                shortestPath = parts[0].trim() + "  Distance: " + distance + " km";
            }
        }

        return shortestPath;
    }

    public List<String> getCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (City city : cities) {
            cityNames.add(city.getName());
        }
        return cityNames;
    }

    private City getCity(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        City newCity = new City(name);
        cities.add(newCity);
        return newCity;
    }
}