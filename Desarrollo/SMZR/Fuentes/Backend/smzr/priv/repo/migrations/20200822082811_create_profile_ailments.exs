defmodule Smzr.Repo.Migrations.CreateProfileAilments do
  use Ecto.Migration

  def change do
    create table(:profile_ailments) do
      add :profile_id, references(:profiles, on_delete: :nothing)
      add :ailment_levels_id, references(:ailment_levels, on_delete: :nothing)

      timestamps()
    end

    create index(:profile_ailments, [:profile_id])
    create index(:profile_ailments, [:ailment_levels_id])
  end
end
