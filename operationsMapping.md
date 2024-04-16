# URL Mapping

## URL di base
`https://swa.net/webmarket/rest`

## Operazioni

### 1. Login

`GET [BASE]/users/login`

#### Input

```json
  [
	{ 
		"username" : "pincopallino", 
		"password" : "abciaebuabcoa2183b18481rbq"
	},
    ....    
  ]
```

#### Output

(200): 

```json
    { 
		"authenticated" : "true", 
		"url" : " [BASE]/users/{id}"
    }  
```

-------------------------------------------------

### 2. Inserimento richiesta d'acquisto

`POST [BASE]/requests`

#### Input

`{request}`

#### Output

(201 - CREATED): `[BASE]/requests/{id}`

-------------------------------------------------

### 3. Associazione richiesta &rarr tecnico

`PATCH [BASE]/requests/{id}/technicianEmail`

#### Input

`{technicianEmail}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 4. Inserimento proposta d'acquisto

`POST [BASE]/proposals`

#### Input

`{proposal}`

#### Output

(201 - CREATED): `[BASE]/proposals/{id}`

--------------------------------------------------

### 5. Aggiornamento proposta d'acquisto

`PATCH [BASE]/proposals/{id}`

#### Input

`{proposal}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 6. Approvazione proposta d'acquisto

`PATCH [BASE]/proposals/{id}/response`

#### Input

`{response}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 7. Eliminazione richiesta d'acquisto

`DELETE [BASE]/requests/{id}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 8. Estrazione lista richieste in corso di un certo ordinante

`GET [BASE]/requests/current?ordererID=123123`

#### Output

(200):

```json
  [
	{ 
		"id" : "123123", 
		"url" : "[BASE]/requests/{id}"
	},
    ....    
  ]
```

--------------------------------------------------

### 9. Estrazione lista richieste non assegnate

`GET [BASE]/requests/unassigned`

#### Output

(200):

```json
  [
	{ 
		"id" : "123123", 
		"url" : "[BASE]/requests/{id}"
	},
    ....    
  ]
```

--------------------------------------------------

### 10. Estrazione dettagli proposta

`GET [BASE]/proposals/{id}`

#### Output

(200): `{proposal}`

--------------------------------------------------

### 11. Estrazione richieste gestite da un certo tecnico

`GET [BASE]/requests?technicianEmail=unamailacaso@gmail.com`

#### Output

(200):

```json
  [
	{ 
		"id" : "123123", 
		"url" : "[BASE]/requests/{id}"
	},
    ....    
  ]
```


















