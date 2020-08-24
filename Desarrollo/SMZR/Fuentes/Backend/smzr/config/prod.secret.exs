# In this file, we load production configuration and secrets
# from environment variables. You can also hardcode secrets,
# although such is generally not recommended and you have to
# remember to add this file to your .gitignore.
use Mix.Config

database_url =
  System.get_env("DATABASE_URL") ||
    raise """
    environment variable DATABASE_URL is missing.
    For example: ecto://USER:PASS@HOST/DATABASE
    """

config :smzr, Smzr.Repo,
  # ssl: true,
  url: database_url,
  pool_size: String.to_integer(System.get_env("POOL_SIZE") || "10")

secret_key_base =
  System.get_env("SECRET_KEY_BASE") ||
    raise """
    environment variable SECRET_KEY_BASE is missing.
    You can generate one by calling: mix phx.gen.secret
    """

#config :smzr, SmzrWeb.Endpoint,
config :smzr, SmzrWeb.Endpoint,
  server: true,
  check_origin: true,
  http: [
    port: 80,
    transport_options: [socket_opts: [:inet6]]
  ],
  https: [
    port: 443,
    cipher_suite: :strong,
    keyfile: "/etc/letsencrypt/live/smzr.makinap.com/privkey.pem",#System.get_env("SOME_APP_SSL_KEY_PATH"),
    certfile: "/etc/letsencrypt/live/smzr.makinap.com/cert.pem",#System.get_env("SOME_APP_SSL_CERT_PATH"),
    cacertfile: "/etc/letsencrypt/live/smzr.makinap.com/chain.pem",
    transport_options: [socket_opts: [:inet6]]
  ],
  secret_key_base: secret_key_base

# ## Using releases (Elixir v1.9+)
#
# If you are doing OTP releases, you need to instruct Phoenix
# to start each relevant endpoint:
#
#     config :smzr, SmzrWeb.Endpoint, server: true
#
# Then you can assemble a release by calling `mix release`.
# See `mix help release` for more information.
