# URL Mapping

## URL di base

`https://swa.net/webmarket/rest`

## Operazioni

### 1. Login

`POST [BASE]/users/login`

### Chiedere al prof materiale

---

### 2. Logout

`POST [BASE]/users/logout`

### Chiedere al prof materiale

---

### 3. Inserimento richiesta d'acquisto

`POST [BASE]/requests`

#### Input

`{request}`

#### Output

(201 - CREATED): `[BASE]/requests/{id}`

---

### 4. Associazione richiesta ad un tecnico

`PUT [BASE]/requests/{id}/technician`

#### Input

`{technician}`

#### Output

(204 - NO CONTENT)

---

### 5. Inserimento e aggiornamento proposta d'acquisto

`PUT [BASE]/requests/{id}/proposal`

#### Input

`{proposal}`

#### Output

(204 - NO CONTENT)

---

### 6. Approvazione proposta d'acquisto

`PUT [BASE]/requests/{id}/proposal/answer`

#### Input

`{answer}`

#### Output

(204 - NO CONTENT)

---

### 7. Eliminazione richiesta d'acquisto

`DELETE [BASE]/requests/{id}`

#### Output

(204 - NO CONTENT)

---

### 8. Estrazione della lista delle richieste in corso di un certo ordinante

`GET [BASE]/requests/ongoing?ordererid=12312`

#### Output

(200):

```json
[
  {
    "id": "123123",
	"technician":{
		"id": "123123",
		"username": "aname",
    	"email": "anemail@gmail.com"
	},
	"url": "[BASE]/requests/{id}"
  },
  ...
]
```

---

### 9. Estrazione lista richieste non assegnate

`GET [BASE]/requests/unassigned`

#### Output

(200):

```json
  [
	{
		"id" : "123123",
		"orderer": {
			"id": "123123",
			"username": "aname",
    		"email": "anemail@gmail.com"
		},
		"url" : "[BASE]/requests/{id}"
	},
    ....
  ]
```

---

### 10. Estrazione dettagli proposta

`GET [BASE]/requests/{id}/proposal`

#### Output

(200): `{proposal}`

---

### 11. Estrazione richieste gestite da un certo tecnico

`GET [BASE]/requests?techid=123123`

#### Output

(200):

```json
  [
	{
		"id" : "123123",
		"orderer": {
			"id": "123123",
			"username": "aname",
    		"email": "anemail@gmail.com"
		},
		"ongoing": true,
		"url" : "[BASE]/requests/{id}"
	},
    ....
  ]
```
