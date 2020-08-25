defmodule Smzr.Repo.Migrations.CreateAilments do
  use Ecto.Migration

  def change do
    create table(:ailments) do
      add :description, :string

      timestamps()
    end

  end
end
