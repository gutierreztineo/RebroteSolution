defmodule Smzr.Repo.Migrations.CreateAilmentLevels do
  use Ecto.Migration

  def change do
    create table(:ailment_levels) do
      add :description, :string
      add :level, :integer
      add :ailment_id, references(:ailments, on_delete: :nothing)

      timestamps()
    end

    create index(:ailment_levels, [:ailment_id])
  end
end
