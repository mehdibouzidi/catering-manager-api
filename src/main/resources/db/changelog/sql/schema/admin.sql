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
  CREATE SCHEMA IF NOT EXISTS admin;
  ALTER SCHEMA admin
  OWNER TO cateringadmin;
  GRANT ALL PRIVILEGES ON SCHEMA admin TO cateringadmin;
  GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA admin TO cateringadmin;
  GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA admin TO cateringadmin;
END
$$;