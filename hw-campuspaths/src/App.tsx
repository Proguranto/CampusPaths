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

import React, {Component} from 'react';
import logo from "./img/uw_logo.svg";
import husky from "./img/husky.png";
import map from "./img/map.png";

// Allows us to write CSS styles inside App.css, any styles will apply to all components inside <App />
import "./App.css";

interface MapState {
    startText: string;
    endText: string;
    locations: [];
}

function Header() {
    return (
        <div className='header'>
            <img src={logo} className='header-img-1' alt='logo'></img>
            <h1 className='header-title'>Husky Route Planner</h1>
            <img src={husky} className='header-img-2' alt='husky'></img>
        </div>
    );
}

class Map extends React.Component<{}, MapState> {
    constructor(props: any) {
        super(props);
        this.state = {startText: "", endText: "", locations: []};
    }

    async getLocations() {
        let locationPromise = await fetch("http://localhost:4567/locations");
        let locationList = await locationPromise.json();
        this.setState({locations: locationList});
    }

    async onTextChange(evt: any, isStart: boolean) {
        this.removeAutocompleteDropdown(isStart);
        const filteredList: string[] = [];
        const text = evt.target.value;
        if (isStart) {
            this.setState({startText: evt.target.value});
            if (text.length >= 1) {
                this.state.locations.forEach((location: string) => {
                    let shortName = location.split(",", 1)[0];
                    let longName = location.substring(shortName.length + 2);
                    if ((text.toLowerCase() === shortName.substring(0, text.length).toLowerCase())
                        || (text.toLowerCase() === longName.substring(0, text.length).toLowerCase())) {
                        filteredList.push(location);
                    }
                });
            }
            this.createAutocompleteDropdown(filteredList, true);
        } else {
            this.setState({endText: evt.target.value});
            if (text.length >= 1) {
                this.state.locations.forEach((location: string) => {
                    let shortName = location.split(",", 1)[0];
                    let longName = location.substring(shortName.length + 2);
                    if ((text.toLowerCase() === shortName.substring(0, text.length).toLowerCase())
                        || (text.toLowerCase() === longName.substring(0, text.length).toLowerCase())) {
                        filteredList.push(location);
                    }
                });
            }
            this.createAutocompleteDropdown(filteredList, false);
        }
    }

    createAutocompleteDropdown(list: string[], isStart: boolean) {
        
        if (list.length === 0) {
            return;
        }
        
        const listEl = document.createElement("ul");
        listEl.className = "autocomplete-list";

        list.forEach((location) => {
            const listItem = document.createElement("li");
            const locationButton = document.createElement("button");
            locationButton.innerHTML = location;
            locationButton.addEventListener("click", (evt) => {this.onClick(evt, isStart)});
            listItem.appendChild(locationButton);

            listEl.appendChild(listItem);
        });

        if (isStart) {
            listEl.id = "autocomplete-list-start";
            document.querySelector("#start-id")?.appendChild(listEl);
        } else {
            listEl.id = "autocomplete-list-end";
            document.querySelector("#end-id")?.appendChild(listEl);
        }
    }

    removeAutocompleteDropdown(isStart: boolean) {
        let listID = null;
        if (isStart) {
            listID = document.querySelector("#autocomplete-list-start");
        } else {
            listID = document.querySelector("#autocomplete-list-end");
        }
        listID?.remove();
    }

    onClick(evt: any, isStart: boolean) {
        this.removeAutocompleteDropdown(isStart);
        if (isStart) {
            this.setState({startText: evt.target.innerText.split(",", 1)[0]});
        } else {
            this.setState({endText: evt.target.innerText.split(",", 1)[0]});
        }
    }

    async onFindPath(evt: any) {
        this.clearResult();
        const resultID = document.querySelector("#results");
        if (this.state.startText.length <= 0 || this.state.endText.length <= 0) {
            const resultText = document.createElement("div");
            resultText.id = "result-text";
            const text = document.createElement("p");
            text.innerHTML = "Please insert both a start and end location!"
            text.style.color = "red";
            resultText.appendChild(text);
            resultID?.appendChild(resultText);
        } else {
            // check if valid start name and end text.
            let checkPromise = await fetch("http://localhost:4567/check?start=" + this.state.startText + "&end=" + this.state.endText);
            let status = await checkPromise.text();
            if (status === "false") {
                const resultText = document.createElement("div");
                resultText.id = "result-text";
                const text = document.createElement("p");
                text.innerHTML = "Invalid start or end location!"
                text.style.color = "red";
                resultText.appendChild(text);
                resultID?.appendChild(resultText);
            } else {
                let pathPromise = await fetch("http://localhost:4567/path?start=" + this.state.startText + "&end=" + this.state.endText);
                let path = await pathPromise.text();

                const resultText = document.createElement("div");
                resultText.id = "result-text";
                
                if (path === "none") {
                    const text = document.createElement("p");
                    text.innerHTML = "No path exists from " + this.state.startText + " to " + this.state.endText + "!"; 
                    text.style.color = "red";
                    resultText.appendChild(text);
                    resultID?.appendChild(resultText);
                } else {
                    let pathList: any[] = path.split("=> ");
                    let lastEl = pathList.pop();
                    pathList.forEach((segment) => {
                        const seg = document.createElement("p");
                        seg.innerHTML = segment + " =>";
                        seg.style.color = "green";
                        resultText.appendChild(seg);
                    });
                    const seg = document.createElement("p");
                    seg.innerHTML = lastEl;
                    seg.style.color = "green";
                    resultText.appendChild(seg);
                    resultID?.appendChild(resultText);
                }
            }
        }
    }

    clearResult() {
        const resultText = document.querySelector("#result-text");
        resultText?.remove();
    }

    render() {
        if (this.state.locations.length === 0)   this.getLocations();
        return (
            <div className='map-container'>
                <div className='map-text'>
                    <h2>Welcome! 🫰</h2>
                    <p>
                        I made a react application that helps people in the UW campus
                        find paths/routes from one location to another. To use this 
                        application please select a location from the drop down menus
                        in "start" and "end". Then press the "find path" button and the
                        the resulting path will be shown under "path". Thank you for 
                        visiting and using this application!
                    </p>
                    <p>
                        💩 Grant Tannert 💩
                    </p>
                    <div className='map-selection'>
                        <div id='start-id' className='start'>
                            <label>Start:</label>
                            <input type='text' name='locations' value={this.state.startText} onChange={evt => this.onTextChange(evt, true)}></input>
                        </div>
                        <div id='end-id' className='end'>
                            <label>End:</label>
                            <input type='text' name='locations' value={this.state.endText} onChange={evt => this.onTextChange(evt, false)}></input>
                        </div>
                    </div>
                    <button className='find' onClick={evt => this.onFindPath(evt)}>Find Path!</button>
                    <div id='results' className='results'>
                        <h3>Results:</h3>
                    </div>
                </div>
                <img src={map} className='map-img' alt='map'></img>
            </div>
        );
    }
}

class App extends Component<{}, {}> {

    render() {
        return (
            <>
                <Header />
                <Map />
            </>
        );
    }

}

export default App;
