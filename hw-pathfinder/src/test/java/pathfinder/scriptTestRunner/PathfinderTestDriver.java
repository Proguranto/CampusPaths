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

package pathfinder.scriptTestRunner;

import graph.Graph;
import pathfinder.DijkstraPathFinder;
import pathfinder.datastructures.Path;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    private final Map<String, Graph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;


    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new PathfinderTestDriver which reads command
     * from {@code r} and writes results to {@code w}
     */
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) || inputLine.charAt(0) == '#') {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        Graph<String, Double> g = new Graph<>();
        graphs.put(graphName, g);
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph.Node<String> node = new Graph.Node<>(nodeName);
        Graph<String, Double> g = graphs.get(graphName);
        g.insertNode(node);

        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);
        Double eLabel = Double.parseDouble(edgeLabel);

        addEdge(graphName, parentName, childName, eLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {

        Graph<String, Double> g = graphs.get(graphName);
        Graph.Node<String> parent = new Graph.Node<>(parentName);
        Graph.Node<String> child = new Graph.Node<>(childName);
        Graph.Edge<String, Double> e = new Graph.Edge<>(parent, child, edgeLabel);
        g.insertEdge(e);

        String eLabel = String.format("%.3f", edgeLabel);

        output.println("added edge " + eLabel + " from "
                + parentName + " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {

        Graph<String, Double> g = graphs.get(graphName);

        // Get Node labels and sort.
        List<Graph.Node<String>> nodes = g.listNodes();
        List<String> nodeLabels = new ArrayList<>();
        for (Graph.Node<String> n : nodes) {
            nodeLabels.add(n.getLabel());
        }
        Collections.sort(nodeLabels);

        // Print desired output.
        output.print(graphName + " contains:");
        for (String s : nodeLabels) {
            output.print(" " + s);
        }
        output.println();
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {

        // Get children nodes and edges and sort them.
        Graph<String, Double> g = graphs.get(graphName);
        Graph.Node<String> n = new Graph.Node<>(parentName);
        List<Graph.Edge<String, Double>> edges = g.childrenOf(n);
        List<String> labels = new ArrayList<>();
        for (Graph.Edge<String, Double> e : edges) {
            labels.add(e.getChild().getLabel() + "(" + e.getLabel() + ")");
        }
        Collections.sort(labels);

        output.print("the children of " + parentName + " in " + graphName + " are:");
        for (String s : labels) {
            output.print(" " + s);
        }
        output.println();
    }

    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }

        String graphName = arguments.get(0);
        String startNode = arguments.get(1);
        String destNode = arguments.get(2);

        findPath(graphName, startNode, destNode);
    }

    private void findPath(String graphName, String startNode, String destNode) {
        Graph<String, Double> g = graphs.get(graphName);
        Graph.Node<String> start = new Graph.Node<>(startNode);
        Graph.Node<String> dest = new Graph.Node<>(destNode);
        DijkstraPathFinder<String, Double> d = new DijkstraPathFinder<>(g);
        Path<Graph.Node<String>> path = d.shortestPathFinder(start, dest);

        output.println("path from " + startNode + " to " + destNode + ":");
        if (!g.containsNode(start) && g.containsNode(dest)) {
            output.println("unknown: " + start.getLabel());
        } else if (g.containsNode(start) && !g.containsNode(dest)) {
            output.println("unknown: " + dest.getLabel());
        } else if (!g.containsNode(start) && !g.containsNode(dest)) {
            output.println("unknown: " + start.getLabel() + ", " + dest.getLabel());
        } else if (path == null) {
            output.println("no path found");
        } else if (start.equals(dest)) {
            String pathCost = String.format("%.3f", path.getCost());
            output.println(start.getLabel() + " to " + dest.getLabel() + " with weight " + pathCost);
            output.println("total cost: " + pathCost);
        } else {
            for (Path<Graph.Node<String>>.Segment p : path) {
                String pathCost = String.format("%.3f", p.getCost());
                output.println(p.getStart().getLabel() + " to " + p.getEnd().getLabel() + " with weight " + pathCost);
            }
            String totalCost = String.format("%.3f", path.getCost());
            output.println("total cost: " + totalCost);
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
