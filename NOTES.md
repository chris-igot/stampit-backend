## API routes

| Route                        | GET                                | GET      | POST requirements             |
| ---------------------------- | ---------------------------------- | -------- | ----------------------------- |
| /                            | redirects depending on user cookie | redirect | --                            |
| /login                       | JSP: user login                    | JSP      | email, pw                     |
| /registration                | JSP: user registration             | JSP      | username, email, pw,pwConfirm |
| /public                      | React: public wall                 | jSON     | --                            |
| /profile/{profileID}         | React: user profile                | jSON     | --                            |
| /profile/{profileID}/edit    | React: edit user profile           | jSON     | profileInfo                   |
| /profile/{profileID}/follows | React: profiles followed by user   | jSON     | removedProfileIDsList         |
| /profile/search              | React: search user profiles        | jSON     | searchString                  |
| /post/{postID}               | React: image post                  | jSON     | --                            |
| /post/new                    | React: add post                    | jSON     | imageFile,description         |
| /post/{postID}/stamp/new     | React: add a stamp to post         | jSON     | stampID,x,y                   |
| (tbd)                        | image path                         | --       | --                            |
