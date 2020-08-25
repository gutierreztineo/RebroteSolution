defmodule SmzrWeb.AdviceController do
  use SmzrWeb, :controller

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Advice

  action_fallback SmzrWeb.FallbackController

  def index(conn, _params) do
    advices = Monitoring.list_advices()
    render(conn, "index.json", advices: advices)
  end

  def create(conn, %{"advice" => advice_params}) do
    with {:ok, %Advice{} = advice} <- Monitoring.create_advice(advice_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.advice_path(conn, :show, advice))
      |> render("show.json", advice: advice)
    end
  end

  def show(conn, %{"id" => id}) do
    advice = Monitoring.get_advice!(id)
    render(conn, "show.json", advice: advice)
  end

  def update(conn, %{"id" => id, "advice" => advice_params}) do
    advice = Monitoring.get_advice!(id)

    with {:ok, %Advice{} = advice} <- Monitoring.update_advice(advice, advice_params) do
      render(conn, "show.json", advice: advice)
    end
  end

  def delete(conn, %{"id" => id}) do
    advice = Monitoring.get_advice!(id)

    with {:ok, %Advice{}} <- Monitoring.delete_advice(advice) do
      send_resp(conn, :no_content, "")
    end
  end
end
