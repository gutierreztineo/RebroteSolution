defmodule Smzr.Repo.Migrations.CreateUserLocations do
  use Ecto.Migration

  def change do
    create table(:user_locations) do
      add :user_id, references(:users, on_delete: :nothing)
      add :location_id, references(:locations, on_delete: :nothing)

      timestamps()
    end

    create index(:user_locations, [:user_id])
    create index(:user_locations, [:location_id])
  end
end
