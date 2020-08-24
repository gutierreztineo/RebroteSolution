defmodule SmzrWeb.AilmentAdviceController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentAdvice

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    ailment_advices = Monitoring.list_ailment_advices()
    render(conn, "index.json", ailment_advices: ailment_advices)
  end

  def create(conn, %{"ailment_advice" => ailment_advice_params}) do
    with {:ok, %AilmentAdvice{} = ailment_advice} <- Monitoring.create_ailment_advice(ailment_advice_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.ailment_advice_path(conn, :show, ailment_advice))
      |> render("show.json", ailment_advice: ailment_advice)
    end
  end

  def show(conn, %{"id" => id}) do
    ailment_advice = Monitoring.get_ailment_advice!(id)
    render(conn, "show.json", ailment_advice: ailment_advice)
  end

  def update(conn, %{"id" => id, "ailment_advice" => ailment_advice_params}) do
    ailment_advice = Monitoring.get_ailment_advice!(id)

    with {:ok, %AilmentAdvice{} = ailment_advice} <- Monitoring.update_ailment_advice(ailment_advice, ailment_advice_params) do
      render(conn, "show.json", ailment_advice: ailment_advice)
    end
  end

  def delete(conn, %{"id" => id}) do
    ailment_advice = Monitoring.get_ailment_advice!(id)

    with {:ok, %AilmentAdvice{}} <- Monitoring.delete_ailment_advice(ailment_advice) do
      send_resp(conn, :no_content, "")
    end
  end
end
