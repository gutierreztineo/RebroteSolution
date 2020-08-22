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
end
