defmodule SmzrWeb.AilmentController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Ailment

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    ailments = Monitoring.list_ailments()
    render(conn, "index.json", ailments: ailments)
  end

  def create(conn, %{"ailment" => ailment_params}) do
    with {:ok, %Ailment{} = ailment} <- Monitoring.create_ailment(ailment_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.ailment_path(conn, :show, ailment))
      |> render("show.json", ailment: ailment)
    end
  end

  def show(conn, %{"id" => id}) do
    ailment = Monitoring.get_ailment!(id)
    render(conn, "show.json", ailment: ailment)
  end

  def update(conn, %{"id" => id, "ailment" => ailment_params}) do
    ailment = Monitoring.get_ailment!(id)

    with {:ok, %Ailment{} = ailment} <- Monitoring.update_ailment(ailment, ailment_params) do
      render(conn, "show.json", ailment: ailment)
    end
  end

  def delete(conn, %{"id" => id}) do
    ailment = Monitoring.get_ailment!(id)

    with {:ok, %Ailment{}} <- Monitoring.delete_ailment(ailment) do
      send_resp(conn, :no_content, "")
    end
  end
end
