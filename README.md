# pitaya-finch
REST API service for some Natural Language Processing **NLP** tasks. This service uses internally **Apache Open NLP** and provides an HTTP bridge (REST API) to use **NLP** stuff.

Pitaya service runs over **Finch** which allows you to create composable HTTP services definitions in a functional programming way making them elegant.

Finch endpoints are deployed on Finagle which already provides a super performance. In short: **We want to build tiny, elegant and efficient NLP services.**

# Contents
1) [How to run](#how-to-run)
2) [How to test](#how-to-test)
3) [API definition](#api-definition)


# How to run
This project uses Twitter-Server to wrap the entire service and provide useful tools for monitoring. When runing the project you can _admin_ your service through: `http://<domain>:9990`
```shell
$ sbt compile
$ sbt run
```

## Stats console
![Requests metric](docs/img/stats.png)


# How to test
To run all the test suite
```shell
$ sbt test
```

To run a specific test case
```shell
$ sbt testOnly *<class-spec-name>
```


# API definition
* **General**
  1. [Current version](#current-version)
  1. [Schema](#schema)
  1. [Root endpoint](#root-endpoint)
* **NLP**
  1. [Token processing](#token-processing)
      1. [Tokenizer](#tokenizer)
          1. [Get token algorithms](#get-token-algorithms)
          1. [Tokenize text](#tokenize-text)
      1. [Normalizer](#normalizer)
          1. [Lowercase converter](#lowercase-converter)
          1. [Stopwords remover](#stopwords-remover)
          1. [Stemmer](#stemmer)
          1. [Lemmatizer](#lemmatizer)
      1. [Pipeline: Token processing pipeline](#pipeline-token-processing-pipeline)
  
  
## General
### Current version
The current version of this API is **v1**.
 
### Schema
All data is sent and received as **JSON**.
 
### Root endpoint
The root endpoint is: `http://<host>:<port>/pitaya-finch/api/v1/`
 
 
## NLP
### Token processing
#### Tokenizer
##### Get token algorithms
Returns a list of supported tokenizer algorithms.

```
GET /nlp/tokenizer
```

###### Parameters
N/A

###### Response
```javascript
Status: 200 OK
```
```javascript
[
    "SIMPLE",
    "WHITESPACE",
    "MAX_ENTROPY"
]
```

##### Tokenize text
Returns a list of tokens of the provided text using the specified algorithm.
 
```
POST /nlp/tokenizer
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `text` | `string` | The text to be split in tokens. |
| `algorithm` | `enum` | The tokenizer algorithm to be used. **Values:** `SIMPLE`, `WHITESPACE`, `MAX_ENTROPY`|
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{
    "text": "Thi's is a sample.",
    "algorithm": "WHITESPACE",
    "tokens": [
        "Thi's",
        "is",
        "a",
        "sample."
    ]
}
```
 
 
#### Normalizer
##### Lowercase converter
Returns a list of tokens in lower case format.
 
```
POST /nlp/normalizer/lowercase
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `tokens` | `array[String]` | The list of tokens to be lower cased. |
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{
    "tokens": [
        "ThIs",
        "requires",
        "TO",
        "BE",
        "lOwErCasED"
    ],
    "result": [
        "this",
        "requires",
        "to",
        "be",
        "lowercased"
    ]
}
```

 
##### Stopwords remover
Returns a list of tokens without stopwords. Stopwords works for English.
 
```
POST /nlp/normalizer/stopwords
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `tokens` | `array[String]` | The list of tokens to remove stopwords. |
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{
    "tokens": [
        "this",
        "requires",
        "to",
        "be",
        "lowercased"
    ],
    "result": [
        "requires",
        "lowercased"
    ]
}
```

 
##### Stemmer
Returns a list of pairs with the word and their respective Stem. Stemmer works for English.
 
```
POST /nlp/normalizer/stem
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `tokens` | `array[String]` | The list of tokens to get their stem. |
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{
    "tokens": [
        "banking",
        "bank",
        "banked",
        "became",
        "become"
    ],
    "result": [
        {"orginal": "banking", "stem": "bank"},
        {"orginal": "bank", "stem": "bank"},
        {"orginal": "banked", "stem": "bank"},
        {"orginal": "became", "stem": "becom"},
        {"orginal": "become", "stem": "becom"}
    ]
}
```

 
##### Lemmatizer
Returns a list of pairs with the word and their respective Lemmas. Lemmatizer works for English.
 
```
POST /nlp/normalizer/lemma
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `tokens` | `array[String]` | The list of tokens to get their lemmas. |
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{
    "tokens": [
        "bob",
        "hello"
    ],
    "result": [
        {
            "original": "bob",
            "lemmas": [
                {"tag": "NNS", "description": "Noun, plural"},
                {"tag": "NN", "description": "Noun, singular or mass"},
                {"tag": "VB", "description": "Verb, base form"},
                {"tag": "VBP", "description": "Verb, non-3rd person singular present"}
            ]
        },
        {
            "original": "hello",
            "lemmas": [
                {"tag": "NN", "description": "Noun, singular or mass"}
            ]
        }
    ]
}
```

 
#### Pipeline: Token processing pipeline
Process the provided text using the specified algorithms in the sequence defined by the Pipeline object.

A Pipeline object has three main components or steps:

* **init:** Is the first stage of the Pipeline. The *type* of this stage is `String => List[String]`. We only support `Tokenizer` algorithm for **init** stage.
* **stages:** Is a list of *stage* (algorithms) which will be applied to the result of the previous stage. The *type* of this stage is `List[String] => List[String]`.
* **result:** Is the last stage of the Pipeline. The *type* of this stage is `List[String] => Result`.

| Stage     | Algorithms | Description |
| --------- | ---------- | ----------- |
| `init` | `Tokenizer` | The text to be split in tokens. |
| `stage` | `Lowercase converter`, `Stop words remover` | Tokens to be processed and the result is a list of tokens. |
| `result` | `Stemmer`, `Lemmatizer` | The list of tokens to be processed by a final stage which reproduces a *Result*. |
 
```
POST /nlp/pipeline
```
 
###### Parameters (Body)

| Name | Type | Description |
| --------- | -------- | ---- |
| `text` | `string` | The text to be split in tokens. |
| `pipeline` | `object` | The pipeline definition. |
| `pipeline.init` | `object` | The first stage of the pipeline processing. |
| `pipeline.stages` | `array[object]` | A list of stage objects. |
| `pipeline.result` | `object` | The result of the pipeline processing. |

###### Example
```javascript
{
  "text": "tokenizeame este otro texto.",
  "pipeline": {
    "init": {"algorithm": "tokenizer"},
    "stages": [
      {"algorithm": "lowercase"},
      {"algorithm": "stopper"}
    ],
    "result": {"algorithm": "stem"}
  }
}
```
 
###### Response
```javascript
Status: 200 OK
```
```javascript
{}
```
