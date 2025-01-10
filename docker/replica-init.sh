#!/bin/bash
set -e

echo "Setting up replica..."

echo "Stop the PostgreSQL server and reconfigure for replication"
pg_ctl -D "$PGDATA" stop

echo "remove data"
rm -rf "$PGDATA"/*

echo "wait primary"
# Wait for the primary to be available
until pg_isready -h primary -U admin; do
  echo "Waiting for primary database..."
  sleep 2
done

echo "Set up the standby using pg_basebackup"
pg_basebackup -h primary -D "$PGDATA" -U replicator -v -P -R

echo "Start PostgreSQL server in standby mode"
pg_ctl -D "$PGDATA" -o "-c primary_conninfo='host=primary port=5432 user=replicator password=replicator_password'" -w start
