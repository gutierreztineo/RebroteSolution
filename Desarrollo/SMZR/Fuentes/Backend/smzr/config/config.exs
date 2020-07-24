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
  secret_key_base: "IPCAzNZ6qb3PadGCZd/5PQa235hwBLKaTkmEBe3fKHnP+MQDlQdCvLazycb3EALm",
  render_errors: [view: SmzrWeb.ErrorView, accepts: ~w(html json), layout: false],
  pubsub_server: Smzr.PubSub,
  live_view: [signing_salt: "4Q6IChAm"]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
