defmodule Smzr.Monitoring.ProfileRisk do
  use Ecto.Schema
  import Ecto.Changeset

  schema "profile_risks" do
    field :risk_id, :id
    field :profile_id, :id

    timestamps()
  end

  @doc false
  def changeset(profile_risk, attrs) do
    profile_risk
    |> cast(attrs, [])
    |> validate_required([])
  end
end
