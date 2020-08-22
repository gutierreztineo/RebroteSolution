defmodule Smzr.Monitoring.Ailment do
  use Ecto.Schema
  import Ecto.Changeset

  schema "ailments" do
    field :description, :string

    timestamps()
  end

  @doc false
  def changeset(ailment, attrs) do
    ailment
    |> cast(attrs, [:description])
    |> validate_required([:description])
  end
end
