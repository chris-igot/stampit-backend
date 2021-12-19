export interface PostType {
    id: number | string;
    image: string;
    stamps: StampType[];
}

export interface StampType {
    id: number | string;
    stamp: string;
    x: number;
    y: number;
}

export interface UserType {
    id: number | string;
    profile: ProfileType;
    posts: PostType[];
}

export interface ProfileType {
    name: string;
    description: string;
    image: string;
}
