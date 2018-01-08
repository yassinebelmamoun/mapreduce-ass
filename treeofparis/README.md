# The Trees of Paris


The objective of this problem is to compute some information on the trees of Paris using the file arbres.csv with MapReduce programs.

Here is a sample of the input:

```
(48.857140829, 2.29533455314)	7	Maclura	pomifera	Moraceae	1935	13		Quai Branly, avenue de La Motte-Piquet, avenue de la Bourdonnais, avenue de Suffren	Oranger des Osages		6	Parc du Champs de Mars
(48.8685686134, 2.31331809304)	8	Calocedrus	decurrens	Cupressaceae	1854	20	195	Cours-la-Reine, avenue Franklin-D.-Roosevelt, avenue Matignon, avenue Gabriel	CÃ¨dre Ã  encens		11	Jardin des Champs ElysÃ©es
(48.8768191638, 2.33210374339)	9	Pterocarya	fraxinifolia	Juglandaceae	1862	22	330	Place d'Estienne-d'Orves	PÃ©rocarya du Caucase		14	Square Etienne d'Orves
(48.8373323894, 2.40776275516)	12	Celtis	australis	Cannabaceae	1906	16	295	27, boulevard Soult	Micocoulier de Provence		16	Avenue 27 boulevard Soult
```

The headers are:

- GEOPOINT
- ARRONDISSEMENT
- GENRE
- ESPECE
- FAMILLE
- ANNEE
- HAUTEURCIRCONFERENCE
- ADRESSE
- NOM
- VARIETE
- OBJECTID
- NOM_E



## Number of trees by type:

```
araucana	1
atlantica	2
australis	1
baccata	2
bignonioides	1
biloba	5
bungeana	1
cappadocicum	1
carpinifolia	4
colurna	3
coulteri	1
decurrens	1
dioicus	1
distichum	3
excelsior	1
fraxinifolia	2
giganteum	5
giraldii	1
glutinosa	1
grandiflora	1
hippocastanum	3
ilex	1
involucrata	1
japonicum	1
kaki	2
libanii	2
monspessulanum	1
nigra	3
nigra laricio	1
opalus	1
orientalis	8
papyrifera	1
petraea	2
pomifera	1
pseudoacacia	1
sempervirens	1
serrata	1
stenoptera	1
suber	1
sylvatica	8
tomentosa	2
tulipifera	2
ulmoides	1
virginiana	2
x acerifolia	11
```


## Height of the highest tree of each type:

```
araucana	9.0
atlantica	25.0
australis	16.0
baccata	13.0
bignonioides	15.0
biloba	33.0
bungeana	10.0
cappadocicum	16.0
carpinifolia	30.0
colurna	20.0
coulteri	14.0
decurrens	20.0
dioicus	10.0
distichum	35.0
excelsior	30.0
fraxinifolia	27.0
giganteum	35.0
giraldii	35.0
glutinosa	16.0
grandiflora	12.0
hippocastanum	30.0
ilex	15.0
involucrata	12.0
japonicum	10.0
kaki	14.0
libanii	30.0
monspessulanum	12.0
nigra	30.0
nigra laricio	30.0
opalus	15.0
orientalis	34.0
papyrifera	12.0
petraea	31.0
pomifera	13.0
pseudoacacia	11.0
sempervirens	30.0
serrata	18.0
stenoptera	30.0
suber	10.0
sylvatica	30.0
tomentosa	20.0
tulipifera	35.0
ulmoides	12.0
virginiana	14.0
x acerifolia	45.0
```


## Borough of the oldest tree in Paris:

_TO DO_
