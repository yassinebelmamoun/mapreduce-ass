# Term Frequency-Inverse Document Frequency (TF-IDF)

TFIDF, short for term frequency–inverse document frequency, is a numerical statistic that is intended to reflect how important a word is to a document in a collection or corpus.

## The problem

Based on 2 documents as Input:

- callwild.txt
- defoe-robinson-103.txt


```
                     THE CALL OF THE WILD
                         by Jack London
                        CHAPTER ONE.
                     Into the Primitive.

                 Old longings nomadic leap,
                   Chafing at custom's chain;
                 Again from its brumal sleep
                   Wakens the ferine strain.

  BUCK DID NOT READ THE newspapers, or he would have known that
trouble was brewing not alone for himself, but for every tide-water
dog, strong of muscle and with warm, long hair, from Puget Sound to
San Diego. Because men, groping in the Arctic darkness, had found a

(...)
```

We want to compute TF-IDF scores for each word.


## The solution

The problem is divided into 3 rounds.

### First Round:

The first round is a word count that displays the occurence of each word in each document.

Here is a sample of the ouput:

```
depend--defoe-robinson-103.txt	7
depended--defoe-robinson-103.txt	4
depending--callwild.txt	1
depending--defoe-robinson-103.txt	2
```

### Second Round:

The second round evalutes the total number of words in each document.

Here is a sample of the ouput:

```
daughters--callwild.txt	1;20605
crippled--callwild.txt	2;20605
brave--defoe-robinson-103.txt	1;69378
peopled--defoe-robinson-103.txt	1;69378
```

### Third Round:

The third round evalutes the TF-IDF score of each word with the following formula:

```
(occurence / count_words_doc) * log(count_doc / count_doc_with_word)
```

Here is a sample of the ouput:

```
affectionate--defoe-robinson-103.txt	8.677966953904154E-6
affectionately--defoe-robinson-103.txt	8.677966953904154E-6
affections--defoe-robinson-103.txt	2.603390086171246E-5
affects--defoe-robinson-103.txt	4.338983476952077E-6
```

## The result:

We were looking for the 20 words that have the highest tf-idf scores in these documents:

```
buck--callwild.txt	0.53%
dogs--callwild.txt	0.17%
thornton--callwild.txt	0.15%
myself--defoe-robinson-103.txt	0.13%
spitz--callwild.txt	0.09%
sled--callwild.txt	0.09%
francois--callwild.txt	0.09%
friday--defoe-robinson-103.txt	0.08%
trail--callwild.txt	0.06%
john--callwild.txt	0.06%
perrault--callwild.txt	0.06%
hal--callwild.txt	0.05%
team--callwild.txt	0.05%
thoughts--defoe-robinson-103.txt	0.05%
sol--callwild.txt	0.04%
ice--callwild.txt	0.04%
leks--callwild.txt	0.04%
traces--callwild.txt	0.04%
around--callwild.txt	0.04%
```
_Note that we consider the word containing 3 or more characters_
