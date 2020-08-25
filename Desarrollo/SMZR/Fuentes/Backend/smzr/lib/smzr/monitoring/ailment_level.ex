defmodule Smzr.Monitoring.AilmentLevel do
  use Ecto.Schema
  import Ecto.Changeset

  schema "ailment_levels" do
    field :description, :string
    field :level, :integer
    field :ailment_id, :id

    timestamps()
  end

  @doc false
  def changeset(ailment_level, attrs) do
    ailment_level
    |> cast(attrs, [:description, :level])
    |> validate_required([:description, :level])
  end
end
