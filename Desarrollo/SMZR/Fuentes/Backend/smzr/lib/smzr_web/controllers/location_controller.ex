defmodule SmzrWeb.LocationController do
  use SmzrWeb, :controller

  alias Smzr.Tracking
  alias Smzr.Tracking.Location

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    locations = Tracking.list_locations()
    render(conn, "index.json", locations: locations)
  end

  def create(conn, %{"location" => location_params}) do
    with {:ok, %Location{} = location} <- Tracking.create_location(location_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.location_path(conn, :show, location))
      |> render("show.json", location: location)
    end
  end

  def show(conn, %{"id" => id}) do
    location = Tracking.get_location!(id)
    render(conn, "show.json", location: location)
  end

  def update(conn, %{"id" => id, "location" => location_params}) do
    location = Tracking.get_location!(id)

    with {:ok, %Location{} = location} <- Tracking.update_location(location, location_params) do
      render(conn, "show.json", location: location)
    end
  end

  def delete(conn, %{"id" => id}) do
    location = Tracking.get_location!(id)

    with {:ok, %Location{}} <- Tracking.delete_location(location) do
      send_resp(conn, :no_content, "")
    end
  end

end
