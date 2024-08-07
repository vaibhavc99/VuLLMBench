# Instructions to use CVEfixes


## Converting the CVEfixes dataset from the compressed SQL dump

We provide a compressed SQL dump for the dataset, which can be converted into
an SQLite3 database using the following command: 


```console
$ gzcat Data/CVEfixes_v1.0.7.sql.gz | sqlite3 Data/CVEfixes.db
```

## Exploring the vulnerability data

The overall structure of the database is shown in the [ER diagram](Doc/ER_diagram.png).
You can use any SQLite tool to view and query the database, such as
[DB Browser for SQLite](https://sqlitebrowser.org/), an open-source visual explorer
for SQLite databases that is available for Mac, Windows, and Linux.

We provide a [Jupyter Notebook](Examples/query_CVEfixes.ipynb) with
example queries to extract the data at different abstraction levels,
code to generate statistics, as well as the code to replicate all tables
and plots presented in the paper based on the _CVEfixes_ data.

Some example queries to extract the part of _CVEfixes_ database are as
follows:

- a query to extract all the method_level vulnerability data of C
  programming language.

```console
SQL_QUERY = "SELECT m.method_change_id, m.name, m.code, m.before_change, f.programming_language
from file_change f, method_change m
WHERE m.file_change_id=f.file_change_id
AND f.programming_language='C';"
```

- a query to extract all the code patches of Java programming language
  that have added/removed only a single statement to fix
  vulnerabilities.

```console
SQL_QUERY = "SELECT cv.cve_id, f.filename, f.num_lines_added, f.num_lines_deleted, f.code_before, f.code_after, cc.cwe_id
FROM file_change f, commits c, fixes fx, cve cv, cwe_classification cc
WHERE f.hash = c.hash
AND c.hash = fx.hash
AND fx.cve_id = cv.cve_id
AND cv.cve_id = cc.cve_id
AND f.num_lines_added<=1
AND f.num_lines_deleted<=1
AND f.programming_language='Java';;"
```

## Collection Log

The file [CVEfixes_v1.0.7.log.gz](Data/CVEfixes_v1.0.7.log.gz) contains a 
detailed collection log, including information on a small number of 
repositories that are referenced in the CVEs but were no longer accessible 
at the date of collecting this dataset.


## Gathering the dataset from scratch

We refer to the companion GitHub repository containing the code to
automatically collect the data at
<https://github.com/secureIT-project/CVEfixes>, released with DOI:
[10.5281/zenodo.5111494](https://doi.org/10.5281/zenodo.5111494).


