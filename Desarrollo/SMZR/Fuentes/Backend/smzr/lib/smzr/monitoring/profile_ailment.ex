defmodule Smzr.Monitoring.ProfileAilment do
  use Ecto.Schema
  import Ecto.Changeset

  schema "profile_ailments" do
    field :profile_id, :id
    field :ailment_levels_id, :id

    timestamps()
  end

  @doc false
  def changeset(profile_ailment, attrs) do
    profile_ailment
    |> cast(attrs, [:profile_id, :ailment_levels_id])
    |> validate_required([:profile_id, :ailment_levels_id])
  end
end
