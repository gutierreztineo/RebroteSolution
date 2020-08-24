defmodule Smzr.Repo.Migrations.CreateRisks do
  use Ecto.Migration

  def change do
    create table(:risks) do
      add :descripcion, :string
      add :name, :string

      timestamps()
    end

  end
end
