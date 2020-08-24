defmodule Smzr.Repo.Migrations.CreateProfileRisks do
  use Ecto.Migration

  def change do
    create table(:profile_risks) do
      add :risk_id, references(:risks, on_delete: :nothing)
      add :profile_id, references(:profiles, on_delete: :nothing)

      timestamps()
    end

    create index(:profile_risks, [:risk_id])
    create index(:profile_risks, [:profile_id])
  end
end
