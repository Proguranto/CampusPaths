/*
 * Copyright (C) 2022 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the API that the text interface
 * view/controller require models to implement.
 */
public class CampusMap implements ModelAPI {

    private static final boolean DEBUG = false;

    // Represents a campus map as a graph where points are stored as nodes
    // and path costs represented as edge labels.
    //
    // RI: graph != null and buildings != null and paths != null
    //     graph, buildings, paths do not contain null.
    // AF(this) = campus map represented by the graph
    private Graph<Point, Double> graph;

    private Map<String, CampusBuilding> buildings;

    private List<CampusPath> paths;

    /**
     *
     */
    public CampusMap() {
        graph = new Graph<>();
        buildings = new HashMap<>();
        for (CampusBuilding c : CampusPathsParser.parseCampusBuildings("campus_buildings.csv")) {
            buildings.put(c.getShortName(), c);
        }
        paths = CampusPathsParser.parseCampusPaths("campus_paths.csv");

        // Constructing graph.
        for(CampusPath c : paths) {
            Graph.Node<Point> n1 = new Graph.Node<>(new Point(c.getX1(), c.getY1()));
            Graph.Node<Point> n2 = new Graph.Node<>(new Point(c.getX2(), c.getY2()));
            Graph.Edge<Point, Double> e = new Graph.Edge<>(n1, n2, c.getDistance());
            graph.insertNode(n1);
            graph.insertNode(n2);
            graph.insertEdge(e);
        }
        checkRep();
    }

    @Override
    public boolean shortNameExists(String shortName) {
        checkRep();
        return buildings.containsKey(shortName);
    }

    @Override
    public String longNameForShort(String shortName) {
        checkRep();
        if (!shortNameExists(shortName)) {
            throw new IllegalArgumentException("Campus map does not contain a building with provided short name.");
        }
        CampusBuilding c = buildings.get(shortName);
        checkRep();
        return c.getLongName();
    }

    @Override
    public Map<String, String> buildingNames() {
        checkRep();
        Map<String, String> buildingNames = new HashMap<>();
        for (String s : buildings.keySet()) {
            buildingNames.put(s, buildings.get(s).getLongName());
        }

        checkRep();
        return buildingNames;
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        checkRep();
        CampusBuilding startBuilding = buildings.get(startShortName);
        CampusBuilding endBuilding = buildings.get(endShortName);
        Graph.Node<Point> start = new Graph.Node<>(new Point(startBuilding.getX(), startBuilding.getY()));
        Graph.Node<Point> dest = new Graph.Node<>(new Point(endBuilding.getX(), endBuilding.getY()));

        DijkstraPathFinder<Point, Double> algo = new DijkstraPathFinder<>(graph);
        checkRep();
        return algo.shortestPathFinder(start.getLabel(), dest.getLabel());
    }

    private void checkRep() {
        assert graph != null : "graph is null!";
        assert buildings != null : "buildings is null!";
        assert paths != null : "paths is null!";

        if (DEBUG) {
            assert !graph.containsNode(null) : "graph contains null!";
            assert !graph.containsEdge(null) : "graph contains null!";
            for (String s : buildings.keySet()) {
                assert s != null;
            }
            assert !paths.contains(null);
        }
    }
}
