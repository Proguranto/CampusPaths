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
import { useEffect } from 'react';

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

    onTextChange(evt: any, isStart: boolean) {
        if (isStart) {
            this.setState({startText: evt.target.value});
            console.log(this.state.startText);
        } else {
            this.setState({endText: evt.target.value});
            console.log(this.state.endText);
        }
    }

    render() {
        this.getLocations();
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
                        <div className='start'>
                            <label>Start:</label>
                            <input type='text' name='locations' value={this.state.startText} onChange={evt => this.onTextChange(evt, true)}></input>
                            {/* <ul className='autocomplete-list'>
                                <li>
                                    <button>SEA, South East Asia</button>
                                </li>
                                <li>
                                    <button>CAt, Catastrophic Amphrisic Tower</button>
                                </li>
                                <li>
                                    <button>CAt, Catastrophic Amphrisic Tower</button>
                                </li>
                                <li>
                                    <button>CAt, Catastrophic Amphrisic Tower</button>
                                </li>
                                <li>
                                    <button>CAt, Catastrophic Amphrisic Tower</button>
                                </li>
                                <li>
                                    <button>CAt, Catastrophic Amphrisic Tower</button>
                                </li>
                            </ul> */}
                        </div>
                        <div className='end'>
                            <label>End:</label>
                            <input type='text' name='locations' value={this.state.endText} onChange={evt => this.onTextChange(evt, false)}></input>
                        </div>
                    </div>
                    <button>cap</button>
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
