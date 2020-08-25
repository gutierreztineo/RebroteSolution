defmodule SmzrWeb.AilmentLevelController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentLevel

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    ailment_levels = Monitoring.list_ailment_levels()
    render(conn, "index.json", ailment_levels: ailment_levels)
  end

  def create(conn, %{"ailment_level" => ailment_level_params}) do

    case Monitoring.get_ailment(ailment_level_params["ailment_id"])  do
      nil -> conn |> json(%{"message": "Malestar no existe"})
      _ ->
        with {:ok, %AilmentLevel{} = ailment_level} <- Monitoring.create_ailment_level(ailment_level_params) do
          conn
          |> put_status(:created)
          |> put_resp_header("location", Routes.ailment_level_path(conn, :show, ailment_level))
          |> render("show.json", ailment_level: ailment_level)
        end
    end
  end

  def show(conn, %{"id" => id}) do
    ailment_level = Monitoring.get_ailment_level!(id)
    render(conn, "show.json", ailment_level: ailment_level)
  end

  def update(conn, %{"id" => id, "ailment_level" => ailment_level_params}) do
    ailment_level = Monitoring.get_ailment_level!(id)

    with {:ok, %AilmentLevel{} = ailment_level} <- Monitoring.update_ailment_level(ailment_level, ailment_level_params) do
      render(conn, "show.json", ailment_level: ailment_level)
    end
  end

  def delete(conn, %{"id" => id}) do
    ailment_level = Monitoring.get_ailment_level!(id)

    with {:ok, %AilmentLevel{}} <- Monitoring.delete_ailment_level(ailment_level) do
      send_resp(conn, :no_content, "")
    end
  end
end
