defmodule Smzr.Tracking do
  @moduledoc """
  The Tracking context.
  """

  import Ecto.Query, warn: false
  alias Smzr.Repo

  alias Smzr.Tracking.Location

  @doc """
  Returns the list of locations.

  ## Examples

      iex> list_locations()
      [%Location{}, ...]

  """
  def list_locations do
    Repo.all(Location)
  end

  @doc """
  Gets a single location.

  Raises `Ecto.NoResultsError` if the Location does not exist.

  ## Examples

      iex> get_location!(123)
      %Location{}

      iex> get_location!(456)
      ** (Ecto.NoResultsError)

  """
  def get_location!(id), do: Repo.get!(Location, id)

  @doc """
  Creates a location.

  ## Examples

      iex> create_location(%{field: value})
      {:ok, %Location{}}

      iex> create_location(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_location(attrs \\ %{}) do
    %Location{}
    |> Location.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a location.

  ## Examples

      iex> update_location(location, %{field: new_value})
      {:ok, %Location{}}

      iex> update_location(location, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_location(%Location{} = location, attrs) do
    location
    |> Location.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a location.

  ## Examples

      iex> delete_location(location)
      {:ok, %Location{}}

      iex> delete_location(location)
      {:error, %Ecto.Changeset{}}

  """
  def delete_location(%Location{} = location) do
    Repo.delete(location)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking location changes.

  ## Examples

      iex> change_location(location)
      %Ecto.Changeset{data: %Location{}}

  """
  def change_location(%Location{} = location, attrs \\ %{}) do
    Location.changeset(location, attrs)
  end

  alias Smzr.Tracking.UserLocation

  @doc """
  Returns the list of user_locations.

  ## Examples

      iex> list_user_locations()
      [%UserLocation{}, ...]

  """
  def list_user_locations do
    Repo.all(UserLocation)
  end

  @doc """
  Gets a single user_location.

  Raises `Ecto.NoResultsError` if the User location does not exist.

  ## Examples

      iex> get_user_location!(123)
      %UserLocation{}

      iex> get_user_location!(456)
      ** (Ecto.NoResultsError)

  """
  def get_user_location!(id), do: Repo.get!(UserLocation, id)

  @doc """
  Creates a user_location.

  ## Examples

      iex> create_user_location(%{field: value})
      {:ok, %UserLocation{}}

      iex> create_user_location(%{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def create_user_location(attrs \\ %{}) do
    %UserLocation{}
    |> UserLocation.changeset(attrs)
    |> Repo.insert()
  end

  @doc """
  Updates a user_location.

  ## Examples

      iex> update_user_location(user_location, %{field: new_value})
      {:ok, %UserLocation{}}

      iex> update_user_location(user_location, %{field: bad_value})
      {:error, %Ecto.Changeset{}}

  """
  def update_user_location(%UserLocation{} = user_location, attrs) do
    user_location
    |> UserLocation.changeset(attrs)
    |> Repo.update()
  end

  @doc """
  Deletes a user_location.

  ## Examples

      iex> delete_user_location(user_location)
      {:ok, %UserLocation{}}

      iex> delete_user_location(user_location)
      {:error, %Ecto.Changeset{}}

  """
  def delete_user_location(%UserLocation{} = user_location) do
    Repo.delete(user_location)
  end

  @doc """
  Returns an `%Ecto.Changeset{}` for tracking user_location changes.

  ## Examples

      iex> change_user_location(user_location)
      %Ecto.Changeset{data: %UserLocation{}}

  """
  def change_user_location(%UserLocation{} = user_location, attrs \\ %{}) do
    UserLocation.changeset(user_location, attrs)
  end
end
