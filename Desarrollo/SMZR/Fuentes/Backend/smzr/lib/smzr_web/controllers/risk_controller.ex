defmodule SmzrWeb.RiskController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Risk

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    risks = Monitoring.list_risks()
    render(conn, "index.json", risks: risks)
  end

  def create(conn, %{"risk" => risk_params}) do
    with {:ok, %Risk{} = risk} <- Monitoring.create_risk(risk_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.risk_path(conn, :show, risk))
      |> render("show.json", risk: risk)
    end
  end

  def show(conn, %{"id" => id}) do
    risk = Monitoring.get_risk!(id)
    render(conn, "show.json", risk: risk)
  end

  def update(conn, %{"id" => id, "risk" => risk_params}) do
    risk = Monitoring.get_risk!(id)

    with {:ok, %Risk{} = risk} <- Monitoring.update_risk(risk, risk_params) do
      render(conn, "show.json", risk: risk)
    end
  end

  def delete(conn, %{"id" => id}) do
    risk = Monitoring.get_risk!(id)

    with {:ok, %Risk{}} <- Monitoring.delete_risk(risk) do
      send_resp(conn, :no_content, "")
    end
  end
end
