# URL Mapping

## URL di base
`https://swa.net/webmarket/rest`

## Operazioni

### 1. Login

`POST [BASE]/users/login`

### Chiedere al prof materiale

-------------------------------------------------

### 2. Logout

`POST [BASE]/users/logout`

### Chiedere al prof materiale

### 3. Inserimento richiesta d'acquisto

`POST [BASE]/requests`

#### Input

`{request}`

#### Output

(201 - CREATED): `[BASE]/requests/{id}`

-------------------------------------------------

### 4. Associazione richiesta &rarr tecnico

`PATCH [BASE]/requests/{id}/technicianEmail`

#### Input

`{technicianEmail}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 5. Inserimento proposta d'acquisto

`POST [BASE]/proposals`

#### Input

`{proposal}`

#### Output

(201 - CREATED): `[BASE]/proposals/{id}`

--------------------------------------------------

### 6. Aggiornamento proposta d'acquisto

`PATCH [BASE]/proposals/{id}`

#### Input

`{proposal}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 7. Approvazione proposta d'acquisto

`PATCH [BASE]/proposals/{id}/response`

#### Input

`{response}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 8. Eliminazione richiesta d'acquisto

`DELETE [BASE]/requests/{id}`

#### Output

(204 - NO CONTENT)

--------------------------------------------------

### 9. Estrazione lista richieste in corso di un certo ordinante

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

### 10. Estrazione lista richieste non assegnate

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

### 11. Estrazione dettagli proposta

`GET [BASE]/proposals/{id}`

#### Output

(200): `{proposal}`

--------------------------------------------------

### 12. Estrazione richieste gestite da un certo tecnico

`GET [BASE]/requests/{email}`

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


















