defmodule Smzr.Monitoring.Advice do
  use Ecto.Schema
  import Ecto.Changeset

  schema "advices" do
    field :description, :string

    timestamps()
  end

  @doc false
  def changeset(advice, attrs) do
    advice
    |> cast(attrs, [:description])
    |> validate_required([:description])
  end
end
