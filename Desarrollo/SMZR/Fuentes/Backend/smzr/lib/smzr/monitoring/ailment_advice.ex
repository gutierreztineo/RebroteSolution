defmodule Smzr.Monitoring.AilmentAdvice do
  use Ecto.Schema
  import Ecto.Changeset

  schema "ailment_advices" do
    field :ailment_id, :id
    field :advice_id, :id

    timestamps()
  end

  @doc false
  def changeset(ailment_advice, attrs) do
    ailment_advice
    |> cast(attrs, [])
    |> validate_required([])
  end
end
