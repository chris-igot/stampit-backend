export interface PostType {
    id: number | string;
    pic_url: string;
    stamps: StampType[];
}

export interface StampType {
    id: number | string;
    stamp_url: string;
    x: number;
    y: number;
}

export interface ProfileType {
    user_info: UserInfoType;
    posts: PostType[];
}

export interface UserInfoType {
    id: number | string;
    name: string;
    text: string;
    pic_url: string;
}
