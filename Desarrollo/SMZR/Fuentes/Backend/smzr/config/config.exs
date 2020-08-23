# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

config :smzr,
  ecto_repos: [Smzr.Repo]

# Configures the endpoint
config :smzr, SmzrWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "1uceDbOah8qCZ1R/QbYgFpwjtPpTkV+slevUjTYkYDbbxFVKsmBf9tWnZLPFvMBf",
  render_errors: [view: SmzrWeb.ErrorView, accepts: ~w(html json), layout: false],
  pubsub_server: Smzr.PubSub,
  live_view: [signing_salt: "GJ4Zor0A"]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

config :smzr, Smzr.Guardian,
       issuer: "smzr",
       secret_key: "WhRWZyqF2W/ckZxIkYykIAqbFZlhL5JxvQhR+ODnY0S3Sok35qhp+cvNYywyzzz0"

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
