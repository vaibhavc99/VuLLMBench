#!/usr/bin/env bash
#
# create a SQLite3 database file of the CVEfixes.

# ------------------------------------------------------------------------------
DATA_PATH=Data/
gzcat $DATA_PATH/CVEfixes_v1.0.7.sql.gz | sqlite3 $DATA_PATH/CVEfixes.db

#------------------------------------------------------------------------------