defmodule Smzr.Monitoring do
  @moduledoc """
  The Monitoring context.
  """

  import Ecto.Query, warn: false
  alias Smzr.Repo

  alias Smzr.Monitoring.Ailment

  @doc """
  Returns the list of ailments.

  ## Examples

      iex> list_ailments()
      [%Ailment{}, ...]

  """
  def list_ailments do
    Repo.all(Ailment)
  end

  @doc """
  Gets a single ailment.

  Raises `Ecto.NoResultsError` if the Ailment does not exist.

  ## Examples

      iex> get_ailment!(123)
      %Ailment{}

      iex> get_ailment!(456)
      ** (Ecto.NoResultsError)

  """
  def get_ailment!(id), do: Repo.get!(Ailment, id)

  @doc """
  Creates a ailment.

  ## Examples

      iex> create_ailment(%{field: value})
      {:ok, %Ailment{}}

      iex> create_ailment(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_ailment(attrs \\ %{}) do
    %Ailment{}
    |> Ailment.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a ailment.

  ## Examples

      iex> update_ailment(ailment, %{field: new_value})
      {:ok, %Ailment{}}

      iex> update_ailment(ailment, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_ailment(%Ailment{} = ailment, attrs) do
    ailment
    |> Ailment.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a ailment.

  ## Examples

      iex> delete_ailment(ailment)
      {:ok, %Ailment{}}

      iex> delete_ailment(ailment)
      {:error, %Ecto.Changeset{}}

  """
  def delete_ailment(%Ailment{} = ailment) do
    Repo.delete(ailment)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking ailment changes.

  ## Examples

      iex> change_ailment(ailment)
      %Ecto.Changeset{data: %Ailment{}}

  """
  def change_ailment(%Ailment{} = ailment, attrs \\ %{}) do
    Ailment.changeset(ailment, attrs)
  end

  alias Smzr.Monitoring.ProfileAilment

  @doc """
  Returns the list of profile_ailments.

  ## Examples

      iex> list_profile_ailments()
      [%ProfileAilment{}, ...]

  """
  def list_profile_ailments do
    Repo.all(ProfileAilment)
  end

  @doc """
  Gets a single profile_ailment.

  Raises `Ecto.NoResultsError` if the Profile ailment does not exist.

  ## Examples

      iex> get_profile_ailment!(123)
      %ProfileAilment{}

      iex> get_profile_ailment!(456)
      ** (Ecto.NoResultsError)

  """
  def get_profile_ailment!(id), do: Repo.get!(ProfileAilment, id)

  @doc """
  Creates a profile_ailment.

  ## Examples

      iex> create_profile_ailment(%{field: value})
      {:ok, %ProfileAilment{}}

      iex> create_profile_ailment(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_profile_ailment(attrs \\ %{}) do
    %ProfileAilment{}
    |> ProfileAilment.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a profile_ailment.

  ## Examples

      iex> update_profile_ailment(profile_ailment, %{field: new_value})
      {:ok, %ProfileAilment{}}

      iex> update_profile_ailment(profile_ailment, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_profile_ailment(%ProfileAilment{} = profile_ailment, attrs) do
    profile_ailment
    |> ProfileAilment.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a profile_ailment.

  ## Examples

      iex> delete_profile_ailment(profile_ailment)
      {:ok, %ProfileAilment{}}

      iex> delete_profile_ailment(profile_ailment)
      {:error, %Ecto.Changeset{}}

  """
  def delete_profile_ailment(%ProfileAilment{} = profile_ailment) do
    Repo.delete(profile_ailment)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking profile_ailment changes.

  ## Examples

      iex> change_profile_ailment(profile_ailment)
      %Ecto.Changeset{data: %ProfileAilment{}}

  """
  def change_profile_ailment(%ProfileAilment{} = profile_ailment, attrs \\ %{}) do
    ProfileAilment.changeset(profile_ailment, attrs)
  end

  alias Smzr.Monitoring.AilmentLevel

  @doc """
  Returns the list of ailment_levels.

  ## Examples

      iex> list_ailment_levels()
      [%AilmentLevel{}, ...]

  """
  def list_ailment_levels do
    Repo.all(AilmentLevel)
  end

  @doc """
  Gets a single ailment_level.

  Raises `Ecto.NoResultsError` if the Ailment level does not exist.

  ## Examples

      iex> get_ailment_level!(123)
      %AilmentLevel{}

      iex> get_ailment_level!(456)
      ** (Ecto.NoResultsError)

  """
  def get_ailment_level!(id), do: Repo.get!(AilmentLevel, id)

  @doc """
  Creates a ailment_level.

  ## Examples

      iex> create_ailment_level(%{field: value})
      {:ok, %AilmentLevel{}}

      iex> create_ailment_level(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_ailment_level(attrs \\ %{}) do
    %AilmentLevel{}
    |> AilmentLevel.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a ailment_level.

  ## Examples

      iex> update_ailment_level(ailment_level, %{field: new_value})
      {:ok, %AilmentLevel{}}

      iex> update_ailment_level(ailment_level, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_ailment_level(%AilmentLevel{} = ailment_level, attrs) do
    ailment_level
    |> AilmentLevel.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a ailment_level.

  ## Examples

      iex> delete_ailment_level(ailment_level)
      {:ok, %AilmentLevel{}}

      iex> delete_ailment_level(ailment_level)
      {:error, %Ecto.Changeset{}}

  """
  def delete_ailment_level(%AilmentLevel{} = ailment_level) do
    Repo.delete(ailment_level)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking ailment_level changes.

  ## Examples

      iex> change_ailment_level(ailment_level)
      %Ecto.Changeset{data: %AilmentLevel{}}

  """
  def change_ailment_level(%AilmentLevel{} = ailment_level, attrs \\ %{}) do
    AilmentLevel.changeset(ailment_level, attrs)
  end

  alias Smzr.Monitoring.Risk

  @doc """
  Returns the list of risks.

  ## Examples

      iex> list_risks()
      [%Risk{}, ...]

  """
  def list_risks do
    Repo.all(Risk)
  end

  @doc """
  Gets a single risk.

  Raises `Ecto.NoResultsError` if the Risk does not exist.

  ## Examples

      iex> get_risk!(123)
      %Risk{}

      iex> get_risk!(456)
      ** (Ecto.NoResultsError)

  """
  def get_risk!(id), do: Repo.get!(Risk, id)

  @doc """
  Creates a risk.

  ## Examples

      iex> create_risk(%{field: value})
      {:ok, %Risk{}}

      iex> create_risk(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_risk(attrs \\ %{}) do
    %Risk{}
    |> Risk.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a risk.

  ## Examples

      iex> update_risk(risk, %{field: new_value})
      {:ok, %Risk{}}

      iex> update_risk(risk, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_risk(%Risk{} = risk, attrs) do
    risk
    |> Risk.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a risk.

  ## Examples

      iex> delete_risk(risk)
      {:ok, %Risk{}}

      iex> delete_risk(risk)
      {:error, %Ecto.Changeset{}}

  """
  def delete_risk(%Risk{} = risk) do
    Repo.delete(risk)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking risk changes.

  ## Examples

      iex> change_risk(risk)
      %Ecto.Changeset{data: %Risk{}}

  """
  def change_risk(%Risk{} = risk, attrs \\ %{}) do
    Risk.changeset(risk, attrs)
  end

  alias Smzr.Monitoring.ProfileRisk

  @doc """
  Returns the list of profile_risks.

  ## Examples

      iex> list_profile_risks()
      [%ProfileRisk{}, ...]

  """
  def list_profile_risks do
    Repo.all(ProfileRisk)
  end

  @doc """
  Gets a single profile_risk.

  Raises `Ecto.NoResultsError` if the Profile risk does not exist.

  ## Examples

      iex> get_profile_risk!(123)
      %ProfileRisk{}

      iex> get_profile_risk!(456)
      ** (Ecto.NoResultsError)

  """
  def get_profile_risk!(id), do: Repo.get!(ProfileRisk, id)

  @doc """
  Creates a profile_risk.

  ## Examples

      iex> create_profile_risk(%{field: value})
      {:ok, %ProfileRisk{}}

      iex> create_profile_risk(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_profile_risk(attrs \\ %{}) do
    %ProfileRisk{}
    |> ProfileRisk.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a profile_risk.

  ## Examples

      iex> update_profile_risk(profile_risk, %{field: new_value})
      {:ok, %ProfileRisk{}}

      iex> update_profile_risk(profile_risk, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_profile_risk(%ProfileRisk{} = profile_risk, attrs) do
    profile_risk
    |> ProfileRisk.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a profile_risk.

  ## Examples

      iex> delete_profile_risk(profile_risk)
      {:ok, %ProfileRisk{}}

      iex> delete_profile_risk(profile_risk)
      {:error, %Ecto.Changeset{}}

  """
  def delete_profile_risk(%ProfileRisk{} = profile_risk) do
    Repo.delete(profile_risk)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking profile_risk changes.

  ## Examples

      iex> change_profile_risk(profile_risk)
      %Ecto.Changeset{data: %ProfileRisk{}}

  """
  def change_profile_risk(%ProfileRisk{} = profile_risk, attrs \\ %{}) do
    ProfileRisk.changeset(profile_risk, attrs)
  end

  alias Smzr.Monitoring.Advice

  @doc """
  Returns the list of advices.

  ## Examples

      iex> list_advices()
      [%Advice{}, ...]

  """
  def list_advices do
    Repo.all(Advice)
  end

  @doc """
  Gets a single advice.

  Raises `Ecto.NoResultsError` if the Advice does not exist.

  ## Examples

      iex> get_advice!(123)
      %Advice{}

      iex> get_advice!(456)
      ** (Ecto.NoResultsError)

  """
  def get_advice!(id), do: Repo.get!(Advice, id)

  @doc """
  Creates a advice.

  ## Examples

      iex> create_advice(%{field: value})
      {:ok, %Advice{}}

      iex> create_advice(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_advice(attrs \\ %{}) do
    %Advice{}
    |> Advice.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a advice.

  ## Examples

      iex> update_advice(advice, %{field: new_value})
      {:ok, %Advice{}}

      iex> update_advice(advice, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_advice(%Advice{} = advice, attrs) do
    advice
    |> Advice.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a advice.

  ## Examples

      iex> delete_advice(advice)
      {:ok, %Advice{}}

      iex> delete_advice(advice)
      {:error, %Ecto.Changeset{}}

  """
  def delete_advice(%Advice{} = advice) do
    Repo.delete(advice)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking advice changes.

  ## Examples

      iex> change_advice(advice)
      %Ecto.Changeset{data: %Advice{}}

  """
  def change_advice(%Advice{} = advice, attrs \\ %{}) do
    Advice.changeset(advice, attrs)
  end

  alias Smzr.Monitoring.AilmentAdvice

  @doc """
  Returns the list of ailment_advices.

  ## Examples

      iex> list_ailment_advices()
      [%AilmentAdvice{}, ...]

  """
  def list_ailment_advices do
    Repo.all(AilmentAdvice)
  end

  @doc """
  Gets a single ailment_advice.

  Raises `Ecto.NoResultsError` if the Ailment advice does not exist.

  ## Examples

      iex> get_ailment_advice!(123)
      %AilmentAdvice{}

      iex> get_ailment_advice!(456)
      ** (Ecto.NoResultsError)

  """
  def get_ailment_advice!(id), do: Repo.get!(AilmentAdvice, id)

  @doc """
  Creates a ailment_advice.

  ## Examples

      iex> create_ailment_advice(%{field: value})
      {:ok, %AilmentAdvice{}}

      iex> create_ailment_advice(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_ailment_advice(attrs \\ %{}) do
    %AilmentAdvice{}
    |> AilmentAdvice.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a ailment_advice.

  ## Examples

      iex> update_ailment_advice(ailment_advice, %{field: new_value})
      {:ok, %AilmentAdvice{}}

      iex> update_ailment_advice(ailment_advice, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_ailment_advice(%AilmentAdvice{} = ailment_advice, attrs) do
    ailment_advice
    |> AilmentAdvice.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a ailment_advice.

  ## Examples

      iex> delete_ailment_advice(ailment_advice)
      {:ok, %AilmentAdvice{}}

      iex> delete_ailment_advice(ailment_advice)
      {:error, %Ecto.Changeset{}}

  """
  def delete_ailment_advice(%AilmentAdvice{} = ailment_advice) do
    Repo.delete(ailment_advice)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking ailment_advice changes.

  ## Examples

      iex> change_ailment_advice(ailment_advice)
      %Ecto.Changeset{data: %AilmentAdvice{}}

  """
  def change_ailment_advice(%AilmentAdvice{} = ailment_advice, attrs \\ %{}) do
    AilmentAdvice.changeset(ailment_advice, attrs)
  end
end
