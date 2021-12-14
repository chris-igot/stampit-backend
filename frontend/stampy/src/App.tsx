import React from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Placeholder from "./components/placeholder";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Placeholder />}></Route>
            </Routes>
        </Router>
    );
}

export default App;
