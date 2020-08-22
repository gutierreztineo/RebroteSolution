defmodule Smzr.Tracking.UserLocation do
  use Ecto.Schema
  import Ecto.Changeset

  schema "user_locations" do
    field :user_id, :id
    field :location_id, :id
    field :tag, :string
    timestamps()
  end

  @doc false
  def changeset(user_location, attrs) do
    user_location
    |> cast(attrs, [:user_id, :location_id, :tag])
    |> validate_required([])
  end
end
