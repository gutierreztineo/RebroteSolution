defmodule SmzrWeb.ProfileController do
  use SmzrWeb, :controller

  alias Smzr.Accounts
  alias Smzr.Accounts.Profile
  alias Smzr.Accounts.User

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    profiles = Accounts.list_profiles()
    render(conn, "index.json", profiles: profiles)
  end

  def create(conn, %{"profile" => profile_params}) do

    %User{ :id => user_id } = Guardian.Plug.current_resource(conn)

    with {:ok, %Profile{} = profile} <- Accounts.create_profile(Map.put(profile_params, "user_id", user_id )) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.profile_path(conn, :show, profile))
      |> render("show.json", profile: profile)
    end
  end

  def show(conn, %{"id" => id}) do
    profile = Accounts.get_profile!(id)
    render(conn, "show.json", profile: profile)
  end

  def update(conn, %{"id" => id, "profile" => profile_params}) do
    profile = Accounts.get_profile!(id)

    with {:ok, %Profile{} = profile} <- Accounts.update_profile(profile, profile_params) do
      render(conn, "show.json", profile: profile)
    end
  end

  def delete(conn, %{"id" => id}) do
    profile = Accounts.get_profile!(id)

    with {:ok, %Profile{}} <- Accounts.delete_profile(profile) do
      send_resp(conn, :no_content, "")
    end
  end

  def my_profile(conn, _params) do
    %User{ :id => user_id } = Guardian.Plug.current_resource(conn)
    profile = Accounts.get_profile_by_user!(user_id)
    render(conn, "show.json", profile: profile)
  end

end
