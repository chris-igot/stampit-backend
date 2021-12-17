import React from "react";
import { useNavigate } from "react-router-dom";

function UploadPhoto() {
    const navigate = useNavigate();
    function uploadPhoto() {
        const input = document.querySelector(
            "input[type='file']"
        ) as HTMLInputElement;
        let data = new FormData();
        data.append("file", (input.files as FileList)[0]);
        fetch("/upload", {
            method: "POST",
            body: data,
        }).then((response) => {
            navigate("/profile");
        });
    }
    return (
        <div>
            <input type="file" name="photo" id="photo" />
            <button
                onClick={() => {
                    uploadPhoto();
                }}
            >
                Submit
            </button>
        </div>
    );
}

export default UploadPhoto;
