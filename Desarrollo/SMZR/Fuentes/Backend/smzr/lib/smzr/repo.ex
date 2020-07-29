defmodule Smzr.Repo do
  use Ecto.Repo,
    otp_app: :smzr,
    adapter: Ecto.Adapters.Postgres
end
