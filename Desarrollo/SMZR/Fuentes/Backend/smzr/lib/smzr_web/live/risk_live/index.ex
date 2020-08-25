defmodule SmzrWeb.RiskLive.Index do
  use SmzrWeb, :live_view

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Risk

  @impl true
  def mount(_params, _session, socket) do
    {:ok, assign(socket, :risks, list_risks())}
  end

  @impl true
  def handle_params(params, _url, socket) do
    {:noreply, apply_action(socket, socket.assigns.live_action, params)}
  end

  defp apply_action(socket, :edit, %{"id" => id}) do
    socket
    |> assign(:page_title, "Edit Risk")
    |> assign(:risk, Monitoring.get_risk!(id))
  end

  defp apply_action(socket, :new, _params) do
    socket
    |> assign(:page_title, "New Risk")
    |> assign(:risk, %Risk{})
  end

  defp apply_action(socket, :index, _params) do
    socket
    |> assign(:page_title, "Listing Risks")
    |> assign(:risk, nil)
  end

  @impl true
  def handle_event("delete", %{"id" => id}, socket) do
    risk = Monitoring.get_risk!(id)
    {:ok, _} = Monitoring.delete_risk(risk)

    {:noreply, assign(socket, :risks, list_risks())}
  end

  defp list_risks do
    Monitoring.list_risks()
  end
end
