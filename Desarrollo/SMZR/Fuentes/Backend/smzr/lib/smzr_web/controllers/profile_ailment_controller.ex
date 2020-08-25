defmodule SmzrWeb.ProfileAilmentController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileAilment

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    profile_ailments = Monitoring.list_profile_ailments()
    render(conn, "index.json", profile_ailments: profile_ailments)
  end

  def create(conn, %{"profile_ailment" => profile_ailment_params}) do
    with {:ok, %ProfileAilment{} = profile_ailment} <- Monitoring.create_profile_ailment(profile_ailment_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.profile_ailment_path(conn, :show, profile_ailment))
      |> render("show.json", profile_ailment: profile_ailment)
    end
  end

  def show(conn, %{"id" => id}) do
    profile_ailment = Monitoring.get_profile_ailment!(id)
    render(conn, "show.json", profile_ailment: profile_ailment)
  end

  def update(conn, %{"id" => id, "profile_ailment" => profile_ailment_params}) do
    profile_ailment = Monitoring.get_profile_ailment!(id)

    with {:ok, %ProfileAilment{} = profile_ailment} <- Monitoring.update_profile_ailment(profile_ailment, profile_ailment_params) do
      render(conn, "show.json", profile_ailment: profile_ailment)
    end
  end

  def delete(conn, %{"id" => id}) do
    profile_ailment = Monitoring.get_profile_ailment!(id)

    with {:ok, %ProfileAilment{}} <- Monitoring.delete_profile_ailment(profile_ailment) do
      send_resp(conn, :no_content, "")
    end
  end
end
