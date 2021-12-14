export interface PostType {
    pic_url: string;
    stamps: StampType[];
}

export interface StampType {
    stamp_url: string;
    x: number;
    y: number;
}

export interface ProfileType {
    user_info: UserInfoType;
    posts: PostType;
}

export interface UserInfoType {
    id: number | string;
    name: string;
    pic_url: string;
}
