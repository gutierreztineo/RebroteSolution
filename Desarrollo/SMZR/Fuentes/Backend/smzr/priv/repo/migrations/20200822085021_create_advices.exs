defmodule Smzr.Repo.Migrations.CreateAdvices do
  use Ecto.Migration

  def change do
    create table(:advices) do
      add :description, :string

      timestamps()
    end

  end
end
