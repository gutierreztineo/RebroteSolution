defmodule Smzr.Repo.Migrations.CreateAilmentAdvices do
  use Ecto.Migration

  def change do
    create table(:ailment_advices) do
      add :ailment_id, references(:ailments, on_delete: :nothing)
      add :advice_id, references(:advices, on_delete: :nothing)

      timestamps()
    end

    create index(:ailment_advices, [:ailment_id])
    create index(:ailment_advices, [:advice_id])
  end
end
