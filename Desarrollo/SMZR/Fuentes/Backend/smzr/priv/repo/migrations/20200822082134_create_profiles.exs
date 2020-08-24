defmodule Smzr.Repo.Migrations.CreateProfiles do
  use Ecto.Migration

  def change do
    create table(:profiles) do
      add :firstname, :string
      add :lastnamep, :string
      add :lastnamem, :string
      add :dni, :string
      add :birthdate, :date
      add :email, :string
      add :gender, :gender
      add :user_id, references(:users, on_delete: :nothing)

      timestamps()
    end

    create index(:profiles, [:user_id])
  end
end
