defmodule SmzrWeb.ProfileRiskController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.ProfileRisk

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    profile_risks = Monitoring.list_profile_risks()
    render(conn, "index.json", profile_risks: profile_risks)
  end

  def create(conn, %{"profile_risk" => profile_risk_params}) do
    with {:ok, %ProfileRisk{} = profile_risk} <- Monitoring.create_profile_risk(profile_risk_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.profile_risk_path(conn, :show, profile_risk))
      |> render("show.json", profile_risk: profile_risk)
    end
  end

  def show(conn, %{"id" => id}) do
    profile_risk = Monitoring.get_profile_risk!(id)
    render(conn, "show.json", profile_risk: profile_risk)
  end

  def update(conn, %{"id" => id, "profile_risk" => profile_risk_params}) do
    profile_risk = Monitoring.get_profile_risk!(id)

    with {:ok, %ProfileRisk{} = profile_risk} <- Monitoring.update_profile_risk(profile_risk, profile_risk_params) do
      render(conn, "show.json", profile_risk: profile_risk)
    end
  end

  def delete(conn, %{"id" => id}) do
    profile_risk = Monitoring.get_profile_risk!(id)

    with {:ok, %ProfileRisk{}} <- Monitoring.delete_profile_risk(profile_risk) do
      send_resp(conn, :no_content, "")
    end
  end
end
