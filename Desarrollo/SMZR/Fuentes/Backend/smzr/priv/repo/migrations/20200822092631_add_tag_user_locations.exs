defmodule Smzr.Repo.Migrations.AddTagUserLocations do
  use Ecto.Migration

  def change do
    alter table(:user_locations) do
      add :tag, :string
    end
  end
end
