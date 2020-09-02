defmodule SmzrWeb.ProfileAilmentController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileAilment

  alias Smzr.Accounts
  alias Smzr.Accounts.Profile
  alias Smzr.Accounts.User

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    profile_ailments = Monitoring.list_profile_ailments()
    render(conn, "index.json", profile_ailments: profile_ailments)
  end

  def create(conn, %{"profile_ailment" => profile_ailment_params}) do
    %User{ :id => user_id } = Guardian.Plug.current_resource(conn)
    case Monitoring.get_ailment_level(profile_ailment_params["ailment_levels_id"])  do
      nil -> conn |> json(%{message: "Nivel no existe"})
      _ ->  with %Profile{ :id => profile_id } <- Accounts.get_profile_by_user!(user_id) do
              with {:ok, %ProfileAilment{} = profile_ailment} <- Monitoring.create_profile_ailment(Map.put(profile_ailment_params, "profile_id", profile_id )) do
                conn
                |> put_status(:created)
                |> put_resp_header("location", Routes.profile_ailment_path(conn, :show, profile_ailment))
                |> render("show.json", profile_ailment: profile_ailment)
              end
            end
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

  def my_ailments(conn, _params) do
    %User{ :id => user_id } = Guardian.Plug.current_resource(conn)

    profile_ailments = Monitoring.list_profile_ailments_by_user(user_id)
    render(conn, "index.json", profile_ailments: profile_ailments)
  end
end
