CREATE TABLE client (
	id SERIAL,
	name VARCHAR,
    value NUMERIC(10,2)
);

INSERT INTO client (id, name, value)
SELECT generate_series(1, 1000000),
       md5(random()::text),
       ROUND((random() * 10)::numeric, 2);

CREATE INDEX idx_client_value ON client(value);
