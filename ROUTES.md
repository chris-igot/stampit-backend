# Route Documentation

## Profile routes

### GET:

`/home`  
`/home/edit`  
`/profile`

returns: "stampyReact.jsp"
<br><br>

### GET: `/api/home`

returns:

```
{
    "name": (string),
    "title": (string),
    "bio": (string),
    "posts": [
        {
            "id": (string),
            "image": (string),
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

---

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
