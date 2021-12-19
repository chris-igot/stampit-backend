import React, { useState, useEffect } from "react";
import { UserType, PostType } from "../../ts_types/types";

function UserProfile() {
    const [user, setUser] = useState<UserType>(getProfile());
    useEffect(() => {
        setUser(getProfile(5));
    }, []);
    function getProfile(id?: number | string) {
        let posts: PostType[] = [];
        for (let i = 0; i < 12; i++) {
            posts.push({
                id: i,
                image: "https://picsum.photos/100?random=" + i,
                stamps: [],
            });
        }
        let user: UserType = {
            id: id ? id : "none",
            profile: {
                name: id ? "person's name" : "no one",
                description: id ? "profile info" : "",
                image: id
                    ? "/files/Example.PNG"
                    : "https://picsum.photos/id/870/100/100?grayscale&blur=2",
            },
            posts,
        };
        console.log(user);

        return user;
    }
    return (
        <div className="">
            <div>
                <div className="flex flex-wrap">
                    <img
                        src={user.profile.image}
                        alt="thumbnail"
                        className="w-11"
                    />
                    <span>{user.profile.name}</span>
                </div>
                <div>
                    <p>{user.profile.description}</p>
                </div>
            </div>
            <div className="flex flex-wrap">
                {(user as UserType).posts.map((post, index) => (
                    <a key={index} href={"/post/" + post.id}>
                        <div className="h-36 w-36">
                            <img
                                src={post.image}
                                alt="thumbnail"
                                className=""
                            />
                        </div>
                    </a>
                    // <p>{url}</p>
                ))}
            </div>
        </div>
    );
}

export default UserProfile;
