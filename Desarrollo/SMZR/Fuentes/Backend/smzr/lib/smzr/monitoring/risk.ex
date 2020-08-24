defmodule Smzr.Monitoring.Risk do
  use Ecto.Schema
  import Ecto.Changeset

  schema "risks" do
    field :descripcion, :string
    field :name, :string

    timestamps()
  end

  @doc false
  def changeset(risk, attrs) do
    risk
    |> cast(attrs, [:descripcion, :name])
    |> validate_required([:descripcion, :name])
  end
end
