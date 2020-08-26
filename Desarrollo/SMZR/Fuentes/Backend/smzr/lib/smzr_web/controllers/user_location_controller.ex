defmodule SmzrWeb.UserLocationController do
  use SmzrWeb, :controller

  alias Smzr.Tracking
  alias Smzr.Tracking.UserLocation
  alias Smzr.Tracking.Location
  alias Smzr.Accounts.User

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    user_locations = Tracking.list_user_locations()
    render(conn, "index.json", user_locations: user_locations)
  end

  def create(conn, %{"user_location" => user_location_params}) do
    with {:ok, %UserLocation{} = user_location} <- Tracking.create_user_location(user_location_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.user_location_path(conn, :show, user_location))
      |> render("show.json", user_location: user_location)
    end
  end

  def show(conn, %{"id" => id}) do
    user_location = Tracking.get_user_location!(id)
    render(conn, "show.json", user_location: user_location)
  end

  def update(conn, %{"id" => id, "user_location" => user_location_params}) do
    user_location = Tracking.get_user_location!(id)

    with {:ok, %UserLocation{} = user_location} <- Tracking.update_user_location(user_location, user_location_params) do
      render(conn, "show.json", user_location: user_location)
    end
  end

  def delete(conn, %{"id" => id}) do
    user_location = Tracking.get_user_location!(id)

    with {:ok, %UserLocation{}} <- Tracking.delete_user_location(user_location) do
      send_resp(conn, :no_content, "")
    end
  end

  def my_location(conn, %{"location" => location_params}) do

    %User{ :id => user_id } = Guardian.Plug.current_resource(conn)

    location_id = case Tracking.get_location_by_lat_lng(location_params)  do
                        %Location{ id: id }  -> id
                        nil -> {:ok, location } = Tracking.create_location(location_params)
                               location.id
    end

    with {:ok, %UserLocation{} = user_location} <- Tracking.create_user_location(%{location_id: location_id, user_id: user_id}) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.user_location_path(conn, :show, user_location))
      |> render("show.json", user_location: user_location)
    end
  end
end
