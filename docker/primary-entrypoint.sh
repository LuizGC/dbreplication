#!/bin/bash
set -e

# Add replication entry to pg_hba.conf
echo "host replication replicator 0.0.0.0/0 md5" >> "$PGDATA/pg_hba.conf"

# Reload PostgreSQL configuration
pg_ctl -D "$PGDATA" -w restart
