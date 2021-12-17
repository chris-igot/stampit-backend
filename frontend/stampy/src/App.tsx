import React from "react";
import "./App.css";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Placeholder from "./components/placeholder";
import UserProfile from "./components/user_profile";
import UploadPhoto from "./components/upload_photo";

function App() {
    return (
        <Router>
            <nav>
                <Link to="/placeholder">placeholder</Link>
                <Link to="/profile">profile</Link>
                <Link to="/upload">upload</Link>
            </nav>
            <Routes>
                <Route path="/placeholder" element={<Placeholder />}></Route>
                <Route path="/profile" element={<UserProfile />}></Route>
                <Route path="/upload" element={<UploadPhoto />}></Route>
            </Routes>
        </Router>
    );
}

export default App;
