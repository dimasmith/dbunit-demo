CREATE TABLE periodic_limits (
  id VARCHAR(255) PRIMARY KEY NOT NULL,
  limit_id VARCHAR(255) REFERENCES limits(id),
  amount VARCHAR(255),
  period VARCHAR(255)
)