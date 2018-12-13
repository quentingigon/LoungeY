

#LOUNGE Y

##Architecture REST - v 0.2

---

##User gestion

| Endpoint  | GET/POST values        | RETURN values | Description                                                  |
| --------- | ---------------------- | ------------- | ------------------------------------------------------------ |
| /login    | *LOG P*                | *LOG R*       | Allow login for user, either with email or with pseudo       |
| /register | *REG P*                | OK/NOK        | Allow register                                               |
| /wall     | {idUser}               | *WAL R*       | Show information for the wall of a user                      |
| /post     | {idPost}               | { **post** }  | return one post (question, commentary, ..) corresponding to id |
| /settings | {idCurrentUser, token} | *PRO R*       | allow the user to peruse his profile and change values/settings. |
| /logout   | {idCurrentUser, token} | OK/NOK        | Allow logout                                                 |



### LOG P

```json
{
    pseudo : string,
    email : string, 
    password : string [hash],
    ... 
}
```



### LOG R

```
{
  	!TBD!
	token, 
	...
}
```

### REG P

```
{
    pseudo,  
	email, 
    password, 
	yearOfStudy,
	orientation, 
	picture (in other POST request ?)
	
}
```

### WAL R

```
{
    pseudo,    
	yearOfStudy,
	orientation, 
	pictures, 
	...
	
}
```

### WAL R

```
{
    pseudo,    
	yearOfStudy,
	orientation, 
	pictures, 
	...
	
}
```



---

## core

| Endpoint                        | POST values                    | RETURN values  | Description                              |
| ------------------------------- | ------------------------------ | -------------- | ---------------------------------------- |
| /lounge                         | {idCurrentUser eventually}     | LOU R          | feed of every public question            |
| /llounge/questions              | / (GET)                        | { posts : [] } | show all public questions                |
| /lounge/friends/{idcurrentUser} | / GET                          | { posts : [] } | show all friends posts only              |
| /posts                          | {idCurrentUser, post : {} }    | OK/NOK         | allow user to add a post                 |
| /posts/{idPost}/comment         | {idCurrentUser, post : {} }    | OK/NOK         | allow user to add a comment              |
| /search                         | {idCurrentUser, searchString } | SEA R          | allow user to search all the public post |

### LOU R

```
{
   posts : []
}
```

### SEA R

```
{
   posts : []
}
```

---

##friends

| Endpoint                                   | POST values | RETURN values    | Description     |
| ------------------------------------------ | ----------- | ---------------- | --------------- |
| /friends/{idCurrentUser}                   | /           | { friends : [] } | get all friends |
| /friends/{idCurrentUser}/new/{idNewFriend} |             |                  |                 |
|                                            |             |                  |                 |

