import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Fetchtest() {
    const navigate = useNavigate();
    useEffect(() => {
        fetch("http://localhost:8080/api/public", {
            // mode: "cors",
            // redirect: "follow",
            // referrer: "no-referrer",
        })
            .then((response) => {
                console.log("STATUS: ", response.status);
                if (response.redirected && response.url.endsWith("/login")) {
                    console.log(
                        "redirect detected",
                        response.headers.keys.toString(),
                        response.url
                    );
                    throw new Error("login-redirect");
                }
                return response.json();
            })
            .then((value) => {
                console.log("success", value);
            })
            .catch((valeue) => {
                console.log("catch!", valeue);
                if (valeue == "Error: login-redirect") {
                    console.log("login redirect detected");
                    navigate("/profile");
                }
            });
    }, []);
    return <div></div>;
}

export default Fetchtest;
