## API routes

| Route                          | GET                                | GET return from server                                                                        | POST requirements             |
| ------------------------------ | ---------------------------------- | --------------------------------------------------------------------------------------------- | ----------------------------- |
| /                              | redirects depending on user cookie | redirect                                                                                      | --                            |
| /login                         | JSP: user login                    | JSP                                                                                           | email, pw                     |
| /registration                  | JSP: user registration             | JSP                                                                                           | username, email, pw,pwConfirm |
| /public                        | React: public wall                 | jSON {posts:[(see post below)]}                                                               | --                            |
| /profile/{profileID}           | React: user profile                | jSON {user_info:{id:(int or string),name:(string), image:(string)}, posts:[(see post below)]} | --                            |
| /profile/{profileID}/edit      | React: edit user profile           | React only                                                                                    | profileInfo                   |
| /profile/{profileID}/follows   | React: profiles followed by user   | jSON {users:[(see user_info above)]}                                                          | removedProfileIDsList         |
| /profile/search                | React: search user profiles        | jSON {users:[(see user_info above)]}                                                          | searchString                  |
| /post/{postID}                 | React: image post                  | jSON {id:(int or string),image:(string), stamps:[(see stamp below)]}                          | --                            |
| /post/new                      | React: add post                    | React only                                                                                    | imageFile,description         |
| /post/{postID}/stamp/{stampID} | --                                 | jSON {id:(int or string),stamp:(string),x:(int),y(int)}                                       | --                            |
| /post/{postID}/stamp/new       | React: add a stamp to post         | jSON                                                                                          | stampID,x,y                   |
| (tbd)                          | image path                         | --                                                                                            | --                            |
