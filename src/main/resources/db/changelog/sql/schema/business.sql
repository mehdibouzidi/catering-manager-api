DO
$$
BEGIN
  IF NOT EXISTS(
      SELECT
      FROM pg_user
      WHERE usename = 'cateringadmin')
  THEN
    CREATE USER cateringadmin
    WITH PASSWORD 'El-Afia-2024';
  END IF;
  CREATE SCHEMA IF NOT EXISTS business;
  ALTER SCHEMA business
  OWNER TO cateringadmin;
  GRANT ALL PRIVILEGES ON SCHEMA business TO cateringadmin;
  GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA business TO cateringadmin;
  GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA business TO cateringadmin;
END
$$;