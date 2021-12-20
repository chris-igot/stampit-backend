# Route Documentation

## Profile routes

### GET:

`/home`  
`/home/edit`  
`/profile`
`/search`
`/follows`

returns: "stampyReact.jsp"
<br><br>

### GET: `/api/home`

returns:

```
{
    "name": (string),
    "title": (string),
    "bio": (string),
    "image": (string),
    "posts": [
        {
            "id": (string),
            "image": (string),
            "stamps": [
                {
                    "image":(string),
                    "x": (integer),
                    "y": (integer)
                },
            ],
            "createdAt": (date string),
            "updatedAt": (date string)
        },
    ]
}
```

<br>

### GET: `/api/profile`

requires: _query parameters_

`id: (string)`

returns: (see return for `/api/home`)
<br><br>

### POST: `/api/home/edit`

requires: _request body_

```
{
    "title": (string),
    "bio": (string),
}
```

returns: (redirect:/home)
<br><br>

### POST: `/api/profile/search`

requires: _request body_

```
{
    "search": (string),
}
```

returns:

```
{
    "results":[
        {
            "name": (string),
            "title": (string),
            "bio": (string),
            "image": (string),
        },
    ]
}
```

<br>
### GET: `/api/follows`

returns:

```
{
    "follows":[
        {
            "name": (string),
            "title": (string),
            "bio": (string),
            "image": (string),
        },
    ]
}
```

<br>
### POST: `/api/follows`

requires: _request body_

```
{
    "profile_id": (string),
    "follow": (boolean)
}
```

returns: (redirect:/follows)
<br>

## Post routes

### GET:

`/post/new`  
`/post`  
`/public`

returns: "stampyReact.jsp"
<br><br>

### GET: `/api/post/new

requires: _request body_

```
{
    "file": (file)
}
```

returns: (redirect:/home)
<br><br>

### GET: `/api/post`

requires: _query parameters_

`id: (string)`

returns:

```
{
    "id": (string),
    "image": (string),
    "stamps": [
        {
            "image":(string),
            "x": (integer),
            "y": (integer)
        },
    ],
    "createdAt": (date string),
    "updatedAt": (date string)
}
```

<br>
### GET: `/api/public`

returns:

```
[
    {
        "id": (string),
        "image": (string),
        "createdAt": (date string),
        "updatedAt": (date string)
    },
]
```

## Profile routes

### POST: `/api/post/stamp`

requires: _request body_

```
{
    "image": (string),
    "x": (integer),
    "y": (integer),
}
```
