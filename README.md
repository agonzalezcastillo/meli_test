# Meli technical Lead
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)
### Installation

- You need to have Docker, Docker-compose installed on your PC
- clone the project from https://github.com/agonzalezcastillo/meli_test
- move to the folder where the project was cloned 
```sh
$ cd /meli_test
```
-start the containers with the following command
```sh
$ docker-compose up
```
-if everything went ok, you should be able to make a get call to the stats endpoint
```sh
[GET] localhost:5000/stats
```
now you can start validating the human/mutants DNAs
```sh
 [POST]localhost:5000/mutant
 { 
	"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
 }
```
this should return true,

-Also the API is hosted in a GCP compute engine, so you could instead comsume/use it from there
```sh
 [GET] 35.223.186.87:5000/stats
```
```sh
 [POST]35.223.186.87:5000/mutant
 { 
	"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
 }
```

