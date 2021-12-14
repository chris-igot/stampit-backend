import React, { useState, useEffect } from "react";
import { ProfileType, PostType } from "../../ts_types/types";

function UserProfile() {
    const [profile, setProfile] = useState<ProfileType>(getProfile());
    useEffect(() => {
        setProfile(getProfile(5));
    }, []);
    function getProfile(id?: number | string) {
        let posts: PostType[] = [];
        for (let i = 0; i < 12; i++) {
            posts.push({
                id: i,
                pic_url: "https://picsum.photos/100?random=" + i,
                stamps: [],
            });
        }
        let profile: ProfileType = {
            user_info: {
                id: id ? id : "none",
                name: id ? "person's name" : "no one",
                text: id ? "profile info" : "",
                pic_url: id
                    ? "https://picsum.photos/100?random=1000"
                    : "https://picsum.photos/id/870/100/100?grayscale&blur=2",
            },
            posts,
        };
        console.log(profile);

        return profile;
    }
    return (
        <div className="">
            <div>
                <div className="flex flex-wrap">
                    <img
                        src={profile.user_info.pic_url}
                        alt="thumbnail"
                        className=""
                    />
                    <span>{profile.user_info.name}</span>
                </div>
                <div>
                    <p>{profile.user_info.text}</p>
                </div>
            </div>
            <div className="flex flex-wrap">
                {(profile as ProfileType).posts.map((post) => (
                    <a href={"/post/" + post.id}>
                        <img src={post.pic_url} alt="thumbnail" className="" />
                    </a>
                    // <p>{url}</p>
                ))}
            </div>
        </div>
    );
}

export default UserProfile;
