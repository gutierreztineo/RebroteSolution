defmodule Smzr.Accounts.Profile do
  use Ecto.Schema
  import Ecto.Changeset

  schema "profiles" do
    field :birthdate, :date
    field :dni, :string
    field :email, :string
    field :firstname, :string
    field :lastnamem, :string
    field :lastnamep, :string
    field :gender, :string
    field :user_id, :id

    timestamps()
  end

  @doc false
  def changeset(profile, attrs) do
    profile
    |> cast(attrs, [:firstname, :lastnamep, :lastnamem, :dni, :birthdate, :email, :gender, :user_id])
    |> validate_required([:firstname, :lastnamep, :lastnamem, :dni, :birthdate, :email, :gender, :user_id])
  end
end
