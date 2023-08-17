# 🗺 Husky Route Planner 🗺
## Introduction
Husky Route Planner is a react web application that allows users to find a path from a start and end location in the University of Washington campus. The application will then result a path where it will be listed in a coordinate to coordinate fashion.  
  
Here is a demo of the web application:
![demo](./demo.gif)

## Languages & Frameworks
* Java
* Typescript
* ReactJS
* Spark Java (Not Apache Spark)
  
## How
I created the frontend GUI using ReactJS and hosted the server locally.  
I used Spark Java to host the server locally.  
The react server communicates to the server to fetch data from the server such as the list of locations or finding the shortest path.  
In the backend, I designed a directed labeled Graph ADT to represent the locations and paths. Then, I used Dijkstra's algorithm to calculate and find the shortest path from one location to another.  
I also implemented the Model-View-Controller(MVC) and Event-Driven paradigm to reduce coupling between frontend and backend.

## Thank you for visiting! 👋
Made by: Grant Tannert  
Credits to CSE 331 course for the guidance on creating the application!