import React from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Placeholder from "./components/placeholder";
import UserProfile from "./components/user_profile";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Placeholder />}></Route>
                <Route path="/profile" element={<UserProfile />}></Route>
            </Routes>
        </Router>
    );
}

export default App;
