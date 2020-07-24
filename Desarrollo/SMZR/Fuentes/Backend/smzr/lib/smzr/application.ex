defmodule Smzr.Application do
  # See https://hexdocs.pm/elixir/Application.html
  # for more information on OTP Applications
  @moduledoc false

  use Application

  def start(_type, _args) do
    children = [
      # Start the Ecto repository
      Smzr.Repo,
      # Start the Telemetry supervisor
      SmzrWeb.Telemetry,
      # Start the PubSub system
      {Phoenix.PubSub, name: Smzr.PubSub},
      # Start the Endpoint (http/https)
      SmzrWeb.Endpoint
      # Start a worker by calling: Smzr.Worker.start_link(arg)
      # {Smzr.Worker, arg}
    ]

    # See https://hexdocs.pm/elixir/Supervisor.html
    # for other strategies and supported options
    opts = [strategy: :one_for_one, name: Smzr.Supervisor]
    Supervisor.start_link(children, opts)
  end

  # Tell Phoenix to update the endpoint configuration
  # whenever the application is updated.
  def config_change(changed, _new, removed) do
    SmzrWeb.Endpoint.config_change(changed, removed)
    :ok
  end
end
