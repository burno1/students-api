# students-api
Created a API to do CRUD operations around student object...

# disclaimer
The spring will generate the tables for you! 
but
To make the "matricula" unique constraint works, you'll need to put the following code on your mysql CLi/workbench

```alter table student add constraint uniqueMatricula unique(matricula);```

- The API methods supported are
- POST
- PUT
- DELETE
- GET

## GET ALL
In order to get all objects persisted, all you need to do is perfom a GET on the specific endpoint

### endpoint: /students


## POST:
@param object - the object to be persisted

The post method accepts the object in form of a JSON, you can use this on postman.
the method accepts the object bellow, **nome*, **sobrenome**,**matricula** are the required fields with minimun size of 3 characters

**telefones** is optional, but if you want to insert it you must insert in the following patterns
* (99)9999-9999
* 99 99999999

if not correctly inserted, you'll receive a bad request.

### endpoint: /students
  
JSON:
  ``` 
  {
    "nome":"john",
    "sobrenome": "doe",
    "matricula":"mat123",
    "telefones":[
        {
            "telefone":"(41) 1123-4444"
        }
    ]
  } 
  ```
  
## PUT
@param Id - the id of the object

The put method makes a update on a pre existing record, you must offer the id for the correct behavior, must have at least 1 field to update.



### endpoint: /students/{id}

JSON:
  ``` 
  {
    "nome":"joana",
    "sobrenome":"doe",
    "telefones":[
        {
            "telefone":"(41)11133-4444"
        }
    ]
  } 
  ```

## DELETE
This method deletes the object and phones from database

### endpoint: /students/{id}

## GET 
@param Id - the id of the object

This method gets the specific object from database

### endpoint: /students/{id}



