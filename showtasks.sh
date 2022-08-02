#!/usr/bin/env bash

fail () {
  echo "There were errors"
}

if ./runcrud.sh; then
  open -a Safari localhost:8080/crud/v1/tasks;
else
  fail
fi
